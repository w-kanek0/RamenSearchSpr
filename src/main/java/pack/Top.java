package pack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Top {
	
	@RequestMapping(value = "/")
	public String login(Model model) {
		return "jsp/login";
	}
}
