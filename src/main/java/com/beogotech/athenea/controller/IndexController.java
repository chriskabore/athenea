package com.beogotech.athenea.controller;

import com.beogotech.athenea.util.AtheneaUtil;
import com.beogotech.athenea.util.LoggerFactoryUtil;
import com.beogotech.athenea.util.Notification;
import com.beogotech.athenea.util.TimeAgoUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Controller
public class IndexController {
	
	private static Logger LOG = LoggerFactoryUtil.getLog(IndexController.class);
	
	@GetMapping(value = "/")
	public String showIndexPage(Model model, HttpServletRequest request){
		model.addAttribute("year", AtheneaUtil.COPYRIGHT_YEAR_INT);
		Locale requestLocale = request.getLocale();
		
		model.addAttribute("numberOfNotifications", "3");
		model.addAttribute("numberOfMessages", "2");
		model.addAttribute("totalOfBudget", "35 000 000 000 FCFA");
		model.addAttribute("budgetAvailableValue", "16 800 000 000 FCFA");
		model.addAttribute("budgetSpentValue", "18 200 000 000 FCFA");
		model.addAttribute("financialImplementationRate", 0.52);
		model.addAttribute("physicalImplementationRate", 0.6);
		model.addAttribute("actionPlanActiveOption", "PA1 - PLAN D'ACTION BUDGETISE DU PAAQE 2018-2020");
		model.addAttribute("actionPlanComponents",new String[] {"CMP001 - Elargissement de l'accès equitable...", "CMP002 - Amelioration de la qualité du processus...", "CMP003 - Renforcement de la capacité institutionnelle..."});
		
		String pattern = "dd-MM-yyyy HH:mm:ss";
		
		MessageFormat formatter = new MessageFormat(pattern, request.getLocale());
		
		model.addAttribute("activity", 10);
		long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);
		SimpleDateFormat sf = new SimpleDateFormat(pattern, request.getLocale());
		
		Date dateOfNotification1 = new Date(System.currentTimeMillis() - (7 * DAY_IN_MILLIS));
		String timeAgo1 = TimeAgoUtil.computeTimeBetween(dateOfNotification1,new Date(),requestLocale);
		Notification msgNotification1 = new Notification("Felix Yameogo","message", dateOfNotification1,timeAgo1 );
		model.addAttribute("msgNotification1", msgNotification1);
		
		long HOUR_IN_MILLIS = TimeUnit.HOURS.toMillis(1);
		long MINUTES_IN_MILLIS = TimeUnit.MINUTES.toMillis(1);
		long SECONDS_IN_MILLIS = TimeUnit.SECONDS.toMillis(1);
		
		Date dateOfNotification2 = new Date(System.currentTimeMillis() - (3 * HOUR_IN_MILLIS));
		String timeAgo2 = TimeAgoUtil.computeTimeBetween(dateOfNotification1,new Date(),requestLocale);
		Notification msgNotification2 = new Notification("Lassina Sana","message", dateOfNotification2,timeAgo2 );
		model.addAttribute("msgNotification2", msgNotification2);
		
		Date dateOfActivityNotification1 = new Date(System.currentTimeMillis() - (10 * MINUTES_IN_MILLIS));
		Date now = new Date();
		String timeAgoOfActivityNotification1 = TimeAgoUtil.computeTimeBetween(dateOfActivityNotification1,now,requestLocale);
		Notification activityNotification1 = new Notification("Felix Yameogo","activity",dateOfActivityNotification1,timeAgoOfActivityNotification1);
		model.addAttribute("activityNotification1", activityNotification1);
		
		Date dateOfActivityNotification2 = new Date(System.currentTimeMillis() - (10 * SECONDS_IN_MILLIS));
		now = new Date();
		String timeAgoOfActivityNotification2 = TimeAgoUtil.computeTimeBetween(dateOfActivityNotification2,now,requestLocale);
		Notification activityNotification2 = new Notification("Felix Yameogo","activity",dateOfActivityNotification2,timeAgoOfActivityNotification2);
		model.addAttribute("activityNotification2", activityNotification2);
		
		Date dateOfActivityNotification3 = new Date(System.currentTimeMillis() - (20 * MINUTES_IN_MILLIS));
		now = new Date();
		String timeAgoOfActivityNotification3 = TimeAgoUtil.computeTimeBetween(dateOfActivityNotification3,now,requestLocale);
		Notification activityNotification3 = new Notification("Felix Yameogo","activity",dateOfActivityNotification3,timeAgoOfActivityNotification3);
		model.addAttribute("activityNotification3", activityNotification3);
		
		return "dashboard";
	}
}
