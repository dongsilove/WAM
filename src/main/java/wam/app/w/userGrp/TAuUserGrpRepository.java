package wam.app.w.userGrp;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TAuUserGrpRepository extends JpaRepository<TAuUserGrp, String> {

	
	Page<TAuUserGrp> findByGrpNmContaining(String searchValue, Pageable pageable);
	
	
}
