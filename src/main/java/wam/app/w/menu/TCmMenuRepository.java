package wam.app.w.menu;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCmMenuRepository extends JpaRepository<TCmMenu, String> {

	
	Page<TCmMenu> findByMenuNmContaining(String searchValue, Pageable pageable);
	
	
}
