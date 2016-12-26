/**
 * 
 */
package com.mindtree.cms.service;

import java.io.IOException;
import java.util.List;

import com.mindtree.cms.controller.BlogCommentsVO;
import com.mindtree.cms.controller.BlogVO;

/**
 * @author Anurag Srivastava
 *
 */
public interface BlogService {
	/**
	 * @param blogVO
	 * @throws IOException
	 */
	public void saveBlog(BlogVO blogVO) throws IOException;

	/**
	 * @return
	 */
	public List<BlogVO> findAll();

	/**
	 * @param blogId
	 * @return
	 */
	public List<BlogCommentsVO> findAllBlogComments(int blogId);

	/**
	 * @param blogCommentsVO
	 * @throws Exception
	 */
	public void saveBlogComments(BlogCommentsVO blogCommentsVO)
			throws Exception;

	/**
	 * @param blogId
	 * @return
	 */
	public BlogVO findAllBlogFo(int blogId);

}
