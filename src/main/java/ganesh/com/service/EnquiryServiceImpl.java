package ganesh.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import ganesh.com.binding.DashBoardResponse;
import ganesh.com.binding.EnquiryFormBinding;
import ganesh.com.binding.EnquirySearchCriteria;
import ganesh.com.entity.SatCourse;
import ganesh.com.entity.SatStatus;
import ganesh.com.entity.SatUserDetails;
import ganesh.com.entity.StudentAddEnquiry;
import ganesh.com.repository.SatCourseRepo;
import ganesh.com.repository.SatStatusRepo;
import ganesh.com.repository.SatUserDetailsRepo;
import ganesh.com.repository.StudentAddEnquiryRepo;
import jakarta.servlet.http.HttpSession;


@Service
public class EnquiryServiceImpl implements EnquiryService{
	
	@Autowired
	private SatUserDetailsRepo enquiryRepo;
	
	@Autowired
	private SatStatusRepo status;
	
	@Autowired
	private SatCourseRepo course;
	
	@Autowired
	private HttpSession session;
	
	
	@Autowired
	private StudentAddEnquiryRepo enqRepo;



	@Override
	public DashBoardResponse getDashBoardData(Integer id) {
		DashBoardResponse response= new DashBoardResponse();
		
		//retreve data from user Table
	    Optional<SatUserDetails> byId = enquiryRepo.findById(id);
	    
	    //chech table is empty or not
	    if(byId.isPresent()) {
	    	
	    	//if not empty then get data from user
	    	SatUserDetails details = byId.get();
	    	
	    	//retreve all enquire data by using enquiries forgien key
	    	List<StudentAddEnquiry> enquiries = details.getEnquiries();
	    	
	    	//total eneqiry
	    	int totalSize = enquiries.size();
	    	
	    	//ony enrolled
	    	int enrolledSize = enquiries.stream()
	    	         .filter(n -> n.getEnqStatus().equals("Enrolled"))
	    	         .collect(Collectors.toList())
	    	         .size();
	    	
	    	//only lost
	    	int lostSize = enquiries.stream()
	    			                .filter(e -> e.getEnqStatus().equals("Lost"))
	    			                .collect(Collectors.toList())
	    			                .size();
	    	
	    	//set data to response
	       response.setTotalEnq(totalSize);
	       response.setEnrolled(enrolledSize);
	       response.setLost(lostSize);
	    }
		 
		return response;
	}

	
	@Override
	public List<String> getCourseName() {
		 List<SatCourse> allCourses = course.findAll();
		 
		 List<String> cour=new ArrayList<>();
		 
		 for(SatCourse cr : allCourses) {
			 cour.add(cr.getCourseName());
		 }
		return cour;
	}

	@Override
	public List<String> getStatus() {
		List<SatStatus> allStatus = status.findAll();
		
		List<String> status= new ArrayList<>();
		
		for(SatStatus xc : allStatus) {
			status.add(xc.getStatusName());
		}
		
		return status;
	}
	
	
	@Override
	public boolean setEnquiryData(EnquiryFormBinding enquiey) {
		
		StudentAddEnquiry entity = new StudentAddEnquiry();
		
		BeanUtils.copyProperties(enquiey, entity);
		
		Integer id=(Integer)session.getAttribute("sessionKey");
		
		SatUserDetails userDetails = enquiryRepo.findById(id).get();
		
		entity.setUser(userDetails);
		
		enqRepo.save(entity);
		
		return true;
	}

	
	/*
	@Override
	public List<StudentAddEnquiry> getAllEnquiry(Integer id, EnquirySearchCriteria criteria) {
		 
	         Optional<SatUserDetails> user = enquiryRepo.findById(id);
	         
	         if(user.isPresent()) {
	        	 SatUserDetails satUserDetails = user.get();
	        	 List<StudentAddEnquiry> enquiries = satUserDetails.getEnquiries();
	        	 
	        	 if(null != criteria.getCourse() && !" ".equals(criteria.getCourse())) {
	        		 enquiries= enquiries.stream().filter(n -> n.getCourseName().equals(criteria.getCourse())).collect(Collectors.toList());
	        	 }
	        	 if(null != criteria.getStatus() && !" ".equals(criteria.getStatus())) {
	        		 enquiries= enquiries.stream().filter(n -> n.getEnqStatus().equals(criteria.getStatus())).collect(Collectors.toList());
	        	 }
	        	 if(null != criteria.getMode() && !" ".equals(criteria.getMode())) {
	        		 enquiries= enquiries.stream().filter(n -> n.getClassMode().equals(criteria.getMode())).collect(Collectors.toList());
	        	 }
	        	 
	        	 return enquiries;
	         }
		return null;
	}   */

	@Override
	public EnquiryFormBinding editEnquiry(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<StudentAddEnquiry> getEnquiriess() {
         Integer id=(Integer)session.getAttribute("sessionKey");
         Optional<SatUserDetails> user = enquiryRepo.findById(id);
         
         if(user.isPresent()) {
        	 SatUserDetails satUserDetails = user.get();
        	 List<StudentAddEnquiry> enquiries = satUserDetails.getEnquiries();
        	 return enquiries;
         }
		return null;
	}



}
