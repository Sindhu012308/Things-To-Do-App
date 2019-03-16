package com.sindhu.springboot.web.springboot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
@Entity
public class Todo {
	
	@Id
	@GeneratedValue
	private int id;
	private String user;
	// @Size is used to set minimum or max number of characters to be in that field and show the error message if not given 
	@Size(min=10, message="Enter atleast 10 characters")
	private String desc;
	private Date targetDate;
	private boolean isDone;

	public Todo() {
		
	}
	
	public Todo(int id, String user , String desc, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", user=" + user + ", desc=" + desc + ", targetDate=" + targetDate + ", isDone="
				+ isDone + "]";
	}

}
