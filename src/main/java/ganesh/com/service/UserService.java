package ganesh.com.service;

import ganesh.com.binding.LoginForm;
import ganesh.com.binding.SignupForm;
import ganesh.com.binding.UnlockForm;

public interface UserService {

	//if we made return typr as boolean then one problem will come i.e  we will not know why login failure
	  public String login(LoginForm login);
	  
	  public boolean signup(SignupForm signup);
	  
	  public boolean forgetPwd(String email);
	  
	  public boolean unlock(UnlockForm unlock);
}
