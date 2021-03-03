package wam.app.w.cdGrp;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCmCdGrpRepository extends JpaRepository<TCmCdGrp, String> {

	
	Page<TCmCdGrp> findByGrpCdNmContaining(String searchValue, Pageable pageable);
	
	
}
