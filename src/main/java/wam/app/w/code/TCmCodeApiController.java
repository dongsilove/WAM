package wam.app.w.code;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wam.app.w.dept.TAuDept;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name = "TCmCodeApiController", description = "코드")
@RestController 
@RequestMapping(value="/api")
public class TCmCodeApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TCmCodeRepository codeRepository;

	@Operation(summary = "코드 목록 조회", description = "검색 값으로 페이징된 코드 목록 화면을 호출한다.")
	@GetMapping("/codes")
	public Page<TCmCode> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TCmCode> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage); //, Sort.by(Direction.ASC, "deptCd")
		if(param.get("cdNm") != null && !param.get("cdNm").toString().equals("")) {
			list = (Page<TCmCode>) codeRepository.findByCdNmContaining(param.get("cdNm").toString(),pageRequest);
		} else if(param.get("grpCd") != null && !param.get("grpCd").toString().equals("")) {
			list = (Page<TCmCode>) codeRepository.findByGrpCd(param.get("grpCd").toString(),pageRequest);
		} else {
			list = (Page<TCmCode>) codeRepository.findAll(pageRequest);
		}
		return list;
	}
	
	@Operation(summary = "코드 조회", description = "단건 조회한다.")
	@GetMapping("/codes/{grpCd}/{cd}")
	public Optional<TCmCode> get( @PathVariable String grpCd, @PathVariable String cd) throws Exception {

		Optional<TCmCode> code = codeRepository.findByGrpCdAndCd(grpCd, cd);
		
		return code;
		
	}
	
	@Operation(summary = "코드 저장", description = "코드 저장한다.")
	@PutMapping("/codes")
	public TCmCode put(@RequestBody TCmCode tCmCode) throws Exception {
		
		logger.debug("코드 저장 호출 : {}", tCmCode);
		TCmCode code;
		code = codeRepository.save(tCmCode);
		if (code == null) {
			logger.debug("저장시 오류발생");
		}
		return code;
		
	}
	
	@Operation(summary = "코드 삭제", description = "코드 삭제한다.")
	@DeleteMapping("/codes/{grpCd}/{cd}")
	public String delete(@PathVariable String grpCd, @PathVariable String cd) throws Exception {
		
		logger.debug("코드 삭제 호출 :(grpCd-"+  grpCd + ", cd-" + cd);
		codeRepository.deleteByGrpCdAndCd(grpCd, cd);
		return "200";
		
	}
	
	@Operation(summary = "공통 코드 목록 조회", description = "그룹코드값으로 코드 목록을 조회한다.")
	@GetMapping("/common/codes/{grpCd}")
	public Page<TCmCode> common_list( @PathVariable(required = true) String grpCd) throws Exception {
		Page<TCmCode> list;
		logger.debug("grpCd:" + grpCd);
		list = (Page<TCmCode>) codeRepository.findByGrpCd(grpCd,Pageable.unpaged());
		return list;
		
	}	
}
