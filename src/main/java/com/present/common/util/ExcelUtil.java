package com.present.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * excel工具类，生成excel表格
 */
public class ExcelUtil {


    /**
     * 导出excel表格的方法
     *
     * @param exportData   导出的的数据  这个多维数组中的列和excel表格中列是对应一致的
     * @param fileName     文件名
     * @param sheetName    sheetName  这个默认为班级名
     * @param header       表头数据
     * @param fileSavePath 文件保存路径
     */
    public static String exportExcel(String[][] exportData, String fileName, String sheetName, String[] header, String fileSavePath) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFFont hssfFont = wb.createFont();
        hssfFont.setFontName("微软雅黑");
        hssfFont.setFontHeightInPoints((short) 11);
        style.setFont(hssfFont);
        initHeader(row, header, 1, style);
        initData(sheet, exportData, style);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(fileSavePath);
        stringBuilder.append('\\');
        stringBuilder.append(fileName);
        stringBuilder.append(".xls");
        String filePath = stringBuilder.toString();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fout = new FileOutputStream(file.getAbsolutePath());
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }


    /**
     * 初始化header
     *
     * @param row         待添加数据的行
     * @param headerArray 其中包含header的数据  每个header头在第一行数据中但是每个header会间隔
     * @param headerGap   头之间的间隔
     */
    public static void initHeader(final HSSFRow row, final String[] headerArray, int headerGap, final HSSFCellStyle hssfStyle) {
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
            HSSFCell cell = row.createCell((short) headerIndex);
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
