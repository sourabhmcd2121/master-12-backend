package com.master.app.pims.entities.schemas.citizenmaster;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note_menu", schema = "citizen_app")
public class NoteMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "note_menu_guid")
	private String noteMenuGuid;
	
	@Column(name = "note_menu_name_en", nullable = false)
	private String noteMenuNameEn;
	
	@Column(name = "note_menu_name_hi", nullable = false)
	private String noteMenuNameHi;
	
	@Column(name = "note_manu_name_rl")
	private String noteMenuNameRl;
	
	@Column(name="is_active", nullable=false, length=1)
    private Boolean isActive;
	
	@Column(name = "order_number", nullable = false)
	private long orderNumber;
	
//	@Column(name = "primary_note_menu_guid", length = 36)
//	private String primaryNoteMenuGuid;
	
	@Column(name = "created_by", nullable=false)
	private String createdBy;
	
	@Column(name = "created_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "created_ip_addr", nullable=false)
	private String createdIpAddr;
	
	@Column(name = "created_mac_addr")
	private String createdMacAddr;
	
	@Column(name = "created_remarks")
	private String createdRemarks;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	@Column(name = "modified_ip_addr")
	private String modifiedIpAddr;
	
	@Column(name = "modified_mac_addr")
	private String modifiedMacAddr;
	
	@Column(name = "modified_remarks")
	private String modifiedRemarks;
	
//	@OneToMany(mappedBy = "noteMenu")
//	private Set<Notification> notification;


}
