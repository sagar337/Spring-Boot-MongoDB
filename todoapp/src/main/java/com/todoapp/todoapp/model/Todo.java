package com.todoapp.todoapp.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





@Document(collection="todos")

@JsonIgnoreProperties(value= {"creatAt"},allowGetters=true)
public class Todo {


	@Id
	private String id;
	
	@NotBlank
	@Size(max=100)
	@Indexed(unique=true)
	private String title;
	private Boolean  completed;
	
	private Date creatAt = new Date();

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Todo(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
	}
	
	public String toString() {
		return String.format("Tod[id=%s,title=%s,completed=%s]",id,title,completed);
	}
}
