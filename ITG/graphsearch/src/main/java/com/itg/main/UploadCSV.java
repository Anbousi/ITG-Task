package com.itg.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadCSV{
//	This class is to get the full path for the uploaded file.
	@RequestMapping(value = "/UploadCSV", method=RequestMethod.POST , consumes = "multipart/form-data")
	public String CSVFile(@RequestParam MultipartFile file , HttpSession session) {
		
		if (!file.isEmpty()) {
			List<List<Integer>> records;
            try {
            	String fileName = file.getOriginalFilename();
            	if(!fileName.contains(".csv") || fileName == null) {
            		return "redirect:/howtouse";
            	}
            	System.out.println(fileName);
            	File files = new File(fileName);
            	file.transferTo(new File(files.getAbsoluteFile()+""));
            	records = UploadCSV.readFile(files.getAbsoluteFile()+"");
            	session.setAttribute("records", records); 
            	Files.deleteIfExists(files.toPath());
            } catch (Exception e) {
                return "redirect:/";
            }
            if(session.getAttribute("records") != null) {
        	session.setAttribute("start", 1);
        	session.setAttribute("goal", records.size()-1);
        	 return "redirect:/";
            }
		}
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/setPath", method=RequestMethod.POST)
	public String CSVFile(@RequestParam("start") String start , @RequestParam("goal") String goal, HttpSession session) {
		System.out.println(session.getAttribute("start") +"  to "+ session.getAttribute("goal"));
		ArrayList<List<Integer>> records = (ArrayList<List<Integer>>) session.getAttribute("records");
		session.setAttribute("UCSdata", null);
		session.setAttribute("GBSdata", null);
		session.setAttribute("ASdata", null);
		if(goal != "") {
			if(Integer.parseInt(goal) > records.size()-1) {
				session.setAttribute("goal",records.size()-1);
			}
			else if(Integer.parseInt(goal) < 1) {
				session.setAttribute("goal",1);
			}
			else {
				session.setAttribute("goal",Integer.parseInt(goal));
			}
			
			
		}
		
		if(start  != "") {
			if(Integer.parseInt(start) > records.size()-1) {
				session.setAttribute("start",records.size()-1);
			}
			else if(Integer.parseInt(start) < 1) {
				session.setAttribute("start",1);
			}
			else {
				session.setAttribute("start",Integer.parseInt(start));
			}
		}
		
		return "redirect:/";
	}
	
	public static List<List<Integer>> readFile(String path) throws IOException {
		
        List<List<Integer>> r = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Integer[] intVal = new Integer[values.length];
                int i = 0;
                for (String s : values) {

                    intVal[i] = (Integer.parseInt(s));
                    i++;
                }
                r.add(Arrays.asList(intVal));
            }
        }
        final List<List<Integer>> records = new ArrayList<>(r);
        return records;
	}
	}
