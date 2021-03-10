package wam.app.w.asstFclty;

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
public class TAmAsstFcltyQuerydslRepository extends QuerydslRepositorySupport {
	
	//private final JPAQueryFactory queryFactory;
	private QTAmAsstFclty tAmAsstFclty = QTAmAsstFclty.tAmAsstFclty;
	
	public TAmAsstFcltyQuerydslRepository(JPAQueryFactory queryFactory) {
		super(TAmAsstFclty.class);
		//this.queryFactory = queryFactory;
	}


    public Page<TAmAsstFclty> findList(Map<String,Object> param, Pageable pageable){
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		JPQLQuery<TAmAsstFclty> query= from(tAmAsstFclty);
		
		if(param.get("asstSn") != null && !param.get("asstSn").toString().equals("")) { 
			Integer asstSn = Integer.parseInt(param.get("asstSn").toString());
			booleanBuilder.and( tAmAsstFclty.asstSn.eq(asstSn) );
		}
		if(param.get("nowUslfsvcCo") != null && !param.get("nowUslfsvcCo").toString().equals("")) { 
			BigDecimal nowUslfsvcCo = new BigDecimal(param.get("nowUslfsvcCo").toString());
			booleanBuilder.and( tAmAsstFclty.nowUslfsvcCo.eq(nowUslfsvcCo) );
		}
		if(param.get("asstNm") != null && !param.get("asstNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.asstNm.contains(param.get("asstNm").toString()) );
		}
		if(param.get("locplcNm") != null && !param.get("locplcNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.locplcNm.contains(param.get("locplcNm").toString()) );
		}
		if(param.get("psitnNm") != null && !param.get("psitnNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.psitnNm.contains(param.get("psitnNm").toString()) );
		}
		if(param.get("frstAcqsYmd") != null && !param.get("frstAcqsYmd").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.frstAcqsYmd.contains(param.get("frstAcqsYmd").toString()) );
		}
		if(param.get("revalYmd") != null && !param.get("revalYmd").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.revalYmd.contains(param.get("revalYmd").toString()) );
		}
		if(param.get("prcNm") != null && !param.get("prcNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.prcNm.contains(param.get("prcNm").toString()) );
		}
		if(param.get("worktypeNm") != null && !param.get("worktypeNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.worktypeNm.contains(param.get("worktypeNm").toString()) );
		}
		if(param.get("splsysNm") != null && !param.get("splsysNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.splsysNm.contains(param.get("splsysNm").toString()) );
		}
		if(param.get("splsysLocplcNm") != null && !param.get("splsysLocplcNm").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.splsysLocplcNm.contains(param.get("splsysLocplcNm").toString()) );
		}
		if(param.get("modifyId") != null && !param.get("modifyId").toString().equals("")) { 
			booleanBuilder.and( tAmAsstFclty.modifyId.contains(param.get("modifyId").toString()) );
		}

		query = query.where(booleanBuilder).orderBy(tAmAsstFclty.asstSn.desc());
		final List<TAmAsstFclty> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
    }
}
