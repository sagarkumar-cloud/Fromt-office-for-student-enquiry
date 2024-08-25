package ganesh.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {

	@GetMapping("/page")
	  public String getPage() {
		  return "index";
	  }
}
