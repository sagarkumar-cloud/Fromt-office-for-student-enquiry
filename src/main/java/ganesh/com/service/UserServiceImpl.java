package ganesh.com.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ganesh.com.binding.LoginForm;
import ganesh.com.binding.SignupForm;
import ganesh.com.binding.UnlockForm;
import ganesh.com.entity.SatUserDetails;
import ganesh.com.repository.SatUserDetailsRepo;
import ganesh.com.utils.EmailUtil;
import ganesh.com.utils.PwdUtil;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

	 @Autowired
	private SatUserDetailsRepo detailsrepo;
	
	 @Autowired
	 private EmailUtil email;
	 
	 @Autowired
	 private HttpSession session;
	 
	 
	 @Override
		public boolean unlock(UnlockForm unlock) {
		 
		    SatUserDetails entity = detailsrepo.findByEmail(unlock.getEmail());
		    if(entity.getPassword().equals(unlock.getPassword())) {
		    	
		    	entity.setAccountStatus("UNLOCKED");
		    	entity.setPassword(unlock.getNewPwd());
		    	detailsrepo.save(entity);
		    	return true;
		    }else {
		    	return false;
		    }
		    
			
		}
	 
	 
	 //================================================================
	 
	@Override
	public boolean signup(SignupForm signup) {
		
		//TODO: check email alredy exits or not
		
	  SatUserDetails byEmail = detailsrepo.findByEmail(signup.getEmail());
	  
	  if(byEmail != null) {
		  return false;
	  }
		
		//TODO:copy form object to entity object
		
		SatUserDetails entity = new SatUserDetails();
		BeanUtils.copyProperties(signup, entity);
		
		
		//TODO: generate rendom password 
		String password=PwdUtil.generateRendomPassword();
		entity.setPassword(password);
		
		
		//TODO:set account status as unlock
		entity.setAccountStatus("LOCKED");
		
		
		//TODO:insert into databse
		
		detailsrepo.save(entity);
		
		
		//TODO:send email to unlock account
		
		String to =signup.getEmail();
		String subject =" unlock your account || sagar it";
		
		StringBuffer body = new StringBuffer();
		body.append("<h1> use temporary password to unlock your account..!</h1>");
		body.append("<br/>");
		body.append(password);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8080/unlock?email="+to+"\">Click here to unlock your account</a>");
		email.sendEmail(to,subject, body.toString());
		return true;
	}
	
	//=================================================================

	@Override
	public boolean forgetPwd(String mail) {
		
		SatUserDetails byEmail = detailsrepo.findByEmail(mail);
		if(mail != null) {
			
			String to =mail;
			String subject ="Recovery password..";
			String body="your password :: "+byEmail.getPassword();
			email.sendEmail(to, subject, body);
			return true;
		}else {
			return false;
		}
	}

	//==============================================================

	@Override
	public String login(LoginForm form) {
		SatUserDetails entity = detailsrepo.findByEmailAndPassword(form.getEmail(), form.getPassword());
		
		if(entity == null) {
			return "invalid credintial";
		}
		
		if(entity.getAccountStatus().equals("LOCKED")) {
			return "your account is locked";
		}
		
		//crete session and store user data in session
		session.setAttribute("sessionKey", entity.getUserId());
		return "success";
	}

	

}
