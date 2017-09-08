package com.shinemo.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * Created by pc on 2017/7/2.
 */
public class TestPOI {
    @Test
    public void testOutput() {
        try {
            //1.创建工作簿
            HSSFWorkbook workbook=new HSSFWorkbook();
            //2.创建工作表
            HSSFSheet sheet = workbook.createSheet("testExcel");//仅仅是指定表名
            //3.创建行
            HSSFRow row = sheet.createRow(3);
            //4.创建单元格
            HSSFCell cell = row.createCell(3);
            cell.setCellValue("testExcel");
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\itcast\\测试.xls");

            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

