/**
 * 
 */
package com.mindtree.cms.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.cms.service.BlogService;

/**
 * @author Anurag Srivastava
 *
 */
@Controller
@RequestMapping("/secure")
public class LoginController {

	@Autowired
	private BlogService blogService;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String homePage(ModelMap model) {
		model.addAttribute("blogFo", new BlogVO());
		return "main";
	}

	/**
	 * @param model
	 * @param blogVO
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
	public String saveBlog(ModelMap model,
			@Valid @ModelAttribute("blogFo") BlogVO blogVO, BindingResult result) {
		// Get the uploaded files and store them
		MultipartFile file = blogVO.getFile();
		if (result.hasErrors()) {
			return "main";
		} else if (file.isEmpty()) {
			model.addAttribute("nofile", "Upload a file");
			return "main";
		} else if (!file.getContentType().equals("image/jpeg")
				&& !file.getContentType().equals("image/png")) {
			model.addAttribute("nofile", "Upload either jpeg or png file");
			return "main";
		}

		if (null != file) {
			String fileName = file.getOriginalFilename();
			List<BlogVO> list = null;
			try {
				blogService.saveBlog(blogVO);
				list = blogService.findAll();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("blogFo", list);
			model.addAttribute("blogFo", new BlogVO());
		}
		model.addAttribute("msg", "Added Successfully");
		return "main";

	}

}
