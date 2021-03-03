package wam.app.w.prjct;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_cm_prjct database table.
 * 
 */
@Entity
@Table(name="t_cm_prjct")
@NamedQuery(name="TCmPrjct.findAll", query="SELECT t FROM TCmPrjct t")
public class TCmPrjct extends wam.app.common.CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CM_PRJCT_PRJCTSN_GENERATOR", sequenceName="T_CM_PRJCT_PRJCT_SN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CM_PRJCT_PRJCTSN_GENERATOR")
	@Column(name="prjct_sn")
	private Integer prjctSn;

	@Column(name="prjct_begin_ymd")
	private String prjctBeginYmd;

	@Column(name="prjct_dc")
	private String prjctDc;

	@Column(name="prjct_end_ymd")
	private String prjctEndYmd;

	@Column(name="prjct_nm")
	private String prjctNm;

	public TCmPrjct() {
	}

	public Integer getPrjctSn() {
		return this.prjctSn;
	}

	public void setPrjctSn(Integer prjctSn) {
		this.prjctSn = prjctSn;
	}

	public String getPrjctBeginYmd() {
		return this.prjctBeginYmd;
	}

	public void setPrjctBeginYmd(String prjctBeginYmd) {
		this.prjctBeginYmd = prjctBeginYmd;
	}

	public String getPrjctDc() {
		return this.prjctDc;
	}

	public void setPrjctDc(String prjctDc) {
		this.prjctDc = prjctDc;
	}

	public String getPrjctEndYmd() {
		return this.prjctEndYmd;
	}

	public void setPrjctEndYmd(String prjctEndYmd) {
		this.prjctEndYmd = prjctEndYmd;
	}

	public String getPrjctNm() {
		return this.prjctNm;
	}

	public void setPrjctNm(String prjctNm) {
		this.prjctNm = prjctNm;
	}

}