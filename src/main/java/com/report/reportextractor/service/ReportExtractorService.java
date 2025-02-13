package com.report.reportextractor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.report.reportextractor.modal.ReportData;
import com.report.reportextractor.repository.ReportExtractorRepository;

@Service
public class ReportExtractorService {

	@Autowired
	private ReportExtractorRepository reportExtractorRepository;

	 public List<ReportData> extractAndSaveData(MultipartFile file) throws IOException {
	        PDDocument document = PDDocument.load(file.getInputStream());
	        PDFTextStripper pdfStripper = new PDFTextStripper();
	        pdfStripper.setSortByPosition(true); // Ensure proper text order
	        pdfStripper.setLineSeparator("\n");
	        String text = pdfStripper.getText(document);
	        document.close();

	        // Normalize spaces & dashes
	        String normalizedText = text.replaceAll("[\\u00A0\\u2007\\u202F\\u2011]", " ");

	        System.out.println("🔍 Extracted PDF Content:\n" + normalizedText);

	        if (normalizedText.isEmpty()) {
	            System.out.println("⚠ PDF text is empty! Check the PDF format.");
	            return Collections.emptyList();
	        }

	        // Extract Scheme Name & Period
	        String schemeName = extractValue(normalizedText, "Scheme Name\\s*:\\s*(.+)");
	        String schemePeriod = extractValue(normalizedText, "Scheme Period\\s*:\\s*(.+)");

	        System.out.println("✅ schemeName: " + schemeName);
	        System.out.println("✅ schemePeriod: " + schemePeriod);

	        List<ReportData> extractedDataList = new ArrayList<>();

	        // Extract table data
	        Pattern pattern = Pattern.compile("(\\d+)\\s+([A-Z0-9-]+\\s*-\\s*S24 FE .*?)\\s+([\\d,]+)\\s+([\\d,]+)");
	        Matcher matcher = pattern.matcher(normalizedText);

	        boolean found = false;
	        while (matcher.find()) {
	            found = true;
	            String sNo = matcher.group(1);
	            String payoutProductGroup = matcher.group(2);
	            String schemePayout = matcher.group(3).replace(",", "");
	            String totalPayout = matcher.group(4).replace(",", "");

	            System.out.println("✅ Extracted Row:");
	            System.out.println("sNo: " + sNo);
	            System.out.println("payoutProductGroup: " + payoutProductGroup);
	            System.out.println("schemePayout: " + schemePayout);
	            System.out.println("totalPayout: " + totalPayout);

	            // Extract Contribution Data
	            Pattern tablePattern = Pattern.compile(
	                Pattern.quote(payoutProductGroup) + "\\s+([\\d,]+)\\s+([\\d,]+)\\s+([\\d,]+)"
	            );
	            Matcher tableMatcher = tablePattern.matcher(normalizedText);

	            String SIELContribution = "0", dealerContribution = "0", total = "0";
	            if (tableMatcher.find()) {
	                SIELContribution = tableMatcher.group(1).replace(",", "");
	                dealerContribution = tableMatcher.group(2).replace(",", "");
	                total = tableMatcher.group(3).replace(",", "");

	                System.out.println("✅ Contribution Data:");
	                System.out.println("SIELContribution: " + SIELContribution);
	                System.out.println("dealerContribution: " + dealerContribution);
	                System.out.println("total: " + total);
	            } else {
	                System.out.println("⚠ Contribution data not found for: " + payoutProductGroup);
	            }

	            ReportData extractedData = new ReportData(
	                schemeName, schemePeriod, sNo, payoutProductGroup, schemePayout,
	                totalPayout, payoutProductGroup, SIELContribution, dealerContribution, total
	            );

	            extractedDataList.add(extractedData);
	        }

	        if (!found) {
	            System.out.println("⚠ No matches found! Check PDF format or regex.");
	            return Collections.emptyList();
	        }

	        // Save to database
	        List<ReportData> savedData = reportExtractorRepository.saveAll(extractedDataList);
	        System.out.println("✅ Saved Records Count: " + savedData.size());

	        return savedData;
	    }

	    private String extractValue(String text, String regex) {
	        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(text);
	        return matcher.find() ? matcher.group(1).trim() : "Not Found";
	    }
}
