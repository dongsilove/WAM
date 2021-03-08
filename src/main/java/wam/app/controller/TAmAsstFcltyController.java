package wam.app.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description : 데이터사전 화면 호출
 * @author 박이정
 * @since 2021.02.10
 * <pre>
 *  수정일        수정자        수정내용
 *  ----------  --------    ---------------------------
 *  2021.02.10   박이정        최초 생성
 * </pre>
 */
@Controller
public class TAmAsstFcltyController {

	@GetMapping("/asstfclty/list")
	public String asst_list(@RequestParam Map<String,Object> params, ModelMap model) throws Exception {
		params.forEach((k,v)->model.addAttribute(k, v));
		return "asstfclty_list";
	}

	@GetMapping("/asstfclty/edit")
	public String asst_edit(@RequestParam Integer asstSn,@RequestParam String params, ModelMap model) throws Exception {
		model.addAttribute("asstSn", asstSn);
		model.addAttribute("params", params);
		return "asstfclty_edit";
	}
	
}
