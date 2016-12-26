/**
 * 
 */
package com.mindtree.cms.dao;

import java.util.List;

import com.mindtree.cms.model.BlogComments;
import com.mindtree.cms.model.BlogContent;

/**
 * @author Anurag Srivastava
 *
 */
public interface BlogDao {

	/**
	 * @param blogContent
	 */
	public void save(BlogContent blogContent);

	/**
	 * @return
	 */
	public List<BlogContent> findAll();

	/**
	 * @param blogId
	 * @return
	 */
	public List<BlogComments> findAllBlogComments(int blogId);

	/**
	 * @param blogComments
	 * @throws Exception
	 */
	public void saveBlogComments(BlogComments blogComments) throws Exception;

	/**
	 * @param blogId
	 * @return
	 */
	public BlogContent findAllBlogContent(int blogId);
}
