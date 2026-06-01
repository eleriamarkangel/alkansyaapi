package com.alkansya.api.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "bank_customers")
@Data
public class BankCustomer {
	@Id
	@Column(name = "customer_number", nullable = false)
    private String customerNumber;
	
	@Column(name = "cst_firstname", nullable = false)
    private String cFirstName;
	
	@Column(name = "cst_lastname", nullable = false)
    private String cLastName;
	
	@Column(name = "mobile_nbr", nullable = false)
    private String mobileNbr;
	
	@Column(name = "bday", nullable = false)
    private String birthday;
	
	@Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;
	
	@Column(name = "date_last_updated", nullable = false)
    private LocalDateTime dateLastUpdated;
	
	@Column(name = "modified_by", nullable = false)
    private String modifiedBy;
	
	@Column(name = "is_active", nullable = false)
	@JsonProperty("is_active")
    private String isActive;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getcFirstName() {
		return cFirstName;
	}

	public void setcFirstName(String cFirstName) {
		this.cFirstName = cFirstName;
	}

	public String getcLastName() {
		return cLastName;
	}

	public void setcLastName(String cLastName) {
		this.cLastName = cLastName;
	}

	public String getMobileNbr() {
		return mobileNbr;
	}

	public void setMobileNbr(String mobileNbr) {
		this.mobileNbr = mobileNbr;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime localDateTime) {
		this.dateCreated = localDateTime;
	}

	public LocalDateTime getDateLastUpdated() {
		return dateLastUpdated;
	}

	public void setDateLastUpdated(LocalDateTime localDateTime) {
		this.dateLastUpdated = localDateTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@JsonProperty("is_active")
	public String isActive() {
		return isActive;
	}

	public void setActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BankCustomer [customerNumber=" + customerNumber + ", cFirstName=" + cFirstName + ", cLastName="
				+ cLastName + ", mobileNbr=" + mobileNbr + ", birthday=" + birthday + ", dateCreated=" + dateCreated
				+ ", dateLastUpdated=" + dateLastUpdated + ", modifiedBy=" + modifiedBy + "]";
	}
	
	
}
