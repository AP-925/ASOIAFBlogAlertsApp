package com.notifierapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notifierapp.Entity.SubscriberVO;
import com.notifierapp.Service.SubscriberService;

@RestController
@RequestMapping("asoiaf")
public class SubscriberController {

	@Autowired
	private SubscriberService subService;

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

}
