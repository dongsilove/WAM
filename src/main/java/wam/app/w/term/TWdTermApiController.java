package wam.app.w.term;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag( name = "TWdTermApiController", description = "용어")
@RestController 
@RequestMapping(value="/api")
public class TWdTermApiController {

	//private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TWdTermRepository termRepository;
	@Autowired
	TWdTermQuerydslRepository termQuerydslRepository;
	

	@Operation(summary = "용어 목록 조회", description = "검색 값으로 페이징된 용어 목록 화면을 호출한다.")
	@GetMapping("/terms")
	public Page<TWdTerm> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		
		Page<TWdTerm> list;
		param.forEach((k,v)->log.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "termSn"));
		list = termQuerydslRepository.findList(param, pageRequest);
		return list;
		
	}
	
	@Operation(summary = "용어 조회", description = "용어 단건 조회한다.")
	@GetMapping("/terms/{termSn}")
	public Optional<TWdTerm> get( @PathVariable Integer termSn) throws Exception {

		Optional<TWdTerm> term = termRepository.findById(termSn);
		
		return term;
		
	}
	
	@Operation(summary = "용어 저장", description = "용어 저장한다.")
	@PutMapping("/terms")
	public TWdTerm put(@RequestBody @Valid TWdTerm tWdTerm)  throws Exception  {
		
		log.debug("용어 저장 호출 : {}", tWdTerm);
		TWdTerm term;
		term = termRepository.save(tWdTerm);
		if (term == null) {
			log.debug("저장시 오류발생");
		}
		return term;
		
	}
	
	@Operation(summary = "용어 삭제", description = "용어 삭제한다.")
	@DeleteMapping("/terms/{termSn}")
	public String delete(@PathVariable Integer termSn) throws Exception {
		
		log.debug("용어 삭제 호출 :"+  Integer.toString(termSn));
		termRepository.deleteById(termSn);
		return "200";
		
	}
	
	
}
