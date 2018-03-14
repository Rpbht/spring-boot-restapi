package com.cignex.rahul.DemoApp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_tbl")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9100282719055357149L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uId;
	private String uEmail;
	private String uPassword;
	private long uNumber;
	
	@OneToMany(mappedBy="user")
	private List<Order> order;
	
	public User() {
		super();
	}

	public User(String uEmail, String uPassword, long uNumber) {
		super();
		this.uEmail = uEmail;
		this.uPassword = uPassword;
		this.uNumber = uNumber;
	}

	public User(long uId, String uEmail, String uPassword, long uNumber) {
		super();
		this.uId = uId;
		this.uEmail = uEmail;
		this.uPassword = uPassword;
		this.uNumber = uNumber;
	}

	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public long getuNumber() {
		return uNumber;
	}

	public void setuNumber(long uNumber) {
		this.uNumber = uNumber;
	}
	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uEmail=" + uEmail + ", uPassword=" + uPassword + ", uNumber=" + uNumber + "]";
	}
}
