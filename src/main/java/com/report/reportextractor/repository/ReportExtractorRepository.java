package com.report.reportextractor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.report.reportextractor.modal.ReportData;

@Repository
public interface ReportExtractorRepository extends JpaRepository<ReportData, Long>{

}
