package com.itg.main;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itg.graph.Graph;
import com.itg.searchmethods.ASearch;
import com.itg.searchmethods.GBS;
import com.itg.searchmethods.UCS;

@Controller
public class HomeController {
	
	
@RequestMapping("/")
public String home(Model model, HttpSession session) {
	
	return "home.jsp";
}

@RequestMapping(value = "/ucs" ,  method=RequestMethod.POST)
public String UCSearch(HttpSession session ) {
	List<List<Integer>> record = (List<List<Integer>>) session.getAttribute("records");
	System.out.println("Progress...");
	if(session.getAttribute("start") != "" && session.getAttribute("goal") != "") {
		System.out.println("searching..." + session.getAttribute("start") +"  to "+ session.getAttribute("goal"));
	int s = (int) session.getAttribute("start");
	int g = (int) session.getAttribute("goal");

		session.setAttribute("UCSdata", UCS.UCSearch(record,s ,g));
	}		
//	session.setAttribute("UCSdata", UCS.UCSearch((Graph)session.getAttribute("graph"), 1, 7));

	return "redirect:/";
}

@RequestMapping(value = "/greedy" ,  method=RequestMethod.POST)
public String GBSearch(HttpSession session ) {
	List<List<Integer>> record = (List<List<Integer>>) session.getAttribute("records");
	if(session.getAttribute("start") != "" && session.getAttribute("goal") != "") {
		System.out.println("searching..." + session.getAttribute("start") +"  to "+ session.getAttribute("goal"));
	int s = (int) session.getAttribute("start");
	int g = (int) session.getAttribute("goal");

		session.setAttribute("GBSdata", GBS.GBSearch(record,s ,g));
	}		
	return "redirect:/";
}

@RequestMapping(value = "/AStar" ,  method=RequestMethod.POST)
public String ASearch(HttpSession session ) {
	List<List<Integer>> record = (List<List<Integer>>) session.getAttribute("records");
	if(session.getAttribute("start") != "" && session.getAttribute("goal") != "") {
		System.out.println("searching..." + session.getAttribute("start") +"  to "+ session.getAttribute("goal"));
	int s = (int) session.getAttribute("start");
	int g = (int) session.getAttribute("goal");

		session.setAttribute("ASdata", ASearch.ASSearch(record,s ,g));
	}		
	return "redirect:/";
}


@RequestMapping(value = "/searchAll" ,  method=RequestMethod.POST)
public String SearchAll(HttpSession session ) {
	List<List<Integer>> record = (List<List<Integer>>) session.getAttribute("records");
	if(session.getAttribute("start") != "" && session.getAttribute("goal") != "") {
		System.out.println("searching..." + session.getAttribute("start") +"  to "+ session.getAttribute("goal"));
	int s = (int) session.getAttribute("start");
	int g = (int) session.getAttribute("goal");

		session.setAttribute("GBSdata", GBS.GBSearch(record,s ,g));
		session.setAttribute("UCSdata", UCS.UCSearch(record,s ,g));
		session.setAttribute("ASdata", ASearch.ASSearch(record,s ,g));
	}		
	return "redirect:/";
}


@RequestMapping("/deleteFile")
public String DeleteFile(HttpSession session) {
	session.invalidate();
	return "home.jsp";
}

@RequestMapping("/howtouse")
public String HowToUse(HttpSession session) {
	session.invalidate();
	return "use.jsp";
}

}
