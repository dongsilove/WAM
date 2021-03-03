package wam.app.w.user;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import wam.app.w.dept.TAuDept;
import wam.app.w.domain.TWdDomain;
import wam.app.w.prjct.TCmPrjct;


/**
 * The persistent class for the t_au_user database table.
 * 
 */
@Entity
@Table(name="t_au_user")
@NamedQuery(name="TAuUser.findAll", query="SELECT t FROM TAuUser t")
public class TAuUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name="clsf_cd")
	private String clsfCd;

	@Column(name="dept_cd")
	private String deptCd;

	@Column(name="ecny_ymd")
	private String ecnyYmd;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String pwd;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name="pwd_salt")
	private String pwdSalt;

	@Column(name="user_nm")
	private String userNm;

	@Column(name="prjct_sn")
	private Integer prjctSn;
	
	@Transient
	private String checkPwd; // 값이 있다면 개인정보 변경 화면임을 나타냄

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(column = @JoinColumn(referencedColumnName = "dept_cd", name = "dept_cd", insertable = false, updatable = false)) })
	private TAuDept tAuDept;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(column = @JoinColumn(referencedColumnName = "prjct_sn", name = "prjct_sn", insertable = false, updatable = false)) })
	private TCmPrjct tCmPrjct;
	
	public TAuUser() {
	}

	
	@Override
	public String toString() {
		return "TAuUser [userId=" + userId + ", clsfCd=" + clsfCd + ", deptCd=" + deptCd + ", ecnyYmd=" + ecnyYmd
				+ ", pwd=" + pwd + ", pwdSalt=" + pwdSalt + ", userNm=" + userNm + ", checkPwd=" + checkPwd
				+ ", tAuDept.deptCd=" + tAuDept.getDeptCd() + "]";
	}


	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClsfCd() {
		return this.clsfCd;
	}

	public void setClsfCd(String clsfCd) {
		this.clsfCd = clsfCd;
	}

	public String getDeptCd() {
		return this.deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getEcnyYmd() {
		return this.ecnyYmd;
	}

	public void setEcnyYmd(String ecnyYmd) {
		this.ecnyYmd = ecnyYmd;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdSalt() {
		return this.pwdSalt;
	}

	public void setPwdSalt(String pwdSalt) {
		this.pwdSalt = pwdSalt;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getCheckPwd() {
		return checkPwd;
	}

	public void setCheckPwd(String checkPwd) {
		this.checkPwd = checkPwd;
	}

	public TAuDept gettAuDept() {
		return tAuDept;
	}

	public void settAuDept(TAuDept tAuDept) {
		this.tAuDept = tAuDept;
	}

	public TCmPrjct gettCmPrjct() {
		return tCmPrjct;
	}


	public void settCmPrjct(TCmPrjct tCmPrjct) {
		this.tCmPrjct = tCmPrjct;
	}


	public Integer getPrjctSn() {
		return prjctSn;
	}


	public void setPrjctSn(Integer prjctSn) {
		this.prjctSn = prjctSn;
	}



}