package wam.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description : 프로젝트 화면 호출
 * @author 박이정
 * @since 2021.02.10
 * <pre>
 *  수정일        수정자        수정내용
 *  ----------  --------    ---------------------------
 *  2021.02.10   박이정        최초 생성
 * </pre>
 */
@Controller
public class TCmPrjctController {

	@GetMapping("/prjct/page")
	public String prjct() throws Exception {
		return "prjct";
	}

}
