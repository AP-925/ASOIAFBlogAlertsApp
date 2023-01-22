package com.notifierapp.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.notifierapp.Constants.NotifierConstants;

@Component
public class DateUtil {

	public String getTodaysDateStringFormatted() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(NotifierConstants.DATE_FORMAT);
		return dtf.format(LocalDate.now());
	}
}
