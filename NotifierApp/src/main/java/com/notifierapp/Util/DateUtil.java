package com.notifierapp.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.notifierapp.Constants.NotifierConstants;

@Component
public class DateUtil {

	public String getTodaysDateStringFormatted() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(NotifierConstants.DATE_FORMAT);
		return dtf.format(LocalDate.now());
	}
	
	public String getCurrentDateAsString() {
		return StringUtils.capitalize(LocalDateTime.now().getMonth().toString().toLowerCase()) + " "
				+ LocalDateTime.now().getDayOfMonth();
	}
}
