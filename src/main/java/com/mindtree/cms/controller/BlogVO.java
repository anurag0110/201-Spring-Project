package com.mindtree.cms.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Anurag Srivastava
 *
 */
public class BlogVO {

	@NotEmpty(message = "Please enter Blog Tittle")
	private String title;

	@NotEmpty(message = "Please enter Blog Content")
	private String description;
	private String comments;
	private String userName;
	private MultipartFile file;
	private String imageSrc;
	private int blogId;
	private List<BlogCommentsVO> blogCommentsVO;
	private int count;

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public List<BlogCommentsVO> getBlogCommentsFo() {
		return blogCommentsVO;
	}

	public void setBlogCommentsFo(List<BlogCommentsVO> blogCommentsVO) {
		this.blogCommentsVO = blogCommentsVO;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
