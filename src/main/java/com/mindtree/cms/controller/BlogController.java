/**
 * 
 */
package com.mindtree.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mindtree.cms.service.BlogService;

/**
 * @author Anurag Srivastava
 *
 */
@Controller
public class BlogController {

	@Autowired
	BlogService blogService;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/")
	public String loginPage(ModelMap model) {
		model.addAttribute("loginFo", new LoginVO());
		return "userSubmit";

	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String userLogin(ModelMap model) {
		model.addAttribute("loginFo", new LoginVO());
		return "userSubmit";
	}

	/**
	 * @param model
	 * @param loginVO
	 * @param result
	 * @param guestUserName
	 * @param blogId
	 * @return
	 */
	@RequestMapping(value = "/viewBlog", method = RequestMethod.GET)
	public String viewBlog(
			Model model,
			@Valid @ModelAttribute LoginVO loginVO,
			BindingResult result,
			@RequestParam(value = "guestUserName", required = false) String guestUserName,
			@RequestParam(value = "blogId", defaultValue = "1", required = false) int blogId) {
		if (result.hasErrors()) {
			return "userSubmit";
		}
		BlogVO blogVO = blogService.findAllBlogFo(blogId);
		model.addAttribute("blogFo", blogVO);
		model.addAttribute("blogCommentsFo", new BlogCommentsVO());
		model.addAttribute("guestUserName", loginVO.getGuestUserName());
		if (blogVO != null) {
			model.addAttribute("blogId", blogVO.getBlogId());
		}
		return "viewblog";

	}

	/**
	 * @param blogCommentsVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveBlogComments", method = RequestMethod.POST)
	public @ResponseBody List<BlogCommentsVO> saveBlogComments(
			@RequestBody BlogCommentsVO blogCommentsVO,
			HttpServletRequest request, HttpServletResponse response) {
		List<BlogCommentsVO> blogCommentsVOs = null;
		try {
			if (blogCommentsVO.getComment() != null
					&& !blogCommentsVO.getComment().isEmpty())
				blogService.saveBlogComments(blogCommentsVO);
			blogCommentsVOs = blogService.findAllBlogComments(blogCommentsVO
					.getBlogId());
		} catch (Exception ex) {

		}
		return blogCommentsVOs;

	}

	/**
	 * @param model
	 * @param loginVO
	 * @param guestUserName
	 * @param blogId
	 * @return
	 */
	@RequestMapping(value = "/viewBlogForAdmin", method = RequestMethod.GET)
	public String viewBlogForAdmin(
			Model model,
			@ModelAttribute LoginVO loginVO,
			@RequestParam(value = "guestUserName", required = false) String guestUserName,
			@RequestParam(value = "blogId", required = false) int blogId) {
		BlogVO blogVO = blogService.findAllBlogFo(blogId);
		if (blogVO != null) {
			model.addAttribute("blogFo", blogVO);
			model.addAttribute("blogCommentsFo", new BlogCommentsVO());
			model.addAttribute("guestUserName", loginVO.getGuestUserName());
			model.addAttribute("blogId", blogVO.getBlogId());
		}
		return "viewblogadmin";

	}
}
