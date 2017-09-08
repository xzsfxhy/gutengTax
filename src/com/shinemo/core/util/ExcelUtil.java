package com.shinemo.core.util;

import com.shinemo.nsfw.user.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import java.util.List;

public class ExcelUtil {
    /**
      * @Description:
      * @author: guteng
      * @Param: [userList:用户列表, outputStream:输出流]
      * @Date:  2017/7/2
     */

    public static void exportUserExcel(List<User> userList, ServletOutputStream outputStream) {
        try {
            //1.创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //1.1创建合并单元格对象
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);//起始行号,结束行号,起始列号,结束列号
            //1.2头标题样式
            HSSFCellStyle style1=createCellStyle(workbook, (short)16);
            //1.3列标题样式
            HSSFCellStyle style2=createCellStyle(workbook, (short)13);

            //2.创建工作表
            HSSFSheet sheet = workbook.createSheet("用户列表");
            //2.1加载合并单元格对象
            sheet.addMergedRegion(cellRangeAddress);

            //3.创建行
            //3.1创建头标题行,并且设置头标题
            HSSFRow row1 = sheet.createRow(0);
            HSSFCell cell1 = row1.createCell(0);
            //加载头标题样式
            cell1.setCellStyle(style1);
            cell1.setCellValue("用户列表");
            //3.2创建列标题行,并且设置列标题
            HSSFRow row2 = sheet.createRow(1);
            String[] titles={"用户名","账号","所属部门","性别","电子邮箱"};
            for(int i=0;i<titles.length;i++){
                HSSFCell cell2 = row2.createCell(i);
                //加载列标题样式
                cell2.setCellStyle(style2);
                cell2.setCellValue(titles[i]);
            }

            //4.操作单元格,将用户列表写入excel
            if(userList!=null){
                for(int j=0;j<userList.size();j++){
                    HSSFRow row3 = sheet.createRow(j + 2);
                    HSSFCell cell11 = row3.createCell(0);
                    cell11.setCellValue(userList.get(j).getName());
                    HSSFCell cell12 = row3.createCell(1);
                    cell12.setCellValue(userList.get(j).getAccount());
                    HSSFCell cell13 = row3.createCell(2);
                    cell13.setCellValue(userList.get(j).getDept());
                    HSSFCell cell14 = row3.createCell(3);
                    cell14.setCellValue(userList.get(j).isGender()?"男":"女");
                    HSSFCell cell15 = row3.createCell(4);
                    cell15.setCellValue(userList.get(j).getEmail());
                }
            }

            //5.输出
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * @Description:
     * @author: guteng
     * @Param: [workbook:工作簿, i:字体大小]
     * @Date:  2017/7/2
     */

    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short i) {
        //单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints(i);
        //加载字体
        cellStyle.setFont(font);
        return cellStyle;
    }
}
