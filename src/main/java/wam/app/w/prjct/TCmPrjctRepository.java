package wam.app.w.prjct;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCmPrjctRepository extends JpaRepository<TCmPrjct, Integer> {
	Page<TCmPrjct> findByPrjctNmContaining(String searchValue,  Pageable pageable);
}
