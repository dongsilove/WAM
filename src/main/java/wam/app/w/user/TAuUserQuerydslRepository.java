package wam.app.w.user;

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
public class TAuUserQuerydslRepository extends QuerydslRepositorySupport {
	
	//private final JPAQueryFactory queryFactory;
	private QTAuUser tAuUser = QTAuUser.tAuUser;
	
	public TAuUserQuerydslRepository(JPAQueryFactory queryFactory) {
		super(QTAuUser.class);
		//this.queryFactory = queryFactory;
	}


    public Page<TAuUser> findList(Map<String,Object> param, Pageable pageable){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		JPQLQuery<TAuUser> query= from(tAuUser);
		
		if(param.get("userId") != null && !param.get("userId").toString().equals("")) { // 사용자 아이디
			booleanBuilder.and( tAuUser.userId.contains(param.get("userId").toString()) );
		}
		if(param.get("deptCd") != null && !param.get("deptCd").toString().equals("")) { // 부서 코드
			booleanBuilder.and( tAuUser.deptCd.eq(param.get("deptCd").toString()) );
		}
		if(param.get("userNm") != null && !param.get("userNm").toString().equals("")) { // 사용자 명
			booleanBuilder.and( tAuUser.userNm.contains(param.get("userNm").toString()) );
		}
		if(param.get("clsfCd") != null && !param.get("clsfCd").toString().equals("")) { // 직급 코드
			booleanBuilder.and( tAuUser.clsfCd.contains(param.get("clsfCd").toString()) );
		}
		if(param.get("ecnyYmd") != null && !param.get("ecnyYmd").toString().equals("")) { // 입사 일
			booleanBuilder.and( tAuUser.ecnyYmd.contains(param.get("ecnyYmd").toString()) );
		}

		query = query.where(booleanBuilder).orderBy(tAuUser.userId.desc());
		final List<TAuUser> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
    }
}
