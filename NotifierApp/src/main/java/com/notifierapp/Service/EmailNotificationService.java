package com.notifierapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.notifierapp.Constants.NotifierConstants;
import com.notifierapp.Entity.SubscriberVO;
import com.notifierapp.Repository.SubscriberRepository;
import com.notifierapp.Util.DateUtil;

@Service
public class EmailNotificationService {

	@Autowired
	private SubscriberRepository subRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private DateUtil dateUtil;

	@Value("${blog.url}")
	public String blogUrl;

	@Value("${testing.pilotTestFlag}")
	public String pilotTestFlag;

	@Value("${testing.testID}")
	public String testID;

	private static final Logger LOGGER = Logger.getLogger(NotifierConstants.EMAIL_NOTIFICATION_SERVICE_LOG_PLACEHOLDER);

	public void sendNotifications(String date) {

		if ("Y".equalsIgnoreCase(pilotTestFlag)) {
			SubscriberVO subscriberVO = new SubscriberVO();
			subscriberVO.setEmailID(testID);
			sendMail(subscriberVO, date);
		} else {
			String todaysDateStringFormatted = dateUtil.getTodaysDateStringFormatted();
			List<SubscriberVO> subscribersList = new ArrayList<>();
			try {
				subscribersList = subRepository.findAll();
			} catch (Exception e) {
				LOGGER.info("Exception while fetching subcribers list.. " + e);
			}

			for (SubscriberVO subscriberVO : subscribersList) {

				// Skip send notification if already sent today
				if (todaysDateStringFormatted.equalsIgnoreCase(subscriberVO.getLatestNotifyDate())) {
					continue;
				}

				sendMail(subscriberVO, date);
			}
		}
	}

	private void sendMail(SubscriberVO subscriberVO, String date) {

		String messageBody = "GRRM has posted a new blog on " + date + "\n" + blogUrl + "\n\n" + "Regards," + "\n"
				+ "Bastard of Winterfell";

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("ASOIAFBlogAlerts");
			message.setTo(subscriberVO.getEmailID());
			message.setText(messageBody);
			message.setSubject("Notification - GRRM's ASOIAF Not a blog site");
			mailSender.send(message);
		} catch (Exception e) {
			LOGGER.info("Exception while sending asoiaf email notification.. " + e);
		}
		LOGGER.info("Email sent successfully to " + subscriberVO.getEmailID());

		updateNotifyDate(subscriberVO);
	}

	private void updateNotifyDate(SubscriberVO subscriberVO) {
		subscriberVO.setLatestNotifyDate(dateUtil.getTodaysDateStringFormatted());
		try {
			subRepository.save(subscriberVO);
		} catch (Exception e) {
			LOGGER.info("Exception while updating notification date : " + e);
		}
	}
}
