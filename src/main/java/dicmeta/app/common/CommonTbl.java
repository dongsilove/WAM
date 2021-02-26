package dicmeta.app.common;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public abstract class CommonTbl {
	//@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@CreatedDate
	@Column(name="regist_dt")
	@Schema(description ="등록 일시" )
	protected LocalDateTime registDt;

	@Column(name="regist_id")
	@CreatedBy
	@Schema(description ="등록 아이디" )
	protected String registId;

	//@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
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
