package com.report.reportextractor.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="report_extractor")
public class ReportData {

	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String schemeName;
	private String schemePeriod;
	private String sNo;
	private String payoutProductGroup;
	private String schemePayout;
	private String totalPayout;
	private String productGroupName;
	private String SIELContribution;
	private String dearlerContribution;
	private String total;
	
	public ReportData() {
		
	}
	
	
	public ReportData(String schemeName, String schemePeriod, String sNo, String payoutProductGroup,
			String schemePayout, String totalPayout, String productGroupName, String sIELContribution,
			String dearlerContribution, String total) {
		super();
		this.schemeName = schemeName;
		this.schemePeriod = schemePeriod;
		this.sNo = sNo;
		this.payoutProductGroup = payoutProductGroup;
		this.schemePayout = schemePayout;
		this.totalPayout = totalPayout;
		this.productGroupName = productGroupName;
		SIELContribution = sIELContribution;
		this.dearlerContribution = dearlerContribution;
		this.total = total;
	}

	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getSchemePeriod() {
		return schemePeriod;
	}
	public void setSchemePeriod(String schemePeriod) {
		this.schemePeriod = schemePeriod;
	}
	public String getsNo() {
		return sNo;
	}
	public void setsNo(String sNo) {
		this.sNo = sNo;
	}
	public String getPayoutProductGroup() {
		return payoutProductGroup;
	}
	public void setPayoutProductGroup(String payoutProductGroup) {
		this.payoutProductGroup = payoutProductGroup;
	}
	public String getSchemePayout() {
		return schemePayout;
	}
	public void setSchemePayout(String schemePayout) {
		this.schemePayout = schemePayout;
	}
	public String getTotalPayout() {
		return totalPayout;
	}
	public void setTotalPayout(String totalPayout) {
		this.totalPayout = totalPayout;
	}
	public String getProductGroupName() {
		return productGroupName;
	}
	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}
	public String getSIELContribution() {
		return SIELContribution;
	}
	public void setSIELContribution(String sIELContribution) {
		SIELContribution = sIELContribution;
	}
	public String getDearlerContribution() {
		return dearlerContribution;
	}
	public void setDearlerContribution(String dearlerContribution) {
		this.dearlerContribution = dearlerContribution;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	@Override
    public String toString() {
        return "ReportData{" +
                "schemeName='" + schemeName + '\'' +
                ", schemePeriod='" + schemePeriod + '\'' +
                ", sNo='" + sNo + '\'' +
                ", payoutProductGroup='" + payoutProductGroup + '\'' +
                ", schemePayout='" + schemePayout + '\'' +
                ", totalPayout='" + totalPayout + '\'' +
                ", productGroupName='" + productGroupName + '\'' +
                ", SIELContribution='" + SIELContribution + '\'' +
                ", dealerContribution='" + dearlerContribution + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
