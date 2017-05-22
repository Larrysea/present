package com.present;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileOutputStream;

public class CreateSimpleExcelToDisk {


    public static void main(String[] args) throws Exception {
    /*    // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("年龄");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("生日");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List list = CreateSimpleExcelToDisk.getStudent();
        Cell tempCell;
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            Student stu = (Student) list.get(i);
            // 第四步，创建单元格，并设置值
            tempCell = row.createCell((short) 0);
            tempCell.setCellValue((double) stu.getId());
            tempCell.setCellStyle(style);


            row.createCell((short) 1).setCellValue(stu.getName());
            row.createCell((short) 2).setCellValue((double) stu.getAge());
            cell = row.createCell((short) 3);
            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
                    .getBirth()));
            cell.setCellStyle(style);
        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("E:/students.xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        String[][] exportData = {{"学号", "姓名", "到课", "缺勤", "病假"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"},
                {"14056119", "王二狗", "1", "1", "1"}};
        String[] header = {"数据结构", "签到总数"};
        exportExcel(exportData, "142056201", "142056201", header);


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
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

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
