package com.liziczh.erp.utils;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    /**
     * 为趋势图生成Excel.xlsx文件
     *
     * @param dataList Excel 数据
     * @param headers  Excel表头
     * @return XSSFWorkbook Excel工作簿对象
     */
    public static XSSFWorkbook create2007ExcelForTrend(String sheetName, String[] headers, List<Object[]> dataList) {
        // step1 创建一张空表
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        int rowCount = dataList.size() + 1;
        int colCount = headers.length;
        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < colCount; j++) {
                row.createCell(j);
            }
        }
        // step2 定义样式与数据格式
        XSSFDataFormat format = workbook.createDataFormat(); // 单元格数据格式
        XSSFFont headerFont = workbook.createFont();
        headerFont.setFontName("微软雅黑 Light");
        headerFont.setFontHeightInPoints((short) 12);
        // 定义表头样式
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setDataFormat(format.getFormat("@"));// 强制为文本形式
        headerStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 设置前景色填充模式
        headerStyle.setFillForegroundColor(new XSSFColor(new Color(249, 155, 28))); // 填充前景色#f99b1c
        headerStyle.setFont(headerFont); // 设置加粗字体
        // 定义日期型样式
        XSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("yyyy/MM/dd"));
        dateStyle.setAlignment(HorizontalAlignment.CENTER);
        // 定义整数类型样式
        XSSFCellStyle integerStyle = workbook.createCellStyle();
        integerStyle.setDataFormat(format.getFormat("0"));
        integerStyle.setAlignment(HorizontalAlignment.RIGHT);
        // 定义小数数据类型样式
        XSSFCellStyle decimalStyle = workbook.createCellStyle();
        decimalStyle.setDataFormat(format.getFormat("0.00000"));
        decimalStyle.setAlignment(HorizontalAlignment.RIGHT);
        // 定义百分数类型样式
        XSSFCellStyle percentStyle = workbook.createCellStyle();
        percentStyle.setDataFormat(format.getFormat("0.00%"));
        percentStyle.setAlignment(HorizontalAlignment.RIGHT);
        // 定义文本类型样式
        XSSFCellStyle textStyle = workbook.createCellStyle();
        textStyle.setDataFormat(format.getFormat("@"));
        textStyle.setAlignment(HorizontalAlignment.CENTER);

        // step3 写入表头
        XSSFRow rowHeader = sheet.getRow(0);
        rowHeader.setHeightInPoints((float) 22.5);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = rowHeader.getCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(headers[i]);
            sheet.setColumnWidth(i, (headers[i].length() + 2) * 2 * 256);
        }
        // step4 写入表格数据
        int rowNum = dataList.size();
        for (Object[] data : dataList) {
            XSSFRow row = sheet.getRow(rowNum);
            row.getCell(0).setCellStyle(textStyle);
            row.getCell(0).setCellValue(dataList.size() - rowNum + 1);
            sheet.setColumnWidth(0, 4 * 256);
            for (int j = 0; j < data.length; j++) {
                XSSFCell cell = row.getCell(j + 1);
                Boolean isDate = false; // data[j]是否为日期型
                Boolean isDecimal = false; // data[j]是否为小数型
                Boolean isInteger = false; // data[j]是否为整数
                Boolean isPercent = false; // data[j]是否为百分数
                if (data[j] != null || "".equals(data[j])) {
                    // 判断data[j]是否为日期型
                    isDate = data[j] instanceof Date;
                    //判断data[j]是否为整数型
                    isInteger = String.valueOf(data[j]).matches("^([0-9]{1,})$");
                    //判断data[j]是否为浮点型
                    isDecimal = String.valueOf(data[j]).matches("^([0-9]{1,}[.][0-9]*)$");
                    // 判断data[j]是否为百分数：最后一列是百分数
                    if (j == data.length - 1) {
                        isDecimal = false;
                        isPercent = true;
                    } else {
                        isPercent = false;
                    }
                }
                if (isDate) {
                    cell.setCellStyle(dateStyle);
                    cell.setCellValue(new Date(((Date) data[j]).getTime()));
                    sheet.setColumnWidth(j + 1, 12 * 256);
                } else if (isInteger) {
                    cell.setCellStyle(integerStyle);
                    cell.setCellValue(Integer.parseInt(String.valueOf(data[j])));
                } else if (isDecimal) {
                    cell.setCellStyle(decimalStyle);
                    cell.setCellValue(Double.parseDouble(String.valueOf(data[j])));
                } else if (isPercent) {
                    cell.setCellStyle(percentStyle);
                    cell.setCellValue(Double.parseDouble(String.valueOf(data[j])) / 100);
                } else {
                    cell.setCellStyle(textStyle);
                    cell.setCellValue(data[j] == null ? "" : String.valueOf(data[j]));
                }
            }
            rowNum--;
        }
        return workbook;
    }

    /**
     * 将 workbook 写入输出流中
     *
     * @param workbook Excel工作簿
     * @param out      输出流
     */
    public static void writeToOutputStream(XSSFWorkbook workbook, OutputStream out) {
        // step5 写入输出流中
        try {
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
