package com.present.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileOutputStream;

/**
 * excel�����࣬����excel���
 */
public class ExcelUtil {


    /**
     * ����excel���ķ���
     *
     * @param exportData   �����ĵ�����  �����ά�����е��к�excel��������Ƕ�Ӧһ�µ�
     * @param fileName     �ļ���
     * @param sheetName    sheetName  ���Ĭ��Ϊ�༶��
     * @param header       ��ͷ����
     * @param fileSavePath �ļ�����·��
     */
    public static void exportExcel(String[][] exportData, String fileName, String sheetName, String[] header, String fileSavePath) {
        // ��һ��������һ��webbook����Ӧһ��Excel�ļ�
        HSSFWorkbook wb = new HSSFWorkbook();
        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
        HSSFRow row = sheet.createRow((int) 0);
        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFFont hssfFont = wb.createFont();
        hssfFont.setFontName("΢���ź�");
        hssfFont.setFontHeightInPoints((short) 11);
        style.setFont(hssfFont);
        initHeader(row, header, 1, style);
        initData(sheet, exportData, style);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(fileSavePath);
        stringBuilder.append('/');
        stringBuilder.append(fileName);
        stringBuilder.append("xls");
        try {
            FileOutputStream fout = new FileOutputStream(stringBuilder.toString());
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ��ʼ��header
     *
     * @param row         ��������ݵ���
     * @param headerArray ���а���header������  ÿ��headerͷ�ڵ�һ�������е���ÿ��header����
     * @param headerGap   ͷ֮��ļ��
     */
    public static void initHeader(final HSSFRow row, final String[] headerArray, int headerGap, final HSSFCellStyle hssfStyle) {
        int headerIndex = 0;
        //����һ�����
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
     * ��ʼ��������ݲ���
     *
     * @param hssfSheet  ��Ҫ��ӵ���
     * @param exportData ��Ҫ����������
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
        //���������
        for (int row = 0; row < exportData.length; row++) {
            hssfRow = hssfSheet.createRow(2 + row);
            //���������
            for (int column = 0; column < exportData[row].length; column++) {
                hssfCell = hssfRow.createCell(column);
                hssfCell.setCellValue(exportData[row][column]);
                hssfCell.setCellStyle(hssfCellStyle);
            }
        }
    }

}
