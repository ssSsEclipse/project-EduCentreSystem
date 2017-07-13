package com.hkta.educentresystem.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CentreDto implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3411381203336244460L;

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
	
	private String discountComissionPdf;
		
	private BigDecimal grandTotal;
	
	@JsonIgnore
	private byte[] logo;

	@JsonIgnore
	private Boolean hasLogo;

	
	public Long getId() {
		return id;
	}
	
	public Boolean isHasLogo() {
		return hasLogo;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
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

	public String getDiscountComissionPdf() {
		return discountComissionPdf;
	}

	public void setDiscountComissionPdf(String discountComissionPdf) {
		this.discountComissionPdf = discountComissionPdf;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public Boolean getHasLogo() {
		return hasLogo;
	}

	public void setHasLogo(Boolean hasLogo) {
		this.hasLogo = hasLogo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	
}
