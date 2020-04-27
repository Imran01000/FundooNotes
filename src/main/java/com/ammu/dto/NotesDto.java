package com.ammu.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class NotesDto 
{

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 2 , max = 60)
	private String title;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 2)
	private String description;
	
	private boolean isTrash;
	
	private boolean isArchive;
	
	//DEFINE SETTER AND GETTER.
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
	public boolean isTrash() {
		return isTrash;
	}
	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
	public boolean isArchive() {
		return isArchive;
	}
	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}
	
}
