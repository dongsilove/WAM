package wam.app.w.govword;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name = "TWdGovwordApiController", description = "행정단어")
@RestController 
@RequestMapping(value="/api")
public class TWdGovwordApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TWdGovwordRepository govwordRepository;

	@Operation(summary = "행정단어 목록 조회", description = "검색 값으로 페이징된 행정단어 목록 화면을 호출한다.")
	@GetMapping("/govwords")
	public Page<TWdGovword> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TWdGovword> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "wordSn"));
		if(param.get("wordNm") != null && !param.get("wordNm").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findByWordNmContaining(param.get("wordNm").toString(), pageRequest);
		} else if(param.get("wordEnAbbr") != null && !param.get("wordEnAbbr").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findByWordEnAbbrContaining(param.get("wordEnAbbr").toString(), pageRequest);
		} else if(param.get("wordEnNm") != null && !param.get("wordEnNm").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findByWordEnNmContaining(param.get("wordEnNm").toString(), pageRequest);
		} else if(param.get("themaSe") != null && !param.get("themaSe").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findByThemaSeContaining(param.get("themaSe").toString(), pageRequest);
		} else if(param.get("wordSe") != null && !param.get("wordSe").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findByWordSeContaining(param.get("wordSe").toString(), pageRequest);
		} else if(param.get("sttusSe") != null && !param.get("sttusSe").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findBySttusSeContaining(param.get("sttusSe").toString(), pageRequest);
		} else {
			list = (Page<TWdGovword>) govwordRepository.findAll(PageRequest.of(page - 1, perPage));
		}
		return list;
		
	}
	
	@Operation(summary = "공통 행정단어 목록 조회", description = "검색 값으로 페이징된 행정단어 목록 화면을 호출한다.")
	@GetMapping("/common/govwords")
	public Page<TWdGovword> common_list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "50") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TWdGovword> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "wordSn"));
		if(param.get("wordNm") != null && !param.get("wordNm").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findByWordNmContaining(param.get("wordNm").toString(), pageRequest);
		} else if(param.get("wordEnAbbr") != null && !param.get("wordEnAbbr").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findByWordEnAbbrContaining(param.get("wordEnAbbr").toString(), pageRequest);
		} else if(param.get("wordEnNm") != null && !param.get("wordEnNm").toString().equals("")) {
			list = (Page<TWdGovword>) govwordRepository.findByWordEnNmContaining(param.get("wordEnNm").toString(), pageRequest);
		} else {
			list = (Page<TWdGovword>) govwordRepository.findAll(PageRequest.of(page - 1, perPage));
		}
		return list;
		
	}
	
	
}
