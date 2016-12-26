package com.mindtree.cms.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.cms.controller.BlogCommentsVO;
import com.mindtree.cms.controller.BlogVO;
import com.mindtree.cms.service.BlogService;

/**
 * @author Anurag Srivastava
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

	@Autowired
	BlogService blogService;

	/**
	 * 
	 */
	@Test
	public void findAll() {
		List<BlogVO> list = blogService.findAll();
		assertEquals(list.size(), 0);

	}

	@Test
	public void findAllBlogFo() {
		BlogVO blogVO = blogService.findAllBlogFo(15);
		assertNull(blogVO);
	}

	@Test(expected = Exception.class)
	public void saveBlogComments() throws Exception {
		BlogCommentsVO blogCommentsVO = new BlogCommentsVO();
		blogCommentsVO.setBlogId(50);
		blogCommentsVO.setUserName("test");
		blogCommentsVO.setComment("test");
		blogService.saveBlogComments(blogCommentsVO);

	}

}
