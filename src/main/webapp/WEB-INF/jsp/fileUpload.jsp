<%--
  Created by IntelliJ IDEA.
  User: Larry-sea
  Date: 2017/5/20
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Spring MVC上传文件示例</title>
</head>
<body>
<form:form method="POST" modelAttribute="fileUpload"
           enctype="multipart/form-data">
    请选择一个文件上传 :
    <input type="file" name="file" />
    <input type="submit" value="提交上传" />
</form:form>
</body>
</html>
