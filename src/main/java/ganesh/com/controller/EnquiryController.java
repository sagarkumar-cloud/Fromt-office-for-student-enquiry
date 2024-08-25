package ganesh.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ganesh.com.binding.DashBoardResponse;
import ganesh.com.binding.EnquiryFormBinding;
import ganesh.com.binding.EnquirySearchCriteria;
import ganesh.com.entity.StudentAddEnquiry;
import ganesh.com.service.EnquiryService;
import jakarta.servlet.http.HttpSession;
@Controller
public class EnquiryController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EnquiryService service;
	
	
	@GetMapping("logout")
	public String logoutpage() {
		session.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	 public String dashboardPage(Model model) {
		Integer attribute = (Integer)session.getAttribute("sessionKey");
		
		DashBoardResponse dashBoardData = service.getDashBoardData(attribute);
		model.addAttribute("dashBoardData",dashBoardData);
		 return "dashboard";
	 }
	
	//==============================================================================
	 @PostMapping("/addEnq")
	 public String handleAddEnquiry(@ModelAttribute("formObj") EnquiryFormBinding form,Model model) {
		 
		boolean setData= service.setEnquiryData(form);
		
		if(setData) {
			model.addAttribute("successMsg","enquiry added");
		}else {
			model.addAttribute("errMsg","enquiry added failed...!!");
		}
		 
             
		return "add-enquiry";
	 } 
	
	
	 @GetMapping("/enquiry")
	 public String addEnqueryPage(Model model) {
		 //all course
		 List<String> courseName = service.getCourseName();
		 
		 //all status
		 List<String> allStatus = service.getStatus();
		 
		 //create a object of EnquiryForm
		 EnquiryFormBinding enquiryForm = new EnquiryFormBinding();
		 
		 //set data to model object
		 model.addAttribute("course",courseName);
		 model.addAttribute("enquiryStatus",allStatus);
		 model.addAttribute("formObj",enquiryForm);
		 
		 return "add-enquiry";
	 }
	 
	
	 
	 //=================================================================================
	 
	 private void initForm(Model model){
		 //all course
		 List<String> courseName = service.getCourseName();
		 
		 //all status
		 List<String> allStatus = service.getStatus();
		 
		 //create a object of EnquiryForm
		 EnquiryFormBinding enquiryForm = new EnquiryFormBinding();
		 
		 //set data to model object
		 model.addAttribute("courseName",courseName);
		 model.addAttribute("statusName",allStatus);
		 model.addAttribute("formObj",enquiryForm);
	 }
	 
	 
	 @GetMapping("/enquires")
	 public String viewEnqueriesPage(Model model) {
		 initForm(model);
		List<StudentAddEnquiry> enq = service.getEnquiriess();
		model.addAttribute("enquiries",enq);
		return "view-enquires";
	 }
	 
	 
   /*	 
	 @GetMapping("filter-enq")
	 public String filterEnquirys( @RequestParam String mode,
			 @RequestParam String course ,
			 @RequestParam String status,Model model) {
		 
		 EnquirySearchCriteria enq = new EnquirySearchCriteria();
		   enq.setCourse(course);
		   enq.setMode(mode);
		   enq.setStatus(status);
		   Integer attribute = (Integer)session.getAttribute("sessionKey");
		   List<StudentAddEnquiry> allEnquiry = service.getAllEnquiry(attribute, enq);
		   model.addAttribute("enquiries",allEnquiry);
		 return "view-enquires-new";	
		 }
	  */
}
