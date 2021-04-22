package wam.app.w.dept;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the t_au_dept database table.
 * 
 */
@Entity
@Getter @Setter
@Table(name="t_au_dept")
@NamedQuery(name="TAuDept.findAll", query="SELECT t FROM TAuDept t")
public class TAuDept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dept_cd")
	@Schema(description ="부서코드" )
	private String deptCd;

	@Column(name="dept_nm")
	@Schema(description ="부서명" )
	private String deptNm;

	@Column(name="upper_dept_cd")
	@Schema(description ="상위부서코드" )
	private String upperDeptCd;

	@Column(name="author_cn")
	@Schema(description ="권한내용" )
	private String authorCn;
	
	public TAuDept() {
	}



}