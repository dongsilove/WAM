package wam.app.w.userGrp;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name = "TAuUserGrpApiController", description = "사용자그룹")
@RestController 
@RequestMapping(value="/api")
public class TAuUserGrpApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TAuUserGrpRepository deptRepository;

	@Operation(summary = "사용자그룹 목록 조회", description = "검색 값으로 페이징된 사용자그룹 목록 화면을 호출한다.")
	@GetMapping("/usergrps")
	public Page<TAuUserGrp> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TAuUserGrp> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.ASC, "usergrpCd"));
		if(param.get("usergrpNm") != null && !param.get("usergrpNm").toString().equals("")) {
			list = (Page<TAuUserGrp>) deptRepository.findByUsergrpNmContaining(param.get("usergrpNm").toString(),pageRequest);
		} else {
			list = (Page<TAuUserGrp>) deptRepository.findAll(PageRequest.of(page - 1, perPage));
		}
		return list;
	}
	
	@Operation(summary = "사용자그룹 조회", description = "단건 조회한다.")
	@GetMapping("/usergrps/{usergrpCd}")
	public Optional<TAuUserGrp> get( @PathVariable String usergrpCd ) throws Exception {

		Optional<TAuUserGrp> dept = deptRepository.findById(usergrpCd);
		
		return dept;
		
	}
	
	@Operation(summary = "사용자그룹 저장", description = "사용자그룹 저장한다.")
	@PutMapping("/usergrps")
	public TAuUserGrp put(@RequestBody TAuUserGrp tAuUserGrp) throws Exception {
		
		logger.debug("사용자그룹 저장 호출 : {}", tAuUserGrp.toString());
		TAuUserGrp dept;
		dept = deptRepository.save(tAuUserGrp);
		if (dept == null) {
			logger.debug("저장시 오류발생");
		}
		return dept;
		
	}
	
	@Operation(summary = "사용자그룹 삭제", description = "사용자그룹 삭제한다.")
	@DeleteMapping("/usergrps/{usergrpCd}")
	public String delete(@PathVariable String usergrpCd) throws Exception {
		
		logger.debug("사용자그룹 삭제 호출 : usergrpCd-"+  usergrpCd);
		deptRepository.deleteById(usergrpCd);
		return "200";
		
	}
	

}
