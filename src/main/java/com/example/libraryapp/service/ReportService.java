package com.example.libraryapp.service;

import com.example.libraryapp.model.interfaces.Report;

public interface ReportService {
    Report makeReport(Long userId, String reportType);
}
