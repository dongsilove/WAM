package wam.app.w.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import wam.app.w.dept.TAuDept;
import wam.app.w.prjct.TCmPrjct;
import wam.app.w.userGrp.TAuUserGrp;


/**
 * The persistent class for the t_au_user database table.
 * 
 */
@Entity
@Getter @Setter
@Table(name="t_au_user")
@NamedQuery(name="TAuUser.findAll", query="SELECT t FROM TAuUser t")
public class TAuUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	@Schema(description ="사용자아이디" )
	private String userId;

	@Column(name="clsf_cd")
	@Schema(description ="사용자그룹코드" )
	private String clsfCd;

	@Column(name="dept_cd")
	@Schema(description ="부서코드" )
	private String deptCd;

	@Column(name="ecny_ymd")
	@Schema(description ="입사일" )
	private String ecnyYmd;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(description ="비밀번호" )
	private String pwd;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name="pwd_salt")
	@Schema(description ="비밀번호SALT" )
	private String pwdSalt;

	@Column(name="user_nm")
	@Schema(description ="사용자명" )
	private String userNm;

	@Column(name="prjct_sn")
	@Schema(description ="프로젝트일련번호" )
	private Integer prjctSn;
	
	@Column(name="usergrp_cd")
	@Schema(description ="사용자그룹코드" )
	private String usergrpCd;
	
	@Transient
	@Schema(description ="점검비밀번호" )
	private String checkPwd; // 값이 있다면 개인정보 변경 화면임을 나타냄

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(column = @JoinColumn(referencedColumnName = "dept_cd", name = "dept_cd", insertable = false, updatable = false)) })
	private TAuDept tAuDept;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(column = @JoinColumn(referencedColumnName = "prjct_sn", name = "prjct_sn", insertable = false, updatable = false)) })
	private TCmPrjct tCmPrjct;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(column = @JoinColumn(referencedColumnName = "usergrp_cd", name = "usergrp_cd", insertable = false, updatable = false)) })
	private TAuUserGrp tAuUserGrp;
	
	public TAuUser() {
	}

	
	@Override
	public String toString() {
		return "TAuUser [userId=" + userId + ", clsfCd=" + clsfCd + ", deptCd=" + deptCd + ", ecnyYmd=" + ecnyYmd
				+ ", pwd=" + pwd + ", pwdSalt=" + pwdSalt + ", userNm=" + userNm + ", checkPwd=" + checkPwd
				+ ", tAuDept.deptCd=" + tAuDept.getDeptCd() + "]";
	}



}