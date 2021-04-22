package wam.app.w.dept;

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

@Tag( name = "TAuDeptApiController", description = "부서")
@RestController 
@RequestMapping(value="/api")
public class TAuDeptApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TAuDeptRepository deptRepository;

	@Operation(summary = "부서 목록 조회", description = "검색 값으로 페이징된 부서 목록 화면을 호출한다.")
	@GetMapping("/depts")
	public Page<TAuDept> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TAuDept> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.ASC, "deptCd"));
		if(param.get("deptNm") != null && !param.get("deptNm").toString().equals("")) {
			list = (Page<TAuDept>) deptRepository.findByDeptNmContaining(param.get("deptNm").toString(),pageRequest);
		} else {
			list = (Page<TAuDept>) deptRepository.findAll(PageRequest.of(page - 1, perPage));
		}
		return list;
	}
	
	@Operation(summary = "부서 조회", description = "단건 조회한다.")
	@GetMapping("/depts/{deptCd}")
	public Optional<TAuDept> get( @PathVariable String deptCd ) throws Exception {

		Optional<TAuDept> dept = deptRepository.findById(deptCd);
		
		return dept;
		
	}
	
	@Operation(summary = "부서 저장", description = "부서 저장한다.")
	@PutMapping("/depts")
	public TAuDept put(@RequestBody TAuDept tAuDept) throws Exception {
		
		logger.debug("부서 저장 호출 : {}", tAuDept);
		TAuDept dept;
		dept = deptRepository.save(tAuDept);
		if (dept == null) {
			logger.debug("저장시 오류발생");
		}
		return dept;
		
	}
	
	@Operation(summary = "부서 삭제", description = "부서 삭제한다.")
	@DeleteMapping("/depts/{deptCd}")
	public String delete(@PathVariable String deptCd) throws Exception {
		
		logger.debug("부서 삭제 호출 : deptCd-"+  deptCd);
		deptRepository.deleteById(deptCd);
		return "200";
		
	}
	

}
