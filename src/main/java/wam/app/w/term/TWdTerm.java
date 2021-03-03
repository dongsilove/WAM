package wam.app.w.term;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import wam.app.w.domain.TWdDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import wam.app.common.CommonTbl;


/**
 * The persistent class for the t_wd_term database table.
 * 
 */
@Entity
@Table(name="t_wd_term")
@NamedQuery(name="TWdTerm.findAll", query="SELECT t FROM TWdTerm t")
@Getter @Setter
public class TWdTerm extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_WD_TERM_TERMSN_GENERATOR", sequenceName="T_WD_TERM_TERM_SN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_WD_TERM_TERMSN_GENERATOR")
	@Column(name="term_sn")
	@Schema(description ="용어 일련번호" )
	private Integer termSn;

	@Column(name="data_fom")
	@NotBlank(message="빈값을 넣을 수 없습니다.")
	@Schema(description ="데이터 형식" )
	private String dataFom;

	@Column(name="domain_sn")
	@Schema(description ="도메인 일련번호" )
	private Integer domainSn;

	@Column(name="term_en_abbr")
	@Schema(description ="용어 영문 약어" )
	private String termEnAbbr;

	@Column(name="term_en_nm")
	@Schema(description ="용어 영문 명" )
	private String termEnNm;

	@Column(name="term_nm")
	@Schema(description ="용어 명" )
	private String termNm;

	@Column(name="term_se_nm")
	@Schema(description ="용어 구분 명" )
	private String termSeNm;
	
	@Column(name="term_dc")
	@Schema(description ="용어 설명" )
	private String termDc;

	@Column(name="prjct_sn")
	@Schema(description ="프로젝트 일련번호" )
	private Integer prjctSn;
	
	
	//@ManyToOne(fetch = FetchType.LAZY) 
	//@JoinColumn(name = "domain_sn", insertable=false) //  org.hibernate.MappingException: Repeated column in mapping for entity
	//private TWdDomain tWdDomain;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(column = @JoinColumn(referencedColumnName = "domain_sn", name = "domain_sn", insertable = false, updatable = false)) })
	private TWdDomain tWdDomain;

	public TWdTerm() {
	}


	/*
	 * public String getTermDc() { return termDc; }
	 * 
	 * public void setTermDc(String termDc) { this.termDc = termDc; }
	 * 
	 * public Integer getTermSn() { return this.termSn; }
	 * 
	 * public void setTermSn(Integer termSn) { this.termSn = termSn; }
	 * 
	 * public String getDataFom() { return this.dataFom; }
	 * 
	 * public void setDataFom(String dataFom) { this.dataFom = dataFom; }
	 * 
	 * public Integer getDomainSn() { return this.domainSn; }
	 * 
	 * public void setDomainSn(Integer domainSn) { this.domainSn = domainSn; }
	 * 
	 * public String getTermEnAbbr() { return this.termEnAbbr; }
	 * 
	 * public void setTermEnAbbr(String termEnAbbr) { this.termEnAbbr = termEnAbbr;}
	 * 
	 * public String getTermEnNm() { return this.termEnNm; }
	 * 
	 * public void setTermEnNm(String termEnNm) { this.termEnNm = termEnNm; }
	 * 
	 * public String getTermNm() { return this.termNm; }
	 * 
	 * public void setTermNm(String termNm) { this.termNm = termNm; }
	 * 
	 * public Integer getPrjctSn() { return prjctSn; }
	 * 
	 * public void setPrjctSn(Integer prjctSn) { this.prjctSn = prjctSn; }
	 * 
	 * public String getTermSeNm() { return termSeNm; }
	 * 
	 * public void setTermSeNm(String termSeNm) { this.termSeNm = termSeNm; }
	 * 
	 * public TWdDomain gettWdDomain() { return tWdDomain; }
	 * 
	 * public void settWdDomain(TWdDomain tWdDomain) { this.tWdDomain = tWdDomain; }
	 */


}