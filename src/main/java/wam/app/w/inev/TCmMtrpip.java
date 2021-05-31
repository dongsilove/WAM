package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import wam.app.common.CommonTbl;


/**
 * The persistent class for the t_cm_mtrpip database table.
 * 
 */
@Entity
@Table(name="t_cm_mtrpip")
@NamedQuery(name="TCmMtrpip.findAll", query="SELECT t FROM TCmMtrpip t")
public class TCmMtrpip extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CM_MTRPIP_MTRPIPCD_GENERATOR", sequenceName="T_CM_MTRPIP_MTRPIP_CD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CM_MTRPIP_MTRPIPCD_GENERATOR")
	@Column(name="mtrpip_cd")
	private String mtrpipCd;

	@Column(name="innsf_clth_mtrqlt")
	private String innsfClthMtrqlt;

	private String mtrqlt;

	@Column(name="outsf_clth_mtrqlt")
	private String outsfClthMtrqlt;

	public TCmMtrpip() {
	}

	public String getMtrpipCd() {
		return this.mtrpipCd;
	}

	public void setMtrpipCd(String mtrpipCd) {
		this.mtrpipCd = mtrpipCd;
	}

	public String getInnsfClthMtrqlt() {
		return this.innsfClthMtrqlt;
	}

	public void setInnsfClthMtrqlt(String innsfClthMtrqlt) {
		this.innsfClthMtrqlt = innsfClthMtrqlt;
	}

	public String getMtrqlt() {
		return this.mtrqlt;
	}

	public void setMtrqlt(String mtrqlt) {
		this.mtrqlt = mtrqlt;
	}

	public String getOutsfClthMtrqlt() {
		return this.outsfClthMtrqlt;
	}

	public void setOutsfClthMtrqlt(String outsfClthMtrqlt) {
		this.outsfClthMtrqlt = outsfClthMtrqlt;
	}

}