package com.hkta.educentresystem.dto;

public class CentreDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5422955536240948353L;

	private Long id;

	private String institutionPic;

	private String schoolName;

	private String schoolAddress;
	
	private String picMobile;
	
	private String schoolPhone;
	
	private String schoolFax;

	private String email;

	private String website;

	private String bankName;

	private String accountName;

	private String accountNumber;

	private String couponCode;

	private String discountComissionPdfUrl;
	
	private String discountComissionPdfFileName;
	
	private byte[] logo;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getInstitutionPic() {
		return institutionPic;
	}

	public void setInstitutionPic(String institutionPic) {
		this.institutionPic = institutionPic;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getPicMobile() {
		return picMobile;
	}

	public void setPicMobile(String picMobile) {
		this.picMobile = picMobile;
	}

	public String getSchoolPhone() {
		return schoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	public String getSchoolFax() {
		return schoolFax;
	}

	public void setSchoolFax(String schoolFax) {
		this.schoolFax = schoolFax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getDiscountComissionPdfFileName() {
		return discountComissionPdfFileName;
	}

	public void setDiscountComissionPdfFileName(String discountComissionPdfFileName) {
		this.discountComissionPdfFileName = discountComissionPdfFileName;
	}

	public String getDiscountComissionPdfUrl() {
		return discountComissionPdfUrl;
	}

	public void setDiscountComissionPdfUrl(String discountComissionPdfUrl) {
		this.discountComissionPdfUrl = discountComissionPdfUrl;
	}
	
}
