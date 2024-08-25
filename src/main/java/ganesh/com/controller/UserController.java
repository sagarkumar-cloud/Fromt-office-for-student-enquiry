package ganesh.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ganesh.com.binding.LoginForm;
import ganesh.com.binding.SignupForm;
import ganesh.com.binding.UnlockForm;
import ganesh.com.service.UserService;
@Controller
public class UserController {
	
	@Autowired
	private UserService service;

	//model object used to send the data controller to ui
	
	@GetMapping("/unlock")
	 public String unlockUserAccount(@RequestParam String email ,Model model) {
		
		UnlockForm uf = new UnlockForm();
		uf.setEmail(email);
	  
		model.addAttribute("unlockUser", uf);
		
		 return "unlock";
	 }
	
	@PostMapping("/unlock")
	public String handleUnlockForm(@ModelAttribute("unlockUser") UnlockForm form , Model model) {
		
		if(form.getNewPwd().equals(form.getConfirmPwd())) {
			
			boolean unlock = service.unlock(form);
			if(unlock) {
				model.addAttribute("sucessMsg","your account is unlocked");
			}else {
				model.addAttribute("errMsg","givrn temporary password is incorrect, check your mail");
			}
			
		}else {
			model.addAttribute("errMsg","new passwors and confirm password must be same");
		}
		
		return "unlock";
	}
	
	//==========================================================
	
	@GetMapping("/forget")
	 public String forgrtPwdPage() {
		
		
		 return "forgetPwd";
	 }
	
	@PostMapping("/forgetPwd")
	public String handleForgetPage(@RequestParam("email") String email, Model model) {
		boolean forgetPwd = service.forgetPwd(email);
		if(forgetPwd) {
			model.addAttribute("successMsg","check your mail");
			
		}else {
			model.addAttribute("errMsg","plz give correct email");
		}
		System.out.println(email);
		
		return "forgetPwd";
	}
	
	//===================================================================
	@GetMapping("/login")
	 public String loginPage(Model model) {
		//LoginForm log= new LoginForm();//eithr this or below line same things
		model.addAttribute("log", new LoginForm());
		 return "login";
	 }
	
	@PostMapping("/login")
	public String handleLoginForm(@ModelAttribute("log")  LoginForm form,Model model) {
		
		String login = service.login(form);
		if(login.contains("success")) {
			return "redirect:/dashboard";//it is not direct going to dashboard it will redirect
		}else {
			model.addAttribute("errMsg",login);
		}
		
		return "login";
	}
	
	//===================================signup functionalaty==================================
	
	 @PostMapping("/signup")
		public String handleSignUp(@ModelAttribute("user")  SignupForm form,Model model) {
			boolean status = service.signup(form);
			if(status) {
				model.addAttribute("sucessMsg","Account created ,check your email");
			}else {
				model.addAttribute("errMsg","choose unique email!!");
			}
			//System.out.println(form);
			
			return "signup";
		}
		
		
		@GetMapping("/signup")
		 public String signUpPage(Model model ) {
			model.addAttribute("user",new SignupForm());
			 return "signup";
		 }
}
