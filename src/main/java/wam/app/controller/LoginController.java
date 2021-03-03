package wam.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String main() throws Exception {
		return "redirect:/govword/page";
	}
	
	@GetMapping("/login/page")
	public String login() throws Exception {
		return "login";
	}
	
	@GetMapping("/login/logout")
	public String logout(HttpSession session) throws Exception {
		
		session.invalidate();

		return "redirect:/login/page";
	}

	/**
	 *  내정보수정 화면
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/myinfo/page")
	public String myinfo() throws Exception {
		return "myinfo";
	}
	

}
