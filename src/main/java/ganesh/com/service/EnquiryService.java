package ganesh.com.service;

import java.util.List;

import ganesh.com.binding.DashBoardResponse;
import ganesh.com.binding.EnquiryFormBinding;
import ganesh.com.binding.EnquirySearchCriteria;
import ganesh.com.entity.StudentAddEnquiry;



public interface EnquiryService {
	
	public DashBoardResponse getDashBoardData(Integer id);

	public List<String> getCourseName();
	 
	 public List<String> getStatus();
	 
	 
	 
	 public boolean setEnquiryData(EnquiryFormBinding enquiey);
	 
	 //public List<StudentAddEnquiry> getAllEnquiry(Integer id , EnquirySearchCriteria criteria);
	 
	 public EnquiryFormBinding editEnquiry(Integer enqId);
	 
	 public List<StudentAddEnquiry> getEnquiriess();
}
