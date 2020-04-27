package com.ammu.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class LabelDto
{
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 2)
	private String labelName;
	
	private long userId;

	//DEFINE SETTER AND GETTER.
	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
