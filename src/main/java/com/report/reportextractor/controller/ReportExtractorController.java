package com.report.reportextractor.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.report.reportextractor.modal.ReportData;
import com.report.reportextractor.service.ReportExtractorService;

@RestController
@RequestMapping("/api/report/")
public class ReportExtractorController {

	@Autowired
	private ReportExtractorService reportExtractorService;
	
	@PostMapping("/upload")
	public ResponseEntity<List<ReportData>> extractPdfReport(@RequestParam("file") MultipartFile file){
		try {
			List<ReportData> extractedDataList = reportExtractorService.extractAndSaveData(file);
            return ResponseEntity.ok(extractedDataList);
		} catch (IOException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/greetings")
	public String hello() {
		return "Welcome to Springboot jenkins demo";
	}
}
