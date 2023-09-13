package com.app.blog.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@Column(name="post_title", length=100, nullable = false)
	private String title;
	
	@Column(name = "post_content",length = 1000,nullable = false)
	private String content;
	
	@Column(name="post_image", length = 20)
	private String imageName;
	
	@Column(name="date_of_post")
	private Date addDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	private User user;
}
