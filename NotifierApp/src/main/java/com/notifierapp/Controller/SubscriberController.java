package com.notifierapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notifierapp.Entity.SubscriberVO;
import com.notifierapp.Service.ScrapingService;
import com.notifierapp.Service.SubscriberService;
import com.notifierapp.Util.DateUtil;
import com.notifierapp.valueobjects.BlogPostResponseVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("asoiaf")
public class SubscriberController {

	@Autowired
	private SubscriberService subService;
	
	@Autowired
	private ScrapingService scrapingService;
	
	@Autowired
	private DateUtil util;

	@PostMapping(value = "grrm/not-a-blog/subscribe")
	public String subscribeToGrrmBlog(@RequestParam String emailID) {
		SubscriberVO subscriber = new SubscriberVO();
		subscriber.setEmailID(emailID);
		return subService.addSubscriber(subscriber);
	}

	@PostMapping(value = "grrm/not-a-blog/unsubscribe")
	public String unsubscribeFromGrrmBlog(@RequestParam String emailID) {
		SubscriberVO subscriber = new SubscriberVO();
		subscriber.setEmailID(emailID);
		return subService.removeSubscriber(subscriber);
	}

	@GetMapping(value = "grrm/not-a-blog/subscribers/list")
	public List<SubscriberVO> getAllSubscribers() {
		return subService.readSubscribers();
	}
	
	@GetMapping(value = "grrm/not-a-blog/getBlogData")
	public ResponseEntity<BlogPostResponseVO> getBlogData(HttpServletRequest request) {
		
		BlogPostResponseVO response = new BlogPostResponseVO();
		String blogData = scrapingService.retrieveBlogData();
		boolean isNewBlogPostToday = scrapingService.compareBlogDates(blogData, util.getCurrentDateAsString());
		response.setNewBlogPostToday(isNewBlogPostToday);
		response.setRecentBlogDate(blogData);
		
		return new ResponseEntity<BlogPostResponseVO>(response, HttpStatus.OK);
	}

}
