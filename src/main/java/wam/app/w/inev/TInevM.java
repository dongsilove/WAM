package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import wam.app.common.CommonTbl;


/**
 * The persistent class for the t_inev_m database table.
 * 
 */
@Entity
@Table(name="t_inev_m")
@NamedQuery(name="TInevM.findAll", query="SELECT t FROM TInevM t")
public class TInevM extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_INEV_M_EVLSN_GENERATOR", sequenceName="T_INEV_M_EVL_SN",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_INEV_M_EVLSN_GENERATOR")
	@Column(name="evl_sn")
	private Integer evlSn;

	@Column(name="evl_knd")
	private String evlKnd;

	@Column(name="evl_ymd")
	private String evlYmd;

	public TInevM() {
	}

	public Integer getEvlSn() {
		return this.evlSn;
	}

	public void setEvlSn(Integer evlSn) {
		this.evlSn = evlSn;
	}

	public String getEvlKnd() {
		return this.evlKnd;
	}

	public void setEvlKnd(String evlKnd) {
		this.evlKnd = evlKnd;
	}

	public String getEvlYmd() {
		return this.evlYmd;
	}

	public void setEvlYmd(String evlYmd) {
		this.evlYmd = evlYmd;
	}

}