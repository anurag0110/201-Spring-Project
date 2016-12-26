/**
 * 
 */
package com.mindtree.cms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.cms.controller.BlogCommentsVO;
import com.mindtree.cms.controller.BlogVO;
import com.mindtree.cms.dao.BlogDao;
import com.mindtree.cms.model.BlogComments;
import com.mindtree.cms.model.BlogContent;

/**
 * @author Anurag Srivastava
 *
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogDao blogDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.cms.service.BlogService#saveBlog(com.mindtree.cms.controller
	 * .BlogVO)
	 */
	@Transactional
	public void saveBlog(BlogVO blogVO) throws IOException {
		BlogContent blogContent = new BlogContent();
		blogContent.setName(blogVO.getFile().getOriginalFilename());
		blogContent.setTittle(blogVO
				.getFile()
				.getOriginalFilename()
				.substring(0,
						blogVO.getFile().getOriginalFilename().indexOf(".")));
		blogContent.setContent(blogVO.getDescription());
		blogContent.setName(blogVO.getFile().getOriginalFilename());
		blogContent.setFile(blogVO.getFile().getBytes());
		blogContent.setUserName("test");
		blogContent.setType(blogVO.getFile().getContentType());
		blogDao.save(blogContent);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.cms.service.BlogService#findAll()
	 */
	public List<BlogVO> findAll() {
		List<BlogVO> blogVOs = new ArrayList();
		try {
			List<BlogContent> blogContents = blogDao.findAll();
			for (BlogContent blogContent : blogContents) {
				BlogVO blogVO = new BlogVO();
				blogVO.setTitle(blogContent.getTittle());
				blogVO.setDescription(blogContent.getContent());
				byte[] encodeBase64 = Base64
						.encodeBase64(blogContent.getFile());
				String imgBase64encoded = new String(encodeBase64, "UTF-8");
				blogVO.setImageSrc(imgBase64encoded);
				List<BlogCommentsVO> commentsFos = new ArrayList();
				for (BlogComments blogComments : blogContent.getBlogComments()) {
					BlogCommentsVO blogCommentsVO = new BlogCommentsVO();
					blogCommentsVO.setComment(blogComments.getComment());
					blogCommentsVO.setUserName(blogComments.getUserName());
					commentsFos.add(blogCommentsVO);
				}
				blogVO.setBlogCommentsFo(commentsFos);
				blogVOs.add(blogVO);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return blogVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.cms.service.BlogService#findAllBlogComments(int)
	 */
	public List<BlogCommentsVO> findAllBlogComments(int blogId) {
		List<BlogCommentsVO> commentsFos = new ArrayList();
		List<BlogVO> blogVOs = new ArrayList();
		try {
			List<BlogComments> comments = blogDao.findAllBlogComments(blogId);

			for (BlogComments blogComments : comments) {
				BlogCommentsVO blogCommentsVO = new BlogCommentsVO();
				blogCommentsVO.setComment(blogComments.getComment());
				blogCommentsVO.setUserName(blogComments.getUserName());
				blogCommentsVO.setBlogId(blogComments.getBlogContent()
						.getBlogId());
				commentsFos.add(blogCommentsVO);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return commentsFos;
	}

	/**
	 * @param blogVO
	 * @throws IOException
	 */
	@Transactional
	public void saveBlogComments(BlogVO blogVO) throws IOException {
		BlogContent blogContent = new BlogContent();
		blogContent.setName(blogVO.getFile().getOriginalFilename());
		blogContent.setTittle(blogVO
				.getFile()
				.getOriginalFilename()
				.substring(0,
						blogVO.getFile().getOriginalFilename().indexOf(".")));
		blogContent.setContent(blogVO.getComments());
		blogContent.setName(blogVO.getFile().getOriginalFilename());
		blogContent.setFile(blogVO.getFile().getBytes());
		blogContent.setUserName("test");
		blogContent.setType(blogVO.getFile().getContentType());
		blogDao.save(blogContent);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.cms.service.BlogService#saveBlogComments(com.mindtree.cms
	 * .controller.BlogCommentsVO)
	 */
	@Transactional
	public void saveBlogComments(BlogCommentsVO blogCommentsVO)
			throws Exception {
		BlogComments blogComments = new BlogComments();
		BlogContent blogContent = new BlogContent();
		blogContent.setBlogId(blogCommentsVO.getBlogId());
		blogComments.setBlogContent(blogContent);
		blogComments.setUserName(blogCommentsVO.getUserName());
		blogComments.setComment(blogCommentsVO.getComment());
		blogDao.saveBlogComments(blogComments);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.cms.service.BlogService#findAllBlogFo(int)
	 */
	public BlogVO findAllBlogFo(int blogId) {
		BlogVO blogVO = null;
		try {
			BlogContent comments = blogDao.findAllBlogContent(blogId);
			List<BlogContent> blogContents = blogDao.findAll();
			if (comments != null) {
				blogVO = new BlogVO();
				blogVO.setTitle(comments.getTittle());
				blogVO.setBlogId(comments.getBlogId());
				blogVO.setCount(blogContents.size());
				blogVO.setDescription(comments.getContent());
				byte[] encodeBase64 = Base64.encodeBase64(comments.getFile());
				String imgBase64encoded = new String(encodeBase64, "UTF-8");
				blogVO.setImageSrc(imgBase64encoded);
				List<BlogCommentsVO> commentsFos = new ArrayList<BlogCommentsVO>();
				for (BlogComments blogComments : comments.getBlogComments()) {
					BlogCommentsVO blogCommentsVO = new BlogCommentsVO();
					blogCommentsVO.setComment(blogComments.getComment());
					blogCommentsVO.setUserName(blogComments.getUserName());
					blogCommentsVO.setBlogId(comments.getBlogId());
					commentsFos.add(blogCommentsVO);
					blogVO.setBlogCommentsFo(commentsFos);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return blogVO;
	}

}
