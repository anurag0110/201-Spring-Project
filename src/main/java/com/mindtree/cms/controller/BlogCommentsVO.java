package com.mindtree.cms.controller;

/**
 * @author Anurag Srivastava
 *
 */
public class BlogCommentsVO {

	private String userName;
	private String comment;
	private int blogId;

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return blogId
	 */
	public int getBlogId() {
		return blogId;
	}

	/**
	 * @param blogId
	 */
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

}
