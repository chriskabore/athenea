package com.beogotech.athenea.controller;

import com.beogotech.athenea.util.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class DashboardController {
	
	private static Logger LOG = LoggerFactoryUtil.getLog(DashboardController.class);
	
	private MessageSource messageSource;
	private MessageSourceAccessor accessor;
	private Locale currentLocale;
	
	@Autowired
	public DashboardController(MessageSource messageSource){
		this.messageSource = messageSource;
		this.accessor  = new MessageSourceAccessor(this.messageSource, LocaleContextHolder.getLocale());
		this.currentLocale = LocaleContextHolder.getLocale();
	}
	
	@GetMapping(value = "/dashboard")
	public String showIndexPage(HttpServletRequest request, Model model){
		int rateOfBudgetSpent = 52;
		Locale requestLocale = request.getLocale();
		String budgetChart = buildBudgetChart(rateOfBudgetSpent);
		model.addAttribute("year", AtheneaUtil.COPYRIGHT_YEAR_INT);
		model.addAttribute("numberOfNotifications", "3");
		model.addAttribute("numberOfMessages", "2");
		model.addAttribute("totalOfBudget", "35 000 000 000 FCFA");
		model.addAttribute("budgetAvailableValue", "16 800 000 000 FCFA");
		model.addAttribute("budgetSpentValue", "18 200 000 000 FCFA");
		model.addAttribute("rateOfBudgetSpent", rateOfBudgetSpent);
		model.addAttribute("financialImplementationRate", 0.52);
		model.addAttribute("physicalImplementationRate", 0.6);
		model.addAttribute("budgetSpentChart", budgetChart);
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
		
		
		
		return "content/dashboard";
	}
	
	private String buildBudgetChart(int rateOfBudgetSpent) {
		StringBuilder sb = new StringBuilder();
		sb.append("<svg viewBox=\"0 0 40 40\" class=\"circular-chart green\">");
		sb.append("\n");
		sb.append("<path class=\"circle-bg\"\n" +
				"                                                                              d=\"M18 2.0845\n" +
				"                                                                      a 15.9155 15.9155 0 0 1 0 31.831\n" +
				"                                                                      a 15.9155 15.9155 0 0 1 0 -31.831\"\n" +
				"                                                                        />");
		sb.append("<path class=\"circle\"");
		sb.append("stroke-dasharray=\""+rateOfBudgetSpent+","+"100"+"\"");
		
		sb.append("d=\"M18 2.0845\n" +
				"                                                                      a 15.9155 15.9155 0 0 1 0 31.831\n" +
				"                                                                      a 15.9155 15.9155 0 0 1 0 -31.831\"\n" +
				"                                                                        />");
		sb.append("<text x=\"18\" y=\"16\" class=\"percentage\"");
		sb.append("th:text=\""+rateOfBudgetSpent+"%"+"\"");
		sb.append(">");
		sb.append(rateOfBudgetSpent+"%");
		sb.append("</text>");
		sb.append("<text x=\"18\" y=\"24\" class=\"percentage\">");
		sb.append(accessor.getMessage("ui.dashboard.content.spent", LocaleContextHolder.getLocale()));
		sb.append("</text>");
		sb.append("</svg>");
		return sb.toString();
	}
	
	
	
	@RequestMapping(value = "/action-plan-tree", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String getTreeData(){
		
		String actionPlanRootNode = "PA1 - PLAN D'ACTION BUDGETISE DU PAAQE 2018-2020";
		
		TreeNode<String> tree = getActionPlanTree(actionPlanRootNode);
		Gson gson = new Gson();
		String treeJsonString = gson.toJson(tree);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(treeJsonString);
		
		return sb.toString();
	}
	
	private TreeNode<String> getActionPlanTree(String actionPlanRootNode) {
		String[] actionPlanComponents = new String[] {"CMP1 - Elargissement de l’accès équitable à l'enseignement préscolaire et  à l'enseignement secondaire",
				"CMP2 - Amélioration de la qualité du processus d’enseignement et d’apprentissage",
				"CMP3 - Renforcement des capacités institutionnelles"};
		
		List<TreeNode<String>> components = new ArrayList<>();
		List<TreeNode<String>> firstComponentChildren = new ArrayList<>();
		List<TreeNode<String>> secondComponentChildren = new ArrayList<>();
		List<TreeNode<String>> thirdComponentChildren = new ArrayList<>();
		TreeNode<String> rootNode = new TreeNode<>(actionPlanRootNode);
		TreeNode<String> firstComponent = new TreeNode<>(getShorttenedName(actionPlanComponents[0]));
		TreeNode<String> secondComponent = new TreeNode<>(getShorttenedName(actionPlanComponents[1]));
		TreeNode<String> thirdComponent = new TreeNode<>(getShorttenedName(actionPlanComponents[2]));
		// subcomponents of first component
		TreeNode<String> firstSubComponent = new TreeNode<>(getShorttenedName("VOL1.1 - Amélioration de l'accès et de la qualité de l'éducation de la petite enfance à travers le pilotage d'un programme d'enseignement de l'audio interactif et certification sur piste courte"));
		TreeNode<String> secondSubComponent = new TreeNode<>(getShorttenedName("VOL1.2 - Elargir l'accès à l’enseignement secondaire"));
		firstComponentChildren.add(firstSubComponent);
		firstComponentChildren.add(secondSubComponent);
		firstComponent.addChildren(firstComponentChildren);
		
		//Budget Line of VOL1.1
		TreeNode<String> budgetLineVol1_1 = new TreeNode<>(getShorttenedName("LB1.1.1 - Développement et adoption de programmes «IAI» et «DPE»   au profit des enfants de 3-5 ans d'âge des régions de l'Est, du Centre-Est et de  la ville de Ouagadougou"));
		firstSubComponent.addChild(budgetLineVol1_1);
		//Activities of Budget Line lb 1-1-1
		List<TreeNode<String>>activitiesOfBudgetLine = new ArrayList<>();
		TreeNode<String> activityLB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.1 - Rédaction  et révision de scripts des émissions EIA en 8 sessions"));
		TreeNode<String> activity2LB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.2 - Rédaction et révision de scripts des émissions de la formation à distance (FD) en 10 sessions"));
		TreeNode<String> activity3LB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.3 - Enregistrement et montage de 48 émissions EIA"));
		TreeNode<String> activity4LB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.4 - Enregistrement et montage de 25 émissions de la formation à distance (FD)"));
		TreeNode<String> activity5LB1_1_1 = new TreeNode<>(getShorttenedName("ACT1.1.1.5 - Testing des 96 émissions EIA"));
		activitiesOfBudgetLine.add(activityLB1_1_1);
		activitiesOfBudgetLine.add(activity2LB1_1_1);
		activitiesOfBudgetLine.add(activity3LB1_1_1);
		activitiesOfBudgetLine.add(activity4LB1_1_1);
		activitiesOfBudgetLine.add(activity5LB1_1_1);
		budgetLineVol1_1.addChildren(activitiesOfBudgetLine);
		// subcomponents of second component
		TreeNode<String> thirdSubComponent = new TreeNode<>(getShorttenedName("VOL2.1 - Réforme du curriculum"));
		TreeNode<String> fourthSubComponent = new TreeNode<>(getShorttenedName("VOL2.2 - Formation initiale et continue des enseignants du secondaire"));
		TreeNode<String> fifthSubComponent = new TreeNode<>(getShorttenedName("VOL2.3 - Augmentation de la disponibilité des manuels et matériels pédagogiques"));
		TreeNode<String> sixthSubComponent = new TreeNode<>(getShorttenedName("VOL2.4 - Développement des initiatives de qualité en milieu scolaire"));
		TreeNode<String> seventhSubComponent = new TreeNode<>(getShorttenedName("VOL2.5 - Évaluation de l'apprentissage du rendement des élèves et des examens "));
		secondComponentChildren.add(thirdSubComponent);
		secondComponentChildren.add(fourthSubComponent);
		secondComponentChildren.add(fifthSubComponent);
		secondComponentChildren.add(sixthSubComponent);
		secondComponentChildren.add(seventhSubComponent);
		secondComponent.addChildren(secondComponentChildren);
		//subcomponents of component 3
		TreeNode<String> eighthSubComponent = new TreeNode<>(getShorttenedName("VOL3.1 - Planification de l'éducation et Gestion administrative"));
		TreeNode<String> ninthSubComponent = new TreeNode<>(getShorttenedName("VOL3.2 - Promotion des comités de gestion scolaire (COGES)"));
		TreeNode<String> tenthSubComponent = new TreeNode<>(getShorttenedName("VOL3.3 - Gestion de projet"));
		thirdComponentChildren.add(eighthSubComponent);
		thirdComponentChildren.add(ninthSubComponent);
		thirdComponentChildren.add(tenthSubComponent);
		thirdComponent.addChildren(thirdComponentChildren);
		
		components.add(firstComponent);
		components.add(secondComponent);
		components.add(thirdComponent);
		rootNode.addChildren(components);
		return rootNode;
	}
	
	private String getShorttenedName(String name){
		String shortenedName = "";
		if(!name.isEmpty()){
			int numberOfWords =0;
			StringTokenizer st = new StringTokenizer(name);
			numberOfWords = st.countTokens();
			if(numberOfWords>=6){
				String words = Arrays.stream(name.split("\\s+")).limit(6).collect(Collectors.joining(" "));
				shortenedName = words +"...";
			}else{
				shortenedName = name;
			}
		}
		return shortenedName;
	}
}
