package com.alkansya.api.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank_customers")
@Data
public class BankCustomer {
//	customer_number varchar(45) PK 
//	cst_firstname varchar(45) 
//	cst_lastname varchar(45) 
//	mobile_nbr int 
//	bday varchar(45) 
//	date_created date 
//	date_last_updated date 
//	modified_by varchar(45)
	
	@Id
	@Column(name = "customer_number", nullable = false)
    private String customerNumber;
	
	@Column(name = "cst_firstname", nullable = false)
    private String cFirstName;
	
	@Column(name = "cst_lastname", nullable = false)
    private String cLastName;
	
	@Column(name = "mobile_nbr", nullable = false)
    private int mobileNbr;
	
	@Column(name = "bday", nullable = false)
    private String birthday;
	
	@Column(name = "date_created", nullable = false)
    private String dateCreated;
	
	@Column(name = "date_last_updated", nullable = false)
    private String dateLastUpdated;
	
	@Column(name = "modified_by", nullable = false)
    private String modifiedBy;
	
	@Column(name = "is_active", nullable = false)
    private boolean isActive;

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

	public int getMobileNbr() {
		return mobileNbr;
	}

	public void setMobileNbr(int mobileNbr) {
		this.mobileNbr = mobileNbr;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateLastUpdated() {
		return dateLastUpdated;
	}

	public void setDateLastUpdated(String dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BankCustomer [customerNumber=" + customerNumber + ", cFirstName=" + cFirstName + ", cLastName="
				+ cLastName + ", mobileNbr=" + mobileNbr + ", birthday=" + birthday + ", dateCreated=" + dateCreated
				+ ", dateLastUpdated=" + dateLastUpdated + ", modifiedBy=" + modifiedBy + "]";
	}
	
	
}
