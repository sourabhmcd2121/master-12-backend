package com.master.app.pims.entities.schemas.property;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.master.app.pims.entities.schemas.master.OrgPrimary;

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
@Table(name = "mst_use_factor", schema="property")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "PropertyUseFactor.findAll", query = "SELECT g FROM PropertyUseFactor g")})
public class PropertyUseFactor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "use_factor_guid")
    private String useFactorGuid;
    
    @Column(name = "use_factor_code")
    private String useFactorCode;
    
    @Column(name = "use_factor_name_en")
    private String useFactorNameEn;
    
    @Column(name = "use_factor_name_hi")
    private String useFactorNameHi;
    
    @Column(name = "use_factor_name_rl")
    private String useFactorNameRl;
    
    @Column(name = "use_factor_description")
    private String useFactorDesc;
    
    @Column(name = "is_headquarter")
	private Boolean isHeadquarter;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "created_by")
    private String createdByGuid;
    
    @Basic(optional = false)
    @Column(name = "created_date", insertable=false,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name = "created_ip_addr")
    private String createdIpAddr;
    
    @Column(name = "created_mac_addr")
    private String createdMacAddr;
    
    @Column(name = "created_remarks")
    private String createdRemarks;
    
    @Column(name = "modified_by")
    private String modifiedByGuid;
    
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;
    
    @Column(name = "modified_mac_addr")
    private String modifiedMacAddr;
    
    @Column(name = "modified_remarks")
    private String modifedRemarks;

	public PropertyUseFactor(String useFactorGuid) {
		super();
		this.useFactorGuid = useFactorGuid;
	}

  
}
