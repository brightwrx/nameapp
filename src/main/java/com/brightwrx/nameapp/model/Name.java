package com.brightwrx.nameapp.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name="app_names")
public class Name{

	public Name(){}

	public Name(String firstName, String lastName, String meaning, int populartiyIndex) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.meaning = meaning;
		this.populartiyIndex = populartiyIndex;
	}

	public Name(String firstName, String lastName, Timestamp updateTime, String meaning, int populartiyIndex) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.updateTime = updateTime;
		this.meaning = meaning;
		this.populartiyIndex = populartiyIndex;
	}

	@Id
	@Getter
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@Setter
	@Getter
	@Column(nullable = false, length = 30)
	private String firstName;
	
	@Setter
	@Getter
	@Column(nullable = false, length = 30)
	private String lastName;
	
	@Setter	
	@Getter
	@Column(nullable = false)
	private Timestamp updateTime;
	
	@Setter
	@Getter
	@Column(nullable = false, length = 1000)
	private String meaning;
	
	@Setter
	@Getter
	@Column(nullable = true)
    private int populartiyIndex;
	

}