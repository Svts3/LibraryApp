package com.example.libraryapp.service.strategy;

import com.example.libraryapp.service.strategy.Report;

public interface ReportService {
    Report makeReport(Long userId, String reportType);
}
