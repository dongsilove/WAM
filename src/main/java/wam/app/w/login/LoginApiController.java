package wam.app.w.login;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import wam.app.util.CryptoUtil;
import wam.app.util.JsonUtil;
import wam.app.w.menu.TCmMenu;
import wam.app.w.menu.TCmMenuRepository;
import wam.app.w.user.TAuUser;
import wam.app.w.user.TAuUserRepository;


@Tag( name = "LoginApiController", description = "로그인")
@RestController
@RequestMapping(value="/api/login")
public class LoginApiController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TAuUserRepository userRepository;
	@Autowired
	TCmMenuRepository menuRepository;
	
	@Operation(summary = "로그인", description = "로그인 정보를 session에 저장한다.")
	@PostMapping("/login")
	public  String actionLoginNew(@RequestParam Map<String, Object> param,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		
		param.forEach((k,v)->logger.debug("params key : " + k + " value : " + v)); // - 파라메터 디버깅
		if (param.get("userId") == null || param.get("pwd") == null) return "600|parameter no exist "; // parameter 오류
		
		int loginTryCnt = 1;
		String loginTryId = (String)session.getAttribute("loginTryId"); // 로그인시도횟수
			
		// session에 저장된 TAuUser 가져오기
		TAuUser loginInfo = wam.app.util.SessionUtil.getLoginInfo(request);
		if(loginInfo != null && loginInfo.getUserId().equals(param.get("userId").toString())){ // 이미 로그인되어 있다면
			return "200|" + (String)session.getAttribute("firstMenuUrl"); // already loginned
		}

		// 로그인횟수 점검 5회이상일 경우 5분후 재시도 권장
		if (loginTryId == null) {
			session.setAttribute("loginTryId", param.get("userId").toString());
			session.setAttribute("loginTryCnt", 1);
			logger.info("loginTryId == null");
		} else {
			if (!loginTryId.equals(param.get("userId").toString())) {
				session.setAttribute("loginTryId", param.get("userId").toString());
				session.setAttribute("loginTryCnt", 1);
				logger.info("!loginTryId.equals(userId)");
			} else {
				loginTryCnt = (int)session.getAttribute("loginTryCnt");
				session.setAttribute("loginTryCnt", ++loginTryCnt);
				
				if (loginTryCnt > 5) {
					session.setMaxInactiveInterval(5*60); // 5분
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<script type=\'text/javascript'>");
					out.println("alert('로그인 시도 횟수(5회)가 초과되었습니다. 5분 후 재시도 하세요.');");
					out.println("location.href = '/login/page.do';");
					out.println("</script>");
					return null;					
				}
			}
		}
		
		// 일반 로그인 처리
		String pwd = (String)param.get("pwd");
		logger.debug(pwd);
		String userId = (String)param.get("userId");
		Optional<TAuUser> optUser = userRepository.findById(userId);
		
		if(optUser.isPresent()) {
			TAuUser rsltUser = optUser.get();
			String resultPwd = rsltUser.getPwd();
			String resultSalt = rsltUser.getPwdSalt(); 
			String encryptPwd = CryptoUtil.encryptSHA256salt(pwd,resultSalt); // parameter pwd암호화
			logger.debug("encryptPwd - " + encryptPwd);
			logger.debug("resultPwd - " + resultPwd);
			if (resultPwd.equals(encryptPwd)) { // DB의 비밀번호와 암호화비밀번호가 일치하면 
				
				// 메뉴, 권한(사용자그룹)조회 => 권한있는 첫페이지 구하기
				Page<TCmMenu> menuList = (Page<TCmMenu>) menuRepository.listConnectBy(PageRequest.of(1 - 1, 200));
				Map<String, Object> authorCnMap = JsonUtil.convertJsonToObject(rsltUser.getTAuUserGrp().getAuthorCn());
				String firstMenuUrl = "";
				for(TCmMenu menuOne : menuList) {
					if (menuOne.getMenuUrl() != null && !menuOne.getMenuUrl().equals("")) {
						if (authorCnMap.get(menuOne.getMenuId()) != null) {
							firstMenuUrl = menuOne.getMenuUrl();
							break;
						}
					}
				}
				
				session.setAttribute("firstMenuUrl", firstMenuUrl);  // 권한있는 첫 페이지

				session.setAttribute("loginId", userId); // session timeout 점검용
				session.setAttribute("loginDeptNm", rsltUser.getTAuDept().getDeptNm()); // 부서명
				
				session.setAttribute("loginInfo", rsltUser);
				
				return "200|" + firstMenuUrl;
			} else { // 비밀번호 일치하지 않음.
				return "201|pwd is not equal";
			}
		} else { // 입력한 userId에 해당하는 사용자record 없음.
			return "201|TAuUser does not exist";
		}
	}
	

	
	@Operation(summary = "session만료", description = "session만료")
    @RequestMapping(value="/nosession")
	public ResponseEntity<String> nosession(HttpSession session, HttpServletRequest request, HttpServletResponse resp) {
		String result = "unauthorized access";
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.UNAUTHORIZED); // 401 반환
		logger.debug("***************************nosession");
    	return responseEntity;
    }

}
