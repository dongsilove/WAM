package wam.app.w.term;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TWdTermQuerydslRepository extends QuerydslRepositorySupport {
	
	//private final JPAQueryFactory queryFactory;
	private QTWdTerm tWdTerm = QTWdTerm.tWdTerm;
	
	public TWdTermQuerydslRepository(JPAQueryFactory queryFactory) {
		super(TWdTerm.class);
		//this.queryFactory = queryFactory;
	}


    public Page<TWdTerm> findList(Map<String,Object> param, Pageable pageable){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		JPQLQuery<TWdTerm> query= from(tWdTerm);
		
		if(param.get("domainSn") != null && !param.get("domainSn").toString().equals("")) { // 도메인 일련번호
			Integer domainSn = Integer.parseInt(param.get("domainSn").toString());
			booleanBuilder.and( tWdTerm.domainSn.eq(domainSn) );
		}
		if(param.get("prjctSn") != null && !param.get("prjctSn").toString().equals("")) { // 프로젝트 일련번호
			Integer prjctSn = Integer.parseInt(param.get("prjctSn").toString());
			booleanBuilder.and( tWdTerm.prjctSn.eq(prjctSn) );
		}
		if(param.get("termNm") != null && !param.get("termNm").toString().equals("")) { // 용어 명
			booleanBuilder.and( tWdTerm.termNm.contains(param.get("termNm").toString()) );
		}
		if(param.get("termEnAbbr") != null && !param.get("termEnAbbr").toString().equals("")) { // 용어 영문 약어
			booleanBuilder.and( tWdTerm.termEnAbbr.contains(param.get("termEnAbbr").toString()) );
		}
		if(param.get("termEnNm") != null && !param.get("termEnNm").toString().equals("")) { // 용어 영문 명
			booleanBuilder.and( tWdTerm.termEnNm.contains(param.get("termEnNm").toString()) );
		}
		if(param.get("dataFom") != null && !param.get("dataFom").toString().equals("")) { // 데이터 형식
			booleanBuilder.and( tWdTerm.dataFom.contains(param.get("dataFom").toString()) );
		}

		query = query.where(booleanBuilder).orderBy(tWdTerm.termSn.desc());
		final List<TWdTerm> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
    }
}
