package com.notifierapp.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notifierapp.Constants.NotifierConstants;
import com.notifierapp.Entity.SubscriberVO;
import com.notifierapp.Repository.SubscriberRepository;

@Service
public class SubscriberService {

	@Autowired
	private SubscriberRepository subRepository;

	private static final Logger LOGGER = Logger.getLogger(NotifierConstants.SUBSRIBER_SERVICE_LOG_PLACEHOLDER);

	@Transactional
	public String addSubscriber(SubscriberVO subscriber) {
		try {
			if (!subRepository.existsByEmailID(subscriber.getEmailID())) {
				subRepository.save(subscriber);
				return "Your email ID has been added in our subcribers list. We'll notify you when there is a new blog post! Valar Dohaeris! :-)";
			} else {
				return "Your email ID is already present in our subcribers list.";
			}
		} catch (Exception e) {
			LOGGER.info("Exception while saving subscriber : " + e);
			return "Your email ID is already present in our subcribers list.";
		}
	}

	public List<SubscriberVO> readSubscribers() {
		return subRepository.findAll();
	}

	@Transactional
	public String removeSubscriber(SubscriberVO subscriber) {
		if (subRepository.existsByEmailID(subscriber.getEmailID())) {
			try {
				List<SubscriberVO> subscriberList = subRepository.findByEmailID(subscriber.getEmailID());
				subscriberList.stream().forEach(sub -> {
					subRepository.delete(sub);
				});
				return "Your email ID has been removed from our subcribers list. You will no longer receive any notifications.";
			} catch (Exception e) {
				LOGGER.info("Exception while removing subscriber : " + e);
				return "Your email ID has been removed from our subcribers list. You will no longer receive any notifications.";
			}

		} else {
			return "Your email ID is not present in our subscribers list or has been removed previously.";
		}
	}
}
