package com.report.reportextractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ReportExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportExtractorApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ReportExtractorApplication.class);
	}
}
