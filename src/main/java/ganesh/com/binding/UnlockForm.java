package ganesh.com.binding;

public class UnlockForm {
   private String email;
   private String password;
   private String newPwd;
   private String confirmPwd;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNewPwd() {
	return newPwd;
}
public void setNewPwd(String newPwd) {
	this.newPwd = newPwd;
}
public String getConfirmPwd() {
	return confirmPwd;
}
public void setConfirmPwd(String confirmPwd) {
	this.confirmPwd = confirmPwd;
}
@Override
public String toString() {
	return "UnlockForm [email=" + email + ", password=" + password + ", newPwd=" + newPwd + ", confirmPwd=" + confirmPwd
			+ "]";
}
   
   
}
