package ganesh.com.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtil {
  
	 public static String generateRendomPassword() {
		 String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		 String pwd = RandomStringUtils.random( 7, characters );
		
		 return pwd;
	 }
}
