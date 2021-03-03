package wam.app.w.domain;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TWdDomainRepository extends JpaRepository<TWdDomain, Integer> {
	Page<TWdDomain> findByDomainNmContaining(String searchValue,  Pageable pageable);
	Page<TWdDomain> findByDomainEnAbbrContaining(String searchValue,  Pageable pageable);
	Page<TWdDomain> findByDomainEnNmContaining(String searchValue,  Pageable pageable);
	Page<TWdDomain> findByDomainCl(String searchValue,  Pageable pageable);
	Page<TWdDomain> findByDomainExprsnNmContaining(String searchValue,  Pageable pageable);
	Page<TWdDomain> findByDataTypeContaining(String searchValue,  Pageable pageable);
	Page<TWdDomain> findByDataLt(BigDecimal searchValue,  Pageable pageable);
}
