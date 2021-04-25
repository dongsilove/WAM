package wam.app.w.menu;

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

@Tag( name = "TCmMenuApiController", description = "메뉴")
@RestController 
@RequestMapping(value="/api")
public class TCmMenuApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TCmMenuRepository menuRepository;

	@Operation(summary = "메뉴 목록 조회", description = "검색 값으로 페이징된 메뉴 목록 화면을 호출한다.")
	@GetMapping("/menus")
	public Page<TCmMenu> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TCmMenu> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.ASC, "menuId"));
		if(param.get("menuNm") != null && !param.get("menuNm").toString().equals("")) {
			list = (Page<TCmMenu>) menuRepository.findByMenuNmContaining(param.get("menuNm").toString(),pageRequest);
		} else {
			list = (Page<TCmMenu>) menuRepository.findAll(PageRequest.of(page - 1, perPage));
		}
		return list;
	}
	
	@Operation(summary = "계층형 메뉴 목록 조회", description = "계층형으로 메뉴 목록을 조회한다.")
	@GetMapping("/menus/listConnectBy")
	public Page<TCmMenu> listConnectBy( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "200") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TCmMenu> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		//PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.ASC, "menuId"));
		list = (Page<TCmMenu>) menuRepository.listConnectBy(PageRequest.of(page - 1, perPage));
		return list;
	}
	
	@Operation(summary = "메뉴 조회", description = "단건 조회한다.")
	@GetMapping("/menus/{menuId}")
	public Optional<TCmMenu> get( @PathVariable String menuId ) throws Exception {

		Optional<TCmMenu> menu = menuRepository.findById(menuId);
		
		return menu;
		
	}
	
	@Operation(summary = "메뉴 저장", description = "메뉴 저장한다.")
	@PutMapping("/menus")
	public TCmMenu put(@RequestBody TCmMenu tCmMenu) throws Exception {
		
		logger.debug("메뉴 저장 호출 : {}", tCmMenu);
		TCmMenu menu;
		menu = menuRepository.save(tCmMenu);
		if (menu == null) {
			logger.debug("저장시 오류발생");
		}
		return menu;
		
	}
	
	@Operation(summary = "메뉴 삭제", description = "메뉴 삭제한다.")
	@DeleteMapping("/menus/{menuId}")
	public String delete(@PathVariable String menuId) throws Exception {
		
		logger.debug("메뉴 삭제 호출 : menuId-"+  menuId);
		menuRepository.deleteById(menuId);
		return "200";
		
	}
	

}
