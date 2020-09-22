package com.beogotech.athenea.util;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * TimeAgoUtil encapsulates utility methods and data to calculate relative time between two dates
 * @Author beogotech
 */
public class TimeAgoUtil {
	
	public static final Map<String, Long> times = new LinkedHashMap<>();
	
	static {
		times.put("year", TimeUnit.DAYS.toMillis(365));
		times.put("month", TimeUnit.DAYS.toMillis(30));
		times.put("week", TimeUnit.DAYS.toMillis(7));
		times.put("day", TimeUnit.DAYS.toMillis(1));
		times.put("hour", TimeUnit.HOURS.toMillis(1));
		times.put("minute", TimeUnit.MINUTES.toMillis(1));
		times.put("second", TimeUnit.SECONDS.toMillis(1));
	}
	
	public static String toRelative(long duration, int maxLevel) {
		StringBuilder res = new StringBuilder();
		int level = 0;
		for (Map.Entry<String, Long> time : times.entrySet()){
			long timeDelta = duration / time.getValue();
			if (timeDelta > 0){
				res.append(timeDelta)
						.append(" ")
						.append(time.getKey())
						.append(timeDelta > 1 ? "s" : "")
						.append(", ");
				duration -= time.getValue() * timeDelta;
				level++;
			}
			if (level == maxLevel){
				break;
			}
		}
		if ("".equals(res.toString())) {
			return "Just Now";
		} else {
			res.setLength(res.length() - 2);
			res.append(" ago");
			return res.toString();
		}
	}
	
	public static String toRelative(long duration) {
		return toRelative(duration, times.size());
	}
	
	public static String toRelative(Date start, Date end){
		assert start.after(end);
		return toRelative(end.getTime() - start.getTime());
	}
	
	public static String toRelative(Date start, Date end, int level){
		assert start.after(end);
		return toRelative(end.getTime() - start.getTime(), level);
	}
	
	public static LinkedHashMap<String, Integer> computeTimeAgo(Date endDate, Date startDate){
		if(endDate==null || startDate==null){
			return null;
		}
		LinkedHashMap<String, Integer> timeAgo = new LinkedHashMap<>();
		long duration = endDate.getTime() - startDate.getTime();
		int level = 0;
		int maxLevel = times.size();
		for(Map.Entry<String, Long> time: times.entrySet()){
			Long timeDelta = duration / time.getValue();
			if(timeDelta > 0){
				timeAgo.put(time.getKey(),Integer.valueOf(timeDelta.intValue()));
				System.out.println("\n"+time.getKey()+"  :"+timeDelta);
				duration -= time.getValue() * timeDelta;
				level++;
			}
			if(level==maxLevel){
				break;
			}
		}
		return timeAgo;
	}
	
	public static String computeTimeBetween(Date start, Date end, Locale locale) {
		
		StringBuilder sb = new StringBuilder();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		long duration = 0L;
		boolean past = end.getTime() >= start.getTime();
		Map<String,Long> times =  new LinkedHashMap<>();
		times.put("ui.dashboard.header.notification.timeago.year",TimeUnit.DAYS.toMillis(365));
		times.put("ui.dashboard.header.notification.timeago.month",TimeUnit.DAYS.toMillis(30));
		times.put("ui.dashboard.header.notification.timeago.week",TimeUnit.DAYS.toMillis(7));
		times.put("ui.dashboard.header.notification.timeago.day",TimeUnit.DAYS.toMillis(1));
		times.put("ui.dashboard.header.notification.timeago.hour",TimeUnit.HOURS.toMillis(1));
		times.put("ui.dashboard.header.notification.timeago.minute",TimeUnit.MINUTES.toMillis(1));
		times.put("ui.dashboard.header.notification.timeago.second",TimeUnit.SECONDS.toMillis(1));
		
		
		if(past){
			duration = end.getTime() - start.getTime();
			if(Locale.FRENCH.equals(locale)){
				sb.append(bundle.getString("ui.dashboard.header.notification.timeago.ago"));
			}
		}else{
			duration = start.getTime() - end.getTime();
			sb.append(bundle.getString("x.within"));
		}
		sb.append(" ");
		
		
		for (Map.Entry<String, Long> current:times.entrySet()) {
			
			long delta = duration / current.getValue();
			if(delta >=1){
				duration -= current.getValue() * delta;
				sb.append(delta)
						.append(" ")
						.append(bundle.getString(current.getKey()));
				
				if(!(Locale.FRENCH.equals(locale) && current.getKey().equalsIgnoreCase("ui.dashboard.header.notification.timeago.month"))){
					sb.append(delta != 1 ? "s" : "");
				}
				
				sb.append(" ");
				break;
			}
			
		}
		
		if(Locale.ENGLISH.equals(locale)){
			if(past){
				sb.append(bundle.getString("ui.dashboard.header.notification.timeago.ago"));
			}
		}
		if("".equals(sb.toString())){
			sb.append(bundle.getString("ui.dashboard.header.notification.timeago.just.now"));
		}
		
		return sb.toString();
	}
}
