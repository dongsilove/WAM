package wam.app.w.cdGrp;

import java.io.Serializable;
import javax.persistence.*;

import wam.app.common.CommonTbl;


/**
 * The persistent class for the t_cm_cd_grp database table.
 * 
 */
@Entity
@Table(name="t_cm_cd_grp")
@NamedQuery(name="TCmCdGrp.findAll", query="SELECT t FROM TCmCdGrp t")
public class TCmCdGrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="grp_cd")
	private String grpCd;

	@Column(name="grp_cd_nm")
	private String grpCdNm;

	@Column(name="table_nm")
	private String tableNm;
	
	@Column(name="column_nm")
	private String columnNm;
	
	public TCmCdGrp() {
	}

	public String getGrpCd() {
		return this.grpCd;
	}

	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}

	public String getGrpCdNm() {
		return this.grpCdNm;
	}

	public void setGrpCdNm(String grpCdNm) {
		this.grpCdNm = grpCdNm;
	}

	public String getTableNm() {
		return tableNm;
	}

	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}

	public String getColumnNm() {
		return columnNm;
	}

	public void setColumnNm(String columnNm) {
		this.columnNm = columnNm;
	}

}