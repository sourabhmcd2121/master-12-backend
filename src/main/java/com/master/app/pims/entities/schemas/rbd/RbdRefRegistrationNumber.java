package com.master.app.pims.entities.schemas.rbd;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.PersRelation;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;

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
@Table(name = "ref_registration_number", schema = "rbd")
public class RbdRefRegistrationNumber implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ref_registration_number_guid")
	private String refRegistrationNumberGuid;

	@Transient
    private String assessmentYear;

	@Transient
	private String orgPrimary;

	@Transient
    private String zone;

    @Column(name = "submitted_for_type")
    private String submittedForType;

    @Column(name = "generated_number")
    @ColumnDefault("1")
    private Long generatedNumber;

    @Column(name = "last_generated_number")
    @ColumnDefault("00000001")
    private Long lastGeneratedNumber;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "created_ip_addr")
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

    @Transient
    private String  assessmentYearGuid;
    
    @Transient
    private String  orgPrimaryGuid;
    
    @Transient
    private String zoneGuid;

	@JoinColumn(name = "assessment_year_guid", referencedColumnName = "assessment_year_guid")
    @ManyToOne(optional = false)
    private AssessmentYear assessmentYearMaster;
	
	 @JoinColumn(name = "applied_primary_org_guid", referencedColumnName = "org_primary_guid")
	    @ManyToOne(optional = false)
	    private OrgPrimary orgPrimaryMaster;
	 
	 @JoinColumn(name = "applied_wrapper_org_guid", referencedColumnName = "zone_guid")
	    @ManyToOne(optional = false)
	    private GeoZoneMCD zoneMaster;

	public RbdRefRegistrationNumber(String refRegistrationNumberGuid) {
		super();
		this.refRegistrationNumberGuid = refRegistrationNumberGuid;
	}

	

}
