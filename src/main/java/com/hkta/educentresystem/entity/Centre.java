package com.hkta.educentresystem.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "tutorial_centre")
public class Centre extends AuditData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5422955536240948353L;

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

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "coupon_code")
	private String couponCode;

	@Column(name = "discount_comission_pdf")
	private String discountComissionPdf;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tutorialCentre")
	private List<Transaction> transactions;
	
	@Formula("(select sum(t.commission) from transaction_record t where t.tutorial_centre_id = id)")
	private BigDecimal grandTotal;
	
	@Column(name = "logo")
	private byte[] logo;
	
	@Column(name = "has_logo")
	private Boolean hasLogo;

	@PreUpdate
    public void onPreUpdate() {
		super.onPreUpdate();
		this.hasLogo = logo.length > 0;
    }
	
	@PrePersist
	public void onPrePersist() {
		super.onPrePersist();
		this.hasLogo = logo.length > 0;
	}
	
	public Long getId() {
		return id;
	}
	
	public Boolean isHasLogo() {
		return hasLogo;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
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
	
	
}
