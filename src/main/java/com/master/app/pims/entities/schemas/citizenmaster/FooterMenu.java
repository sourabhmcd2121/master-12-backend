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
@Table(name="footer_menu", schema="citizen_app")
public class FooterMenu implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name="footer_menu_guid", unique=true, nullable=false, length=36)
    private String footerMenuGuid;
    
    @Transient
    private MultipartFile fileName;
    
    @Column(name="pdf_file_name")
    private String pdfFileName1;
    
    @Column(name="footer_menu_name_en", nullable=false)
    private String footerMenuNameEn;
    
    @Column(name="footer_menu_name_hi", nullable=false)
    private String footerMenuNameHi;
    
    @Column(name="footer_menu_name_rl")
    private String footerMenuNameRl;
    
    @Column(name="footer_menu_content_html_en")
    private String footerMenuContentHtmlEn;
    
    @Column(name="footer_menu_content_html_hi")
    private String footerMenuContentHtmlHi;
    
    @Column(name="footer_menu_content_html_rl")
    private String footerMenuContentHtmlRl;
    
    @Column(name="footer_menu_url", nullable=false)
    private String footerMenuUrl;

    @Column(name="order_number", precision=19, nullable=false)
    private Long orderNumber;
    

    @Column(name="is_active", precision=19, nullable=false)
    private Boolean isActive;
    
    @Column(name="created_by", nullable=false)
    private String createdBy;

    @Column(name="created_date", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name="created_ip_addr", nullable=false)
    private String createdIpAddr;
    
    @Column(name="created_mac_addr")
    private String createdMacAddr;
    
    @Column(name="created_remarks")
    private String createdRemarks;
    
    @Column(name="modified_by")
    private String modifiedBy;
    
    @Column(name="modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name="modified_ip_addr")
    private String modifiedIpAddr;
    
    @Column(name="modified_mac_addr")
    private String modifiedMacAddr;
    
    @Column(name="modified_remarks")
    private String modifiedRemarks;
    
  
    
   

 

  
}
