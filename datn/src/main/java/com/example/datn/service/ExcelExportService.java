package com.example.datn.service;
import com.example.datn.entity.KhachHang;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {
    public ByteArrayOutputStream exportKhachHangToExcel(List<KhachHang> khachHangList) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("KhachHang");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Họ tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Trạng thái"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // Create data rows
            int rowIndex = 1;
            for (KhachHang khachHang : khachHangList) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(khachHang.getKhachHangId());
                row.createCell(1).setCellValue(khachHang.getHoTen());
                row.createCell(2).setCellValue(khachHang.isGioiTinh() ? "Nam" : "Nữ");
                row.createCell(3).setCellValue(khachHang.getNgaySinh().toString());
                row.createCell(4).setCellValue(khachHang.getSdt());
                row.createCell(5).setCellValue(khachHang.getEmail());
                row.createCell(6).setCellValue(khachHang.isTrangThai() ? "Không hoạt động" : " Hoạt động");
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream;
        }
    }

    private CellStyle createHeaderCellStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
}
