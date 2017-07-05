package com.hkta.educentresystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorial_centre")
public class TutorialCenter extends AuditData{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "institution_pic")
	private String institutionPic;

	@Column(name = "school_name")
	private String schoolName;

	@Column(name = "school_address")
	private String schoolAddress;
	
	@Column(name = "pic_mobile")
	private String picMobile;

	@Column(name = "school_phone")
	private String schoolPhone;
	
	@Column(name = "school_fax")
	private String schoolFax;

	@Column(name = "email")
	private String email;

	@Column(name = "website")
	private String website;

	@Column(name = "bankName")
	private String bank_name;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "coupon_code")
	private String couponCode;

	@Column(name = "discount_comission_pdf")
	private String discountComissionPdf;

	public Long getId() {
		return id;
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

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
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
	
	
}
