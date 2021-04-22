package wam.app.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class CommonTbl {
	//@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@CreatedDate
	@Column(name="regist_dt")
	@Schema(description ="등록 일시" )
	protected LocalDateTime registDt;

	@Column(name="regist_id")
	@CreatedBy
	@Schema(description ="등록 아이디" )
	protected String registId;

	//@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@LastModifiedDate
	@Column(name="modify_dt")
	@Schema(description ="수정 일시" )
	protected LocalDateTime modifyDt;

	@Column(name="modify_id")
	@LastModifiedBy
	@Schema(description ="수정 아이디" )
	protected String modifyId;

	/*
	 * public String getRegistId() { return registId; }
	 * 
	 * public void setRegistId(String registId) { this.registId = registId; }
	 * 
	 * public String getModifyId() { return modifyId; }
	 * 
	 * public void setModifyId(String modifyId) { this.modifyId = modifyId; }
	 * 
	 * public LocalDateTime getRegistDt() { return registDt; }
	 * 
	 * public void setRegistDt(LocalDateTime registDt) { this.registDt = registDt; }
	 * 
	 * public LocalDateTime getModifyDt() { return modifyDt; }
	 * 
	 * public void setModifyDt(LocalDateTime modifyDt) { this.modifyDt = modifyDt; }
	 */

	
}
