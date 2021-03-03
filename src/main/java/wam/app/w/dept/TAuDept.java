package wam.app.w.dept;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_au_dept database table.
 * 
 */
@Entity
@Table(name="t_au_dept")
@NamedQuery(name="TAuDept.findAll", query="SELECT t FROM TAuDept t")
public class TAuDept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dept_cd")
	private String deptCd;

	@Column(name="dept_nm")
	private String deptNm;

	@Column(name="upper_dept_cd")
	private String upperDeptCd;

	public TAuDept() {
	}

	public String getDeptCd() {
		return this.deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getDeptNm() {
		return this.deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getUpperDeptCd() {
		return this.upperDeptCd;
	}

	public void setUpperDeptCd(String upperDeptCd) {
		this.upperDeptCd = upperDeptCd;
	}

}