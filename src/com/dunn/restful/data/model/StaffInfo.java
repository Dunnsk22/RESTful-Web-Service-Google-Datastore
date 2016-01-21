/**
 * 
 */
package com.dunn.restful.data.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author joshuadunn
 *
 */
@XmlRootElement
@XmlType(propOrder = { "staffID", "surname", "forename", "location", "phone", "email" })
public class StaffInfo {
	String Surname, Forename, Address, Email, Phone;
	String StaffID;
	

	public StaffInfo(String staffID, String surname, String forename, String address, String email, String phone_num) {
		super();
		Surname = surname;
		Forename = forename;
		Address = address;
		StaffID = staffID;
		Phone = phone_num;
		Email = email;
	}
	
	public StaffInfo() {
		super();
	}

	/**
	 * @return staffID
	 */
	public String getStaffID() {
		return StaffID;
	}

	public void setStaffID(String staffID) {
		StaffID = staffID;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getForename() {
		return Forename;
	}

	public void setForename(String forename) {
		Forename = forename;
	}

	public String getLocation() {
		return Address;
	}

	public void setLocation(String location) {
		Address = location;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "StaffInfo [Surname=" + Surname + ", Forename=" + Forename + ", Address=" + Address + ", Email=" + Email
				+ ", StaffID=" + StaffID + ", Phone=" + Phone + "]";
	}

}

