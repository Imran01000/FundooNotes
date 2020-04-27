package com.ammu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_notes")
public class NotesModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID" , unique = true , nullable = false , updatable = false)
	private long id;
	
	@Column(name = "TITLE" , nullable = true , updatable = true)
	private String title;
	@Column(name = "DESCRIPTION" , nullable = true , updatable = true)
	private String description;
	
	//DEFINE SETTER AND GETTER.
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
