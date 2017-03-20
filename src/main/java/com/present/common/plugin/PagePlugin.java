package com.present.common.plugin;


import com.present.common.dto.PageInfoDto;
import com.present.common.exception.ExternalException;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @ClassName: PageInterceptor
 * @Description: 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。 
 * 利用拦截器实现Mybatis分页的原理： 
 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，
 * Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，
 * 而且对应的Sql语句是在Statement之前产生的，所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。
 * 在Mybatis中Statement语句是通过RoutingStatementHandler对象的 prepare方法生成的。
 * 所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，
 * 然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用 
 * StatementHandler对象的prepare方法，即调用invocation.proceed()。 
 * 对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，
 * 这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设 
 * 置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。 
 * 
 */ 
@Intercepts({  
	   @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PagePlugin implements Interceptor
{
    // 默认分页数据库类型是mysql
    private static String DEFAULT_DIALECT = "mysql";
    
    // 需要拦截的方法ID后缀
    private static String DEFAULT_PAGESQLID_SUFFIX = "WithPage";
    
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    
    /**
     * 
     * <p>Title: intercept</p>
     * <p>Description: 拦截后要执行的方法</p>
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable
    {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        
        // 通过MetaObject包装一个对象后可以获取或设置该对象的原本不可访问的属性
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,  
        		DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环  
        // 可以分离出最原始的的目标类)  
        while (metaStatementHandler.hasGetter("h"))
        {  
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,   
            DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        
        // 分离最后一个代理对象的目标类  
        while (metaStatementHandler.hasGetter("target")) 
        {  
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,   
            		DEFAULT_OBJECT_WRAPPER_FACTORY);
        }  
        
        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");  
        
        String dialect = null;
        String pageSqlIdSuffix = null;
        
        // 没有配置文件
        if (null == configuration.getVariables())
        {
            dialect = DEFAULT_DIALECT;
            pageSqlIdSuffix = DEFAULT_PAGESQLID_SUFFIX;
        }
        else
        {
            dialect = configuration.getVariables().getProperty("dialect");
            // 配置文件中没有指定分页数据库类型
            if (null == dialect || "".equals(dialect))
            {  
                dialect = DEFAULT_DIALECT;
            }  
            pageSqlIdSuffix = configuration.getVariables().getProperty("pageSqlId");
            // 配置文件中没有指定分页的方法后缀名
            if (null == pageSqlIdSuffix || "".equals(pageSqlIdSuffix))
            {  
                pageSqlIdSuffix = DEFAULT_PAGESQLID_SUFFIX;
            }
        }
        
        MappedStatement mappedStatement = (MappedStatement)   
        metaStatementHandler.getValue("delegate.mappedStatement");  
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以WithPage结尾的  
        // MappedStatement的sql  
        if (mappedStatement.getId().endsWith(pageSqlIdSuffix))
        {
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null)
            {
                throw new NullPointerException("parameterObject is null!");
            }
            else
            {  
                // 分页参数作为参数对象parameterObject的一个属性  
            	PageInfoDto page = (PageInfoDto) parameterObject;
                String sql = boundSql.getSql();
                // 重写sql  
                String pageSql = buildPageSql(sql, page, dialect);
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                
                Connection connection = (Connection) invocation.getArgs()[0];  
                // 重设分页参数里的总页数等  
                setPageParameter(sql, connection, mappedStatement, boundSql, page);  
            }
        }
        
        // 将执行权交给下一个拦截器  
        return invocation.proceed();
    }

    /**
     * <p>Title: plugin</p>
     * <p>Description:拦截器对应的封装原始对象的方法 </p>
     * @param target
     * @return 
     */
    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }

    /**
     * <p>Title: setProperties</p>
     * <p>Description: 设置注册拦截器时设定的属性</p>
     * @param properties 
     */
    public void setProperties(Properties properties)
    {
        
    }
    
    private String buildPageSql(String sql, PageInfoDto page, String dialect)
    {
        if (page != null)
        {
            StringBuilder pageSql = new StringBuilder();
            if ("mysql".equals(dialect))
            {
                pageSql = buildPageSqlForMysql(sql, page);
                sql = pageSql.toString();
            } 
            else if ("oracle".equals(dialect))
            {
                pageSql = buildPageSqlForOracle(sql, page);
                sql = pageSql.toString();
            }  
        }
        
        return sql;
    }  
    
    public StringBuilder buildPageSqlForMysql(String sql, PageInfoDto page)
    {  
        StringBuilder pageSql = new StringBuilder(100);
        pageSql.append(sql);
        pageSql.append(" limit " + ((page.getPageNo() - 1) * page.getPageSize()) + "," + page.getPageSize());
        
        return pageSql;
    }  
    
    public StringBuilder buildPageSqlForOracle(String sql, PageInfoDto page)
    {  
        StringBuilder pageSql = new StringBuilder(100);
        int beginrow = page.getPageNo() - 1;
        int endrow = page.getPageNo() - 1 + page.getPageSize();
                
        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
        pageSql.append(sql);
        pageSql.append(" ) temp where rownum <= ").append(String.valueOf(endrow));
        pageSql.append(") where row_id > ").append(String.valueOf(beginrow));
        
        return pageSql;
    }
    
    /** 
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用  
     * 者就可用通过 分页参数<code>PageParameter</code>获得相关信息。 
     *  
     * @param sql 
     * @param connection 
     * @param mappedStatement 
     * @param boundSql 
     * @param page 
     */  
    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,  
            BoundSql boundSql, PageInfoDto page)
    {  
        // 记录总记录数  
        String countSql = "select count(0) from (" + sql + ") as total";
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,  
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next())
            {
                totalCount = rs.getInt(1);
            }
            page.setTotalSize(totalCount);
        } 
        catch (SQLException e)
        {
        	throw new ExternalException();
        }
        finally
        {
            try
            {
                rs.close();
                countStmt.close();
            }
            catch (SQLException e)
            {
            	throw new ExternalException();
            }
        }
    }
      
    /** 
     * 对SQL参数(?)设值 
     *  
     * @param ps 
     * @param mappedStatement 
     * @param boundSql 
     * @param parameterObject 
     * @throws SQLException 
     */  
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,  
            Object parameterObject) throws SQLException 
    {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }
}
