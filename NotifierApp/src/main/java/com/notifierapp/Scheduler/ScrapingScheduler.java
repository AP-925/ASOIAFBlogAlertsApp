package com.notifierapp.Scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.notifierapp.Constants.NotifierConstants;
import com.notifierapp.Service.EmailNotificationService;
import com.notifierapp.Service.ScrapingService;

import jakarta.annotation.PostConstruct;

@Service
public class ScrapingScheduler {

	private static final Logger LOGGER = Logger.getLogger(NotifierConstants.SCHEDULER_LOG_PLACEHOLDER);

	@Autowired
	private ScrapingService scrapingService;
	
	@Autowired
	private EmailNotificationService notificationService;

	@Value("${scheduling.time}")
	public String schedulingTime;

	@PostConstruct
	public void init() {
		LOGGER.info("Started init after loading ScrapingScheduler");
	}

	@Scheduled(fixedRateString = "${scheduling.time}", timeUnit = TimeUnit.HOURS)
	public void scrapeResource() {
		LOGGER.info(LocalDateTime.now() + ":" + " Triggering scraping api to fetch blog site data");
		boolean newBlogPostToday = scrapingService.isNewBlogPostToday();

		if (newBlogPostToday) {
			LOGGER.info("New blog post observed today. Sending notifications..");
			notificationService.sendNotifications(scrapingService.getCurrentDateAsString());
			LOGGER.info("Notifications sent to all subscribers successfully!");
		} else {
			LOGGER.info("Retrying after " + schedulingTime + " hrs..");
		}
	}
}
