package wam.app.w.code;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import wam.app.common.CommonTbl;


/**
 * The persistent class for the t_cm_code database table.
 * 
 */
@Entity
@IdClass(TCmCodePK.class)
@Table(name="t_cm_code")
@NamedQuery(name="TCmCode.findAll", query="SELECT t FROM TCmCode t")
public class TCmCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GRP_CD")
	@Schema(description ="그룹 코드" )
	private String grpCd;
	
	@Id	
	@Column(name = "CD")
	@Schema(description ="코드" )
	private String cd;

	@Column(name="cd_nm")
	private String cdNm;

	public TCmCode() {
	}


	public String getCdNm() {
		return this.cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}


	public String getGrpCd() {
		return grpCd;
	}


	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}


	public String getCd() {
		return cd;
	}


	public void setCd(String cd) {
		this.cd = cd;
	}

	
}