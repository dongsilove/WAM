package wam.app;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import wam.app.w.domain.TWdDomain;
import wam.app.w.domain.TWdDomainRepository;
import wam.app.w.term.TWdTerm;
import wam.app.w.term.TWdTermQuerydslRepository;



@RunWith(SpringRunner.class)
@SpringBootTest // 통합테스트를 위한 어노테이션
public class WordRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	TWdDomainRepository domainRepository;
	@Autowired 
	TWdTermQuerydslRepository termRepository;

	@Test @Ignore
	public void save() throws Exception { 
		TWdDomain domain = new TWdDomain(new BigDecimal(200),"VARCHAR", "이름", "SYNONM","SYNONYM", "동의어"); 
		domainRepository.save(domain); 
	}

	@Test  @Ignore
	public void delete() throws Exception { 
		Integer domainSn = new Integer("92");;
		domainRepository.deleteById(domainSn); 
	}
	


}
