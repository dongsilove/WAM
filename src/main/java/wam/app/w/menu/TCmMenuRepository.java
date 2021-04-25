package wam.app.w.menu;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TCmMenuRepository extends JpaRepository<TCmMenu, String> {

	
	Page<TCmMenu> findByMenuNmContaining(String searchValue, Pageable pageable);
	
	@Query(value=" with recursive MENU_V (MENU_ID, MENU_NM, UPPER_MENU_ID, MENU_ICON_NM, MENU_URL, dep, pth, cyc)"
			+ " as ("
			+ " 	SELECT M.MENU_ID, M.MENU_NM, M.UPPER_MENU_ID,M.MENU_ICON_NM, M.MENU_URL,  1, "
			+ " 		array[m.menu_ID::::text]  AS pth, false"
			+ " 	FROM t_cm_menu M"
			+ " 	WHERE m.use_yn = 'Y' "
			+ " 	  and m.MENU_ID = 'TOP'"
			+ " 	union all "
			+ " 	select M.MENU_ID, M.MENU_NM, M.UPPER_MENU_ID,M.MENU_ICON_NM, M.MENU_URL, v.dep + 1,"
			+ " 	   array_append( v.pth, m.menu_id::::text) AS pth , m.menu_id = any(v.pth)"
			+ " 	from t_cm_menu m, menu_v v"
			+ " 	where m.upper_menu_id = v.MENU_ID"
			+ " 	  and not cyc"
			+ " )"
			+ " SELECT * "
			+ " from menu_v"
			+ " order by pth" , nativeQuery = true)
	Page<TCmMenu> listConnectBy(Pageable pageable) throws Exception;
	

	
}
