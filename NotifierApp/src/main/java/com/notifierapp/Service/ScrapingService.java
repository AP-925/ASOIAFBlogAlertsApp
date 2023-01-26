package com.notifierapp.Service;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.notifierapp.Constants.NotifierConstants;
import com.notifierapp.Helper.SSLHelper;
import com.notifierapp.Util.DateUtil;

@Service
public class ScrapingService {

	private static final Logger LOGGER = Logger.getLogger(NotifierConstants.SCRAPER_LOG_PLACEHOLDER);

	@Value("${blog.url}")
	public String blogUrl;

	@Value("${testing.pilotTestFlag}")
	public String pilotTestFlag;

	@Value("${testing.testDate}")
	public String testDateFlag;
	
	@Autowired
	private DateUtil util;

	public boolean isNewBlogPostToday() {

		LOGGER.info("Start - isNewBlogPostToday");
		String data = retrieveBlogData();

		if (StringUtils.isNotBlank(data) && !"failed".equalsIgnoreCase(data)) {
			String todayDateString = util.getCurrentDateAsString();
			boolean containsTodaysDate = false;

			if ("Y".equalsIgnoreCase(pilotTestFlag)) {
				containsTodaysDate = data.contains(testDateFlag); // added for testing only
			} else {
				containsTodaysDate = data.contains(todayDateString);
			}
			LOGGER.info("Today's date : " + todayDateString + " " + LocalDate.now().getYear());
			LOGGER.info("End - Is new blog posted on today's date : " + containsTodaysDate);
			return containsTodaysDate;
		} else {
			return false;
		}
	}

	public String retrieveBlogData() {
		LOGGER.info("Start - retrieveBlogData");
		Document blogData = null;
		String scrapedData = "";
		try {
			Connection connection = Jsoup.connect(blogUrl).timeout(10000).sslSocketFactory(SSLHelper.socketFactory());
			blogData = connection.get();
		} catch (Exception e) {
			LOGGER.info("Exception in retrieveBlogData : {}" + e);
		}

		if (blogData != null) {
			Elements elementsByClass = blogData.getElementsByClass(NotifierConstants.CLASS_TO_SEARCH);
			Element element = elementsByClass != null ? elementsByClass.get(0) : null;
			scrapedData = element != null ? element.toString() : "failed";
		}

		LOGGER.info("End - retrieveBlogData");
		return scrapedData;
	}
	
	public boolean compareBlogDates(String data, String todayDateString) {
		if ("Y".equalsIgnoreCase(pilotTestFlag)) {
			return data.contains(testDateFlag); // added for testing only
		} else {
			return data.contains(todayDateString);
		}
	}

}
