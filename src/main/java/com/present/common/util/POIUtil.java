package com.present.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * excel读写工具类
 *
 * @author sun.kai
 *         2016年8月21日
 */
public class POIUtil {
    private final static Logger logger = LoggerFactory.getLogger(POIUtil.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * 读入excel文件，解析后返回
     *
     * @param file
     * @throws IOException
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
        //检查文件  
        checkFile(file);
        //获得Workbook工作薄对象  
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回  
        List<String[]> list = new ArrayList<String[]>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表  
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                //获得当前sheet的开始行  
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行  
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行  
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行  
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    //获得当前行的开始列  
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数  
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行  
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }

    public static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在  
        if (null == file) {
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名  
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件  
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名  
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;
        try {
            //获取excel文件的io流  
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if (fileName.endsWith(xls)) {
                //2003  
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                //2007  
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况  
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型  
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字  
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串  
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式  
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }



    /**
     * 导出excel表格的方法
     *
     * @param exportData 导出的的数据  这个多维数组中的列和excel表格中列是对应一致的
     * @param fileName   文件名
     * @param sheetName  sheetName  这个默认为班级名
     * @param header     表头数据
     */
    public static void exportExcel(String[][] exportData, String fileName, String sheetName, String[] header) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中

        HSSFCellStyle hssfCellStyle=wb.createCellStyle();

        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);

        HSSFFont hssfFont = wb.createFont();
        hssfFont.setFontName("微软雅黑");
        hssfFont.setFontHeightInPoints((short) 11);
        style.setFont(hssfFont);
        initHeader(row, header, 1, style);
        initData(sheet, exportData, style);
        try {
            FileOutputStream fout = new FileOutputStream("E:/" + fileName + ".xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 初始化header
     *
     * @param row         待添加数据的行
     * @param headerArray 其中包含header的数据  每个header头在第一行数据中但是每个header会间隔
     * @param headerGap   头之间的间隔
     */
    public static void initHeader(final HSSFRow row, final String[] headerArray,  int headerGap, final HSSFCellStyle hssfStyle) {
        int headerIndex = 0;
        //增加一个间隔
        headerGap++;
        if (headerGap < 0) {
            throw new IllegalArgumentException("headerGap must big than 0");
        }
        if (headerArray.length <= 0) {
            throw new IllegalArgumentException("headerArray length must big than zero");
        }
        if (row == null) {
            throw new IllegalArgumentException("row must cant empty");
        }
        for (int headerPosition = 0; headerPosition < headerArray.length; headerPosition++) {
            HSSFCell cell = row.createCell( headerIndex);
            cell.setCellStyle(hssfStyle);
            cell.setCellValue(headerArray[headerPosition]);
            headerIndex += headerGap;
        }

    }







    /**
     * 初始化表的数据部分
     *
     * @param hssfSheet  需要添加的行
     * @param exportData 需要导出的数据
     */
    public static void initData(HSSFSheet hssfSheet, String[][] exportData, final HSSFCellStyle hssfCellStyle) throws IllegalArgumentException {
        if (hssfSheet == null) {
            throw new IllegalArgumentException("hssfSheet  is empty");
        }
        if (exportData.length == 0) {
            throw new IllegalArgumentException("exportData is empty");
        }
        HSSFRow hssfRow;
        HSSFCell hssfCell;
        //添加行数据
        for (int row = 0; row < exportData.length; row++) {
            hssfRow = hssfSheet.createRow(2 + row);
            //添加列数据
            for (int column = 0; column < exportData[row].length; column++) {
                hssfCell = hssfRow.createCell(column);
                hssfCell.setCellValue(exportData[row][column]);
                hssfCell.setCellStyle(hssfCellStyle);
            }
        }
    }
}  