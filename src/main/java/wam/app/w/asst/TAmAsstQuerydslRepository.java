package wam.app.w.asst;

import java.math.BigDecimal;
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
public class TAmAsstQuerydslRepository extends QuerydslRepositorySupport {
	
	//private final JPAQueryFactory queryFactory;
	private QTAmAsst tAmAsst = QTAmAsst.tAmAsst;
	
	public TAmAsstQuerydslRepository(JPAQueryFactory queryFactory) {
		super(TAmAsst.class);
		//this.queryFactory = queryFactory;
	}


    public Page<TAmAsst> findList(Map<String,Object> param, Pageable pageable){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		JPQLQuery<TAmAsst> query= from(tAmAsst);
		
		if(param.get("asstSn") != null && !param.get("asstSn").toString().equals("")) { 
			Integer asstSn = Integer.parseInt(param.get("asstSn").toString());
			booleanBuilder.and( tAmAsst.asstSn.eq(asstSn) );
		}
		if(param.get("nowUslfsvcCo") != null && !param.get("nowUslfsvcCo").toString().equals("")) { 
			BigDecimal nowUslfsvcCo = new BigDecimal(param.get("nowUslfsvcCo").toString());
			booleanBuilder.and( tAmAsst.nowUslfsvcCo.eq(nowUslfsvcCo) );
		}
		if(param.get("asstNm") != null && !param.get("asstNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.asstNm.contains(param.get("asstNm").toString()) );
		}
		if(param.get("asstAccntNov") != null && !param.get("asstAccntNov").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.asstAccntNov.contains(param.get("asstAccntNov").toString()) );
		}
		if(param.get("asstAccntSclasNm") != null && !param.get("asstAccntSclasNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.asstAccntSclasNm.contains(param.get("asstAccntSclasNm").toString()) );
		}
		if(param.get("locplcNm") != null && !param.get("locplcNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.locplcNm.contains(param.get("locplcNm").toString()) );
		}
		if(param.get("psitnNm") != null && !param.get("psitnNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.psitnNm.contains(param.get("psitnNm").toString()) );
		}
		if(param.get("frstAcqsYmd") != null && !param.get("frstAcqsYmd").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.frstAcqsYmd.contains(param.get("frstAcqsYmd").toString()) );
		}
		if(param.get("revalYmd") != null && !param.get("revalYmd").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.revalYmd.contains(param.get("revalYmd").toString()) );
		}
		if(param.get("prcNm") != null && !param.get("prcNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.prcNm.contains(param.get("prcNm").toString()) );
		}
		if(param.get("worktypeNm") != null && !param.get("worktypeNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.worktypeNm.contains(param.get("worktypeNm").toString()) );
		}
		if(param.get("splsysNm") != null && !param.get("splsysNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.splsysNm.contains(param.get("splsysNm").toString()) );
		}
		if(param.get("splsysLocplcNm") != null && !param.get("splsysLocplcNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.splsysLocplcNm.contains(param.get("splsysLocplcNm").toString()) );
		}
		if(param.get("asstAccntLclasNm") != null && !param.get("asstAccntLclasNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsst.asstAccntLclasNm.contains(param.get("asstAccntLclasNm").toString()) );
		}

		query = query.where(booleanBuilder).orderBy(tAmAsst.asstSn.desc());
		final List<TAmAsst> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
    }
}
