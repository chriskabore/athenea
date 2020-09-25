package com.beogotech.athenea.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * AtheneaUtil encapsulates utility methods and constants data
 * @author beogotech
 */
public class AtheneaUtil {
	
	
	/** Date and calendar utility information */
	public static int COPYRIGHT_YEAR_INT= Calendar.getInstance().get(Calendar.YEAR);
	
	private static final long YEAR_IN_MILLIS = TimeUnit.DAYS.toMillis(365);
	private static final long MONTH_IN_MILLIS = TimeUnit.DAYS.toMillis(30);
	private static final long WEEK_IN_MILLIS = TimeUnit.DAYS.toMillis(7);
	private static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);
	private static final long HOUR_IN_MILLIS = TimeUnit.HOURS.toMillis(1);
	private static final long MINUTE_IN_MILLIS = TimeUnit.MINUTES.toMillis(1);
	private static final long SECOND_IN_MILLIS = TimeUnit.SECONDS.toMillis(1);
	
	
	
	
	
	/** Constants */
	
	/** MVC REQUEST URIS  && URLS ***/
	public static final String APP_ROOT_URI = "/";
	public static final String LOGIN_PAGE_URI = "content/login";
	public static final String HOME_PAGE_URI = "/home";
	public static final String INDEX_PAGE_URI = "/index";
}
