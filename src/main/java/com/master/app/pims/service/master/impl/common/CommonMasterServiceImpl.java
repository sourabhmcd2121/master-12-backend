package com.master.app.pims.service.master.impl.common;

import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.entities.schemas.citizenmaster.BgImage;
import com.master.app.pims.entities.schemas.citizenmaster.FlashImage;
import com.master.app.pims.entities.schemas.citizenmaster.FooterMenu;
import com.master.app.pims.entities.schemas.citizenmaster.FooterRibbon;
import com.master.app.pims.entities.schemas.citizenmaster.HelplineNumbers;
import com.master.app.pims.entities.schemas.citizenmaster.LogoDeptName;
import com.master.app.pims.entities.schemas.citizenmaster.NoteMenu;
import com.master.app.pims.entities.schemas.citizenmaster.OfficerImageComment;
import com.master.app.pims.entities.schemas.citizenmaster.PhotoGallery;
import com.master.app.pims.entities.schemas.citizenmaster.SocialLinks;
import com.master.app.pims.entities.schemas.citizenmaster.TenderDetails;
import com.master.app.pims.entities.schemas.citizenmaster.TextFlash;
import com.master.app.pims.entities.schemas.citizenmaster.VideoGallery;
import com.master.app.pims.entities.schemas.citizenmaster.WebInfoManager;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.entities.schemas.intramc.IntramcRoleMenuMap;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.CommonMasterAppAlert;
import com.master.app.pims.entities.schemas.mst.CommonMasterIndustryArea;
import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeClassification;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeType;
import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoColonyMCD;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.GeoWardMCD;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.entities.schemas.mst.MstRefSla;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.RefDocsCategoryMap;
import com.master.app.pims.entities.schemas.mst.ReligiousPlaces;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SmsEmailTemplate;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;
import com.master.app.pims.entities.schemas.usr.RefUserDocsMap;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
import com.master.app.pims.repositories.citizen.AdminDetailRepo;
import com.master.app.pims.repositories.citizen.BgImageRepo;
import com.master.app.pims.repositories.citizen.FlashImageRepo;
import com.master.app.pims.repositories.citizen.FooterMenuRepo;
import com.master.app.pims.repositories.citizen.FooterRibbonRepo;
import com.master.app.pims.repositories.citizen.HelplineNumbersRepo;
import com.master.app.pims.repositories.citizen.LogoDeptNameRepo;
import com.master.app.pims.repositories.citizen.NoteMenuRepo;
import com.master.app.pims.repositories.citizen.OfficerImageCommentRepo;
import com.master.app.pims.repositories.citizen.PhotoGalleryRepo;
import com.master.app.pims.repositories.citizen.SocialLinksRepo;
import com.master.app.pims.repositories.citizen.TenderDetailsRepo;
import com.master.app.pims.repositories.citizen.TextFlashRepo;
import com.master.app.pims.repositories.citizen.VideoGalleryRepo;
import com.master.app.pims.repositories.citizen.WebInfoManagerRepo;
import com.master.app.pims.repositories.intramc.IntramcMenuMasterRepo;
import com.master.app.pims.repositories.intramc.IntramcRoleMenuMapRepo;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.repositories.master.OrgPrimaryRepository;
import com.master.app.pims.repositories.master.OrgRadiusRepository;
import com.master.app.pims.repositories.master.OrgWrapperRepository;
import com.master.app.pims.repositories.mst.CommonMasterAppAlertRepo;
import com.master.app.pims.repositories.mst.CommonMasterIndustryAreaRepo;
import com.master.app.pims.repositories.mst.CommonMasterProcessStatusRepo;
import com.master.app.pims.repositories.mst.CommonMasterTradeClassificationRepo;
import com.master.app.pims.repositories.mst.CommonMasterTradeTypeRepo;
import com.master.app.pims.repositories.mst.DocsCategoryInfoRepository;
import com.master.app.pims.repositories.mst.DocsSubmissionInfoRepository;
import com.master.app.pims.repositories.mst.EducationLevelRepository;
import com.master.app.pims.repositories.mst.GeoColonyCategoryRepository;
import com.master.app.pims.repositories.mst.GeoColonyMCDRepo;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import com.master.app.pims.repositories.mst.GeoWardMCDRepo;
import com.master.app.pims.repositories.mst.GeoZoneMCDRepository;
import com.master.app.pims.repositories.mst.MstChargeDetailsRepository;
import com.master.app.pims.repositories.mst.MstRefSlaRepo;
import com.master.app.pims.repositories.mst.OccupationTypeRepository;
import com.master.app.pims.repositories.mst.RefDocsCategoryMapRepository;
import com.master.app.pims.repositories.mst.ReligiousPlacesRepository;
import com.master.app.pims.repositories.mst.RequestSubmissionTypeRepository;
import com.master.app.pims.repositories.mst.SmsEmailTemplateRepository;
import com.master.app.pims.repositories.mst.SubmittedRequestStageRepository;
import com.master.app.pims.repositories.mst.UnitAreaRepository;
import com.master.app.pims.repositories.usr.RefUserDocsMapRepo;
import com.master.app.pims.service.master.common.CommonMasterService;
import jakarta.transaction.Transactional;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommonMasterServiceImpl implements CommonMasterService {

    @Autowired
    private GeoCountryMstRepository geoCountryMstRepository;
    
    @Autowired
    private GeoStateMasterRepository geoStateMasterRepository;
    
    @Autowired
    private GeoColonyCategoryRepository geoColonyCategoryRepository;
    
    @Autowired
    private AssessmentYearRepository assessmentYearRepository;
    
    @Autowired
    private CommonMasterAppAlertRepo commonMasterAppAlertRepo;
    
    
    @Autowired
    private ApplicationMasterRepository applicationMasterRepository;
    
    @Autowired
    private AssociatedChargesInfoRepository associatedChargesInfoRepository;
    
    @Autowired
    private DocsSubmissionInfoRepository docsSubmissionInfoRepository;
    
    @Autowired
    private RequestSubmissionTypeRepository requestSubmissionTypeRepository;
    
    @Autowired
    private SubmittedRequestStageRepository submittedRequestStageRepository;
    
    @Autowired
    private UnitAreaRepository unitAreaRepository;
    
    @Autowired
    private MstChargeDetailsRepository mstChargeDetailsRepository;
    
    @Autowired
    private OccupationTypeRepository occupationTypeRepository;
    
    @Autowired
    private EducationLevelRepository educationLevelRepository;
    
    @Autowired
    private ReligiousPlacesRepository religiousPlacesRepository;
    
    @Autowired
    private DocsCategoryInfoRepository docsCategoryInfoRepository;
    
    @Autowired
    private CommonMasterProcessStatusRepo commonMasterProcessStatusRepo;
    
    @Autowired
    private SmsEmailTemplateRepository smsEmailTemplateRepository;
    
    @Autowired
    private OrgPrimaryRepository orgPrimaryRepository;
    
    @Autowired
    private OrgWrapperRepository orgWrapperRepository;
    
    @Autowired
    private GeoZoneMCDRepository geoZoneMCDRepository;
    
    @Autowired
	private OrgRadiusRepository orgRadiusRepository;
    
    @Autowired
   	private RefDocsCategoryMapRepository refDocsCategoryMapRepository;
    
    @Autowired
   	private RefUserDocsMapRepo refUserDocsMapRepo;

    @Autowired
   	private GeoWardMCDRepo geoWardMCDRepo;
   
    @Autowired
  	private GeoColonyMCDRepo geoColonyMCDRepo;
    
    @Autowired
   	private IntramcMenuMasterRepo intramcMenuMasterRepo;
    
    @Autowired
   	private IntramcRoleMenuMapRepo intramcRoleMenuMapRepo;
    
    @Autowired
   	private CommonMasterTradeClassificationRepo tradeClassificationRepo;
    
    @Autowired
   	private CommonMasterTradeTypeRepo commonMasterTradeTypeRepo;
    
    @Autowired
   	private CommonMasterIndustryAreaRepo industryAreaRepo;
    
    @Autowired
   	private MstRefSlaRepo refSlaRepo;
    
    @Autowired
   	private AdminDetailRepo adminDetailRepo;
    
    @Autowired
   	private FooterRibbonRepo footerRibbonRepo;
    
    @Autowired
   	private FooterMenuRepo footerMenuRepo;
    
    @Autowired
   	private HelplineNumbersRepo helplineNumbersRepo;
    
    @Autowired
   	private LogoDeptNameRepo logoDeptNameRepo;
    
    @Autowired
   	private NoteMenuRepo noteMenuRepo;
    
    @Autowired
   	private PhotoGalleryRepo photoGalleryRepo;
    
    @Autowired
   	private SocialLinksRepo socialLinksRepo;
    
    @Autowired
   	private OfficerImageCommentRepo officerImageCommentRepo;
    
    @Autowired
   	private TextFlashRepo textFlashRepo;
    
    @Autowired
   	private FlashImageRepo flashImageRepo;
    
    @Autowired
   	private BgImageRepo bgImageRepo;
    
    @Autowired
   	private TenderDetailsRepo tenderDetailsRepo;
    
    @Autowired
   	private WebInfoManagerRepo webInfoManagerRepo;
    
    @Autowired
   	private VideoGalleryRepo videoGalleryRepo;
    
    
    
    @Override
    public GeoCountryMst saveGeoCountryMst(GeoCountryMst geoCountryMst) {
        return geoCountryMstRepository.save(geoCountryMst);
    }

    @Override
    public GeoCountryMst getGeoCountryMstById(String id) {
        return geoCountryMstRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));
    }

	@Override
	public GeoStateMaster saveGeoStateMaster(GeoStateMaster geoStateMaster) {
		// TODO Auto-generated method stub
		return geoStateMasterRepository.save(geoStateMaster);
	}

	@Override
	public GeoStateMaster getGeoStateMasterById(String id) {
		// TODO Auto-generated method stub
		
        return geoStateMasterRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public GeoColonyCategory saveGeoColonyCategory(GeoColonyCategory geoColonyCategory) {
		 return geoColonyCategoryRepository.save(geoColonyCategory);
	}

	@Override
	public GeoColonyCategory getGeoColonyCategoryById(String id) {
        return geoColonyCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public ApplicationMaster saveApplicationMaster(ApplicationMaster appMaster) {
		 return applicationMasterRepository.save(appMaster);

	}

	@Override
	public ApplicationMaster getApplicationMasterById(String id) {
        return applicationMasterRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public AssessmentYear saveAssessmentYear(AssessmentYear assessmentYear) {
		 return assessmentYearRepository.save(assessmentYear);
	}

	@Override
	public AssessmentYear getAssessmentYearById(String id) {
        return assessmentYearRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public AssociatedChargesInfo saveAssociatedChargesInfo(AssociatedChargesInfo associatedChargesInfo) {
		 return associatedChargesInfoRepository.save(associatedChargesInfo);
	}

	@Override
	public AssociatedChargesInfo getAssociatedChargesInfoById(String id) {
        return associatedChargesInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public DocsSubmissionInfo saveDocsSubmissionInfo(DocsSubmissionInfo docsSubmissionInfo) {
		 return docsSubmissionInfoRepository.save(docsSubmissionInfo);
	}

	@Override
	public DocsSubmissionInfo getDocsSubmissionInfoById(String id) {
        return docsSubmissionInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RequestSubmissionType saveRequestSubmissionType(RequestSubmissionType requestSubmissionType) {
		 return requestSubmissionTypeRepository.save(requestSubmissionType);

	}

	@Override
	public RequestSubmissionType getRequestSubmissionTypeById(String id) {
        return requestSubmissionTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public SubmittedRequestStage saveSubmittedRequestStage(SubmittedRequestStage submittedRequestStage) {
		 return submittedRequestStageRepository.save(submittedRequestStage);

	}

	@Override
	public SubmittedRequestStage getSubmittedRequestStageById(String id) {
        return submittedRequestStageRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public UnitArea saveUnitArea(UnitArea unitArea) {
		 return unitAreaRepository.save(unitArea);

	}

	@Override
	public UnitArea getUnitAreaById(String id) {
        return unitAreaRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public MstChargeDetails saveMstChargeDetails(MstChargeDetails mstChargeDetails) {
		 return mstChargeDetailsRepository.save(mstChargeDetails);
	}

	@Override
	public MstChargeDetails getMstChargeDetailsById(String id) {
        return mstChargeDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public OccupationType saveOccupationType(OccupationType occupationType) {
		 return occupationTypeRepository.save(occupationType);

	}

	@Override
	public OccupationType getOccupationTypeById(String id) {
        return occupationTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public EducationLevel saveEducationLevel(EducationLevel educationLevel) {
		 return educationLevelRepository.save(educationLevel);
	}

	@Override
	public EducationLevel getEducationLevelById(String id) {
        return educationLevelRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public ReligiousPlaces saveReligiousPlaces(ReligiousPlaces religiousPlaces) {
		 return religiousPlacesRepository.save(religiousPlaces);
	}
	
	@Override
	public ReligiousPlaces getReligiousPlacesById(String id) {
        return religiousPlacesRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}
	
	@Override
	public DocsCategoryInfo saveDocsCategoryInfo(DocsCategoryInfo docsCategoryInfo) {
		 return docsCategoryInfoRepository.save(docsCategoryInfo);
	}

	@Override
	public DocsCategoryInfo getDocsCategoryInfoById(String id) {
        return docsCategoryInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public CommonMasterProcessStatus saveCommonMasterProcessStatus(
			CommonMasterProcessStatus commonMasterProcessStatus) {
		 return commonMasterProcessStatusRepo.save(commonMasterProcessStatus);
	}

	@Override
	public CommonMasterProcessStatus getCommonMasterProcessStatusById(String id) {
        return commonMasterProcessStatusRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public SmsEmailTemplate saveSmsEmailTemplate(SmsEmailTemplate smsEmailTemplate) {
		 return smsEmailTemplateRepository.save(smsEmailTemplate);
	}
	
	@Override
	public SmsEmailTemplate getSmsEmailTemplateById(String id) {
        return smsEmailTemplateRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public OrgPrimary saveOrgPrimary(OrgPrimary orgPrimary) {
		 return orgPrimaryRepository.save(orgPrimary);
	}

	@Override
	public OrgPrimary getOrgPrimaryById(String id) {
        return orgPrimaryRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public OrgWrapper saveOrgWrapper(OrgWrapper orgWrapper) {
		 return orgWrapperRepository.save(orgWrapper);
	}

	@Override
	public OrgWrapper getOrgWrapperById(String id) {
        return orgWrapperRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public GeoZoneMCD saveGeoZoneMCD(GeoZoneMCD geoZoneMCD) {
		 return geoZoneMCDRepository.save(geoZoneMCD);
	}

	@Override
	public GeoZoneMCD getGeoZoneMCDById(String id) {
        return geoZoneMCDRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public OrgRadius saveOrgRadius(OrgRadius orgRadius) {
		 return orgRadiusRepository.save(orgRadius);
	}

	@Override
	public OrgRadius getOrgRadiusById(String id) {
        return orgRadiusRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RefDocsCategoryMap saveRefDocsCategoryMap(RefDocsCategoryMap refDocsCategoryMap) {
		 return refDocsCategoryMapRepository.save(refDocsCategoryMap);
	}

	@Override
	public RefDocsCategoryMap getRefDocsCategoryMapById(String id) {
        return refDocsCategoryMapRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RefUserDocsMap saveRefUserDocsMap(RefUserDocsMap refUserDocsMap) {
		 return refUserDocsMapRepo.save(refUserDocsMap);
	}

	@Override
	public RefUserDocsMap getRefUserDocsMapById(String id) {
        return refUserDocsMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public GeoWardMCD saveGeoWardMCD(GeoWardMCD geoWardMCD) {
		 return geoWardMCDRepo.save(geoWardMCD);
	}
	
	@Override
	public GeoWardMCD getGeoWardMCDById(String id) {
        return geoWardMCDRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public GeoColonyMCD saveGeoColonyMCD(GeoColonyMCD geoColonyMCD) {
		 return geoColonyMCDRepo.save(geoColonyMCD);
	}
	
	@Override
	public GeoColonyMCD getGeoColonyMCDById(String id) {
        return geoColonyMCDRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public CommonMasterAppAlert saveCommonMasterAppAlert(CommonMasterAppAlert commonMasterAppAlert) {
		 return commonMasterAppAlertRepo.save(commonMasterAppAlert);
	}

	@Override
	public CommonMasterAppAlert getCommonMasterAppAlertById(String id) {
        return commonMasterAppAlertRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public IntramcMenuMaster saveIntramcMenuMaster(IntramcMenuMaster intramcMenuMaster) {
		 return intramcMenuMasterRepo.save(intramcMenuMaster);
	}
	
	@Override
	public IntramcMenuMaster getIntramcMenuMasterById(String id) {
        return intramcMenuMasterRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public IntramcRoleMenuMap saveIntramcRoleMenuMap(IntramcRoleMenuMap intramcRoleMenuMap) {
		 return intramcRoleMenuMapRepo.save(intramcRoleMenuMap);
	}
	
	@Override
	public IntramcRoleMenuMap getIntramcRoleMenuMapById(String id) {
        return intramcRoleMenuMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public CommonMasterTradeClassification saveCommonMasterTradeClassification(
			CommonMasterTradeClassification tradeClassification) {
		 return tradeClassificationRepo.save(tradeClassification);
	}

	@Override
	public CommonMasterTradeClassification getCommonMasterTradeClassificationById(String id) {
        return tradeClassificationRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public CommonMasterTradeType saveCommonMasterTradeType(CommonMasterTradeType commonMasterTradeType) {
		 return commonMasterTradeTypeRepo.save(commonMasterTradeType);
	}

	@Override
	public CommonMasterTradeType getCommonMasterTradeTypeById(String id) {
        return commonMasterTradeTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public CommonMasterIndustryArea saveCommonMasterIndustryArea(CommonMasterIndustryArea industryArea) {
		 return industryAreaRepo.save(industryArea);

	}

	@Override
	public CommonMasterIndustryArea getCommonMasterIndustryAreaById(String id) {
        return industryAreaRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}
	
	@Override
	public MstRefSla saveMstRefSla(MstRefSla refSla) {
		 return refSlaRepo.save(refSla);
	}

	@Override
	public MstRefSla getMstRefSlaById(String id) {
        return refSlaRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}
	
	
	/////////////////////////////////Portal Master/////////////////////////////

	@Override
	public AdminDetail saveAdminDetail(AdminDetail adminDetail) {
		 return adminDetailRepo.save(adminDetail);
	}

	@Override
	public AdminDetail getAdminDetailById(String id) {
        return adminDetailRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public FooterRibbon saveFooterRibbon(FooterRibbon footerRibbon) {
		 return footerRibbonRepo.save(footerRibbon);
	}

	@Override
	public FooterRibbon getFooterRibbonById(String id) {
        return footerRibbonRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public FooterMenu saveFooterMenu(FooterMenu footerMenu) {
		 return footerMenuRepo.save(footerMenu);
	}

	@Override
	public FooterMenu getFooterMenuById(String id) {
        return footerMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public HelplineNumbers saveHelplineNumbers(HelplineNumbers helplineNumbers) {
		 return helplineNumbersRepo.save(helplineNumbers);
	}

	@Override
	public HelplineNumbers getHelplineNumbersById(String id) {
        return helplineNumbersRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public LogoDeptName saveLogoDeptName(LogoDeptName logoDeptName) {
		 return logoDeptNameRepo.save(logoDeptName);
	}

	@Override
	public LogoDeptName getLogoDeptNameById(String id) {
        return logoDeptNameRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public NoteMenu saveNoteMenu(NoteMenu noteMenu) {
		 return noteMenuRepo.save(noteMenu);
	}

	@Override
	public NoteMenu getNoteMenuById(String id) {
        return noteMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PhotoGallery savePhotoGallery(PhotoGallery photoGallery) {
		 return photoGalleryRepo.save(photoGallery);
	}

	@Override
	public PhotoGallery getPhotoGalleryById(String id) {
        return photoGalleryRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public SocialLinks saveSocialLinks(SocialLinks socialLinks) {
		 return socialLinksRepo.save(socialLinks);
	}

	@Override
	public SocialLinks getSocialLinksById(String id) {
        return socialLinksRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public OfficerImageComment saveOfficerImageComment(OfficerImageComment officerImageComment) {
		 return officerImageCommentRepo.save(officerImageComment);
	}

	@Override
	public OfficerImageComment getOfficerImageCommentById(String id) {
        return officerImageCommentRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public TextFlash saveTextFlash(TextFlash textFlash) {
		 return textFlashRepo.save(textFlash);
	}

	@Override
	public TextFlash getTextFlashById(String id) {
        return textFlashRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public FlashImage saveFlashImage(FlashImage flashImage) {
		 return flashImageRepo.save(flashImage);
	}

	@Override
	public FlashImage getFlashImageById(String id) {
        return flashImageRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public BgImage saveBgImage(BgImage bgImage) {
		 return bgImageRepo.save(bgImage);
	}

	@Override
	public BgImage getBgImageById(String id) {
        return bgImageRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public TenderDetails saveTenderDetails(TenderDetails tenderDetails) {
		 return tenderDetailsRepo.save(tenderDetails);
	}

	@Override
	public TenderDetails getTenderDetailsById(String id) {
        return tenderDetailsRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public WebInfoManager saveWebInfoManager(WebInfoManager webInfoManager) {
		 return webInfoManagerRepo.save(webInfoManager);
	}

	@Override
	public WebInfoManager getWebInfoManagerById(String id) {
        return webInfoManagerRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public VideoGallery saveVideoGallery(VideoGallery videoGallery) {
		 return videoGalleryRepo.save(videoGallery);
	}

	@Override
	public VideoGallery getVideoGalleryById(String id) {
        return videoGalleryRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	
	
	
	
}
