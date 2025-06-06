package com.master.app.pims.service.master.impl.common;

import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.entities.schemas.citizenmaster.BgImage;
import com.master.app.pims.entities.schemas.citizenmaster.FlashImage;
import com.master.app.pims.entities.schemas.citizenmaster.FooterMenu;
import com.master.app.pims.entities.schemas.citizenmaster.FooterRibbon;
import com.master.app.pims.entities.schemas.citizenmaster.HeaderRibbon;
import com.master.app.pims.entities.schemas.citizenmaster.HelplineNumbers;
import com.master.app.pims.entities.schemas.citizenmaster.LogoDeptName;
import com.master.app.pims.entities.schemas.citizenmaster.Menu;
import com.master.app.pims.entities.schemas.citizenmaster.NoteMenu;
import com.master.app.pims.entities.schemas.citizenmaster.OfficerImageComment;
import com.master.app.pims.entities.schemas.citizenmaster.PhotoGallery;
import com.master.app.pims.entities.schemas.citizenmaster.SocialLinks;
import com.master.app.pims.entities.schemas.citizenmaster.TenderDetails;
import com.master.app.pims.entities.schemas.citizenmaster.TextFlash;
import com.master.app.pims.entities.schemas.citizenmaster.VideoGallery;
import com.master.app.pims.entities.schemas.citizenmaster.WebInfoManager;
import com.master.app.pims.entities.schemas.hospital.HospitalInfo;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.entities.schemas.intramc.IntramcRoleMenuMap;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.master.PersRelation;
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
import com.master.app.pims.entities.schemas.property.ManualReceiptSeries;
import com.master.app.pims.entities.schemas.property.OwnerCategory;
import com.master.app.pims.entities.schemas.property.OwnerType;
import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;
import com.master.app.pims.entities.schemas.property.PropertyCategory;
import com.master.app.pims.entities.schemas.property.PropertyExemption;
import com.master.app.pims.entities.schemas.property.PropertyFloor;
import com.master.app.pims.entities.schemas.property.PropertyMasterRebate;
import com.master.app.pims.entities.schemas.property.PropertyMstSr;
import com.master.app.pims.entities.schemas.property.PropertyOccupancyFactor;
import com.master.app.pims.entities.schemas.property.PropertyOtherCharges;
import com.master.app.pims.entities.schemas.property.PropertyStructureFactor;
import com.master.app.pims.entities.schemas.property.PropertyTaxCategory;
import com.master.app.pims.entities.schemas.property.PropertyType;
import com.master.app.pims.entities.schemas.property.PropertyUseFactor;
import com.master.app.pims.entities.schemas.rbd.RbdFeeRelaxationList;
import com.master.app.pims.entities.schemas.rbd.RbdMstCommonList;
import com.master.app.pims.entities.schemas.rbd.RbdMstDocsCategory;
import com.master.app.pims.entities.schemas.rbd.RbdRefBirthDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefChargeMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefDeathDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefEducationMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefOccupationMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefRegistrationNumber;
import com.master.app.pims.entities.schemas.rbd.RbdRefRelationMap;
import com.master.app.pims.entities.schemas.usr.RefUserDocsMap;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
import com.master.app.pims.repositories.citizen.AdminDetailRepo;
import com.master.app.pims.repositories.citizen.BgImageRepo;
import com.master.app.pims.repositories.citizen.FlashImageRepo;
import com.master.app.pims.repositories.citizen.FooterMenuRepo;
import com.master.app.pims.repositories.citizen.FooterRibbonRepo;
import com.master.app.pims.repositories.citizen.HeaderRibbonRepo;
import com.master.app.pims.repositories.citizen.HelplineNumbersRepo;
import com.master.app.pims.repositories.citizen.LogoDeptNameRepo;
import com.master.app.pims.repositories.citizen.MenuRepo;
import com.master.app.pims.repositories.citizen.NoteMenuRepo;
import com.master.app.pims.repositories.citizen.OfficerImageCommentRepo;
import com.master.app.pims.repositories.citizen.PhotoGalleryRepo;
import com.master.app.pims.repositories.citizen.SocialLinksRepo;
import com.master.app.pims.repositories.citizen.TenderDetailsRepo;
import com.master.app.pims.repositories.citizen.TextFlashRepo;
import com.master.app.pims.repositories.citizen.VideoGalleryRepo;
import com.master.app.pims.repositories.citizen.WebInfoManagerRepo;
import com.master.app.pims.repositories.hospital.HospitalInfoRepo;
import com.master.app.pims.repositories.intramc.IntramcMenuMasterRepo;
import com.master.app.pims.repositories.intramc.IntramcRoleMenuMapRepo;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.repositories.master.OrgPrimaryRepository;
import com.master.app.pims.repositories.master.OrgRadiusRepository;
import com.master.app.pims.repositories.master.OrgWrapperRepository;
import com.master.app.pims.repositories.master.PersRelationRepo;
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
import com.master.app.pims.repositories.property.ManualReceiptSeriesRepo;
import com.master.app.pims.repositories.property.OwnerCategoryRepo;
import com.master.app.pims.repositories.property.OwnerTypeRepo;
import com.master.app.pims.repositories.property.PropertyAgeFactorRepo;
import com.master.app.pims.repositories.property.PropertyCategoryRepo;
import com.master.app.pims.repositories.property.PropertyExemptionRepo;
import com.master.app.pims.repositories.property.PropertyFloorRepo;
import com.master.app.pims.repositories.property.PropertyMasterRebateRepo;
import com.master.app.pims.repositories.property.PropertyMstSrRepo;
import com.master.app.pims.repositories.property.PropertyOccupancyFactorRepo;
import com.master.app.pims.repositories.property.PropertyOtherChargesRepo;
import com.master.app.pims.repositories.property.PropertyStructureFactorRepo;
import com.master.app.pims.repositories.property.PropertyTaxCategoryRepo;
import com.master.app.pims.repositories.property.PropertyTypeRepo;
import com.master.app.pims.repositories.property.PropertyUseFactorRepo;
import com.master.app.pims.repositories.rbd.RbdFeeRelaxationListRepo;
import com.master.app.pims.repositories.rbd.RbdMstCommonListRepo;
import com.master.app.pims.repositories.rbd.RbdMstDocsCategoryRepo;
import com.master.app.pims.repositories.rbd.RbdRefBirthDocsMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefChargeMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefDeathDocsMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefDocsMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefEducationMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefOccupationMapRepo;
import com.master.app.pims.repositories.rbd.RbdRefRegistrationNumberRepo;
import com.master.app.pims.repositories.rbd.RbdRefRelationMapRepo;
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
    private PersRelationRepo persRelationRepo;
    
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
    
    @Autowired
   	private HeaderRibbonRepo headerRibbonRepo;
    
    @Autowired
   	private PropertyAgeFactorRepo propertyAgeFactorRepo;
    
    @Autowired
   	private PropertyExemptionRepo propertyExemptionRepo;
    
    @Autowired
   	private PropertyFloorRepo propertyFloorRepo;
    
    @Autowired
   	private PropertyOccupancyFactorRepo propertyOccupancyFactorRepo;
    
    @Autowired
   	private OwnerCategoryRepo ownerCategoryRepo;
    
    @Autowired
   	private OwnerTypeRepo ownerTypeRepo;
    
    @Autowired
   	private PropertyCategoryRepo propertyCategoryRepo;
    
    @Autowired
   	private PropertyTypeRepo propertyTypeRepo;
    
    @Autowired
   	private PropertyStructureFactorRepo propertyStructureFactorRepo;
    
    @Autowired
   	private PropertyTaxCategoryRepo propertyTaxCategoryRepo;
    
    @Autowired
   	private PropertyUseFactorRepo propertyUseFactorRepo;
    
    @Autowired
   	private PropertyMasterRebateRepo propertyMasterRebateRepo;
    
    @Autowired
   	private PropertyOtherChargesRepo propertyOtherChargesRepo;
    
    @Autowired
   	private ManualReceiptSeriesRepo manualReceiptSeriesRepo;
       
    @Autowired
   	private PropertyMstSrRepo propertyMstSrRepo;
    
    
    
    
    
    
    
    @Autowired
   	private RbdMstCommonListRepo rbdMstCommonListRepo;
    
    @Autowired
   	private RbdMstDocsCategoryRepo rbdMstDocsCategoryRepo;
    
    @Autowired
   	private RbdRefBirthDocsMapRepo rbdRefBirthDocsMapRepo;
    
    @Autowired
   	private RbdRefDeathDocsMapRepo rbdRefDeathDocsMapRepo;
    
    @Autowired
   	private RbdRefChargeMapRepo rbdRefChargeMapRepo;
    
    @Autowired
   	private RbdRefRelationMapRepo rbdRefRelationMapRepo;
    
    @Autowired
   	private RbdRefOccupationMapRepo rbdRefOccupationMapRepo;
    
    @Autowired
   	private RbdRefEducationMapRepo rbdRefEducationMapRepo;
    
    @Autowired
   	private RbdRefRegistrationNumberRepo rbdRefRegistrationNumberRepo;
    
    @Autowired
   	private RbdRefDocsMapRepo rbdRefDocsMapRepo;
    
    @Autowired
   	private RbdFeeRelaxationListRepo rbdFeeRelaxationListRepo;
    
    @Autowired
   	private HospitalInfoRepo hospitalInfoRepo;
    ///////////////////////////////////////Property Master///////////////////////////
    
    @Autowired
   	private MenuRepo menuRepo;
    
    
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

	@Override
	public HeaderRibbon saveHeaderRibbon(HeaderRibbon headerRibbon) {
		 return headerRibbonRepo.save(headerRibbon);
	}

	@Override
	public HeaderRibbon getHeaderRibbonById(String id) {
        return headerRibbonRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public Menu saveMenu(Menu menu) {
		 return menuRepo.save(menu);
	}

	@Override
	public Menu getMenuById(String id) {
        return menuRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyAgeFactor savePropertyAgeFactor(PropertyAgeFactor propertyAgeFactor) {
		 return propertyAgeFactorRepo.save(propertyAgeFactor);
	}

	@Override
	public PropertyAgeFactor getPropertyAgeFactorById(String id) {
        return propertyAgeFactorRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyExemption savePropertyExemption(PropertyExemption propertyExemption) {
		 return propertyExemptionRepo.save(propertyExemption);
	}

	@Override
	public PropertyExemption getPropertyExemptionById(String id) {
        return propertyExemptionRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyFloor savePropertyFloor(PropertyFloor propertyFloor) {
		 return propertyFloorRepo.save(propertyFloor);
	}

	@Override
	public PropertyFloor getPropertyFloorById(String id) {
        return propertyFloorRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyOccupancyFactor savePropertyOccupancyFactor(PropertyOccupancyFactor propertyOccupancyFactor) {
		 return propertyOccupancyFactorRepo.save(propertyOccupancyFactor);
	}

	@Override
	public PropertyOccupancyFactor getPropertyOccupancyFactorById(String id) {
        return propertyOccupancyFactorRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public OwnerCategory saveOwnerCategory(OwnerCategory ownerCategory) {
		 return ownerCategoryRepo.save(ownerCategory);
	}

	@Override
	public OwnerCategory getOwnerCategoryById(String id) {
        return ownerCategoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public OwnerType saveOwnerType(OwnerType ownerType) {
		 return ownerTypeRepo.save(ownerType);
	}

	@Override
	public OwnerType getOwnerTypeById(String id) {
        return ownerTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyCategory savePropertyCategory(PropertyCategory propertyCategory) {
		 return propertyCategoryRepo.save(propertyCategory);
	}

	@Override
	public PropertyCategory getPropertyCategoryById(String id) {
        return propertyCategoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyType savePropertyType(PropertyType propertyType) {
		 return propertyTypeRepo.save(propertyType);
	}

	@Override
	public PropertyType getPropertyTypeById(String id) {
        return propertyTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdMstCommonList saveRbdMstCommonList(RbdMstCommonList rbdMstCommonList) {
		 return rbdMstCommonListRepo.save(rbdMstCommonList);
	}

	@Override
	public RbdMstCommonList getRbdMstCommonListById(String id) {
        return rbdMstCommonListRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdMstDocsCategory saveRbdMstDocsCategory(RbdMstDocsCategory rbdMstDocsCategory) {
		 return rbdMstDocsCategoryRepo.save(rbdMstDocsCategory);
	}

	@Override
	public RbdMstDocsCategory getRbdMstDocsCategoryById(String id) {
        return rbdMstDocsCategoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdRefBirthDocsMap saveRbdMstDocsCategory(RbdRefBirthDocsMap rbdRefBirthDocsMap) {
		 return rbdRefBirthDocsMapRepo.save(rbdRefBirthDocsMap);
	}

	@Override
	public RbdRefBirthDocsMap getRbdRefBirthDocsMapById(String id) {
        return rbdRefBirthDocsMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdRefDeathDocsMap saveRbdRefDeathDocsMap(RbdRefDeathDocsMap rbdRefDeathDocsMap) {
		 return rbdRefDeathDocsMapRepo.save(rbdRefDeathDocsMap);
	}

	@Override
	public RbdRefDeathDocsMap getRbdRefDeathDocsMapById(String id) {
        return rbdRefDeathDocsMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdRefChargeMap saveRbdRefChargeMap(RbdRefChargeMap rbdRefChargeMap) {
		 return rbdRefChargeMapRepo.save(rbdRefChargeMap);
	}

	@Override
	public RbdRefChargeMap getRbdRefChargeMapById(String id) {
		  return rbdRefChargeMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));
	}

	@Override
	public PersRelation savePersRelation(PersRelation persRelation) {
		 return persRelationRepo.save(persRelation);
	}

	@Override
	public PersRelation getPersRelationById(String id) {
		  return persRelationRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdRefRelationMap saveRbdRefRelationMap(RbdRefRelationMap rbdRefRelationMap) {
		 return rbdRefRelationMapRepo.save(rbdRefRelationMap);
	}

	@Override
	public RbdRefRelationMap getRbdRefRelationMapById(String id) {
		  return rbdRefRelationMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdRefOccupationMap saveRbdRefOccupationMap(RbdRefOccupationMap rbdRefOccupationMap) {
		 return rbdRefOccupationMapRepo.save(rbdRefOccupationMap);
	}

	@Override
	public RbdRefOccupationMap getRbdRefOccupationMapById(String id) {
		  return rbdRefOccupationMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdRefEducationMap saveRbdRefEducationMap(RbdRefEducationMap rbdRefEducationMap) {
		 return rbdRefEducationMapRepo.save(rbdRefEducationMap);

	}

	@Override
	public RbdRefEducationMap getRbdRefEducationMapById(String id) {
		  return rbdRefEducationMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdRefRegistrationNumber saveRbdRefRegistrationNumber(RbdRefRegistrationNumber rbdRefRegistrationNumber) {
		 return rbdRefRegistrationNumberRepo.save(rbdRefRegistrationNumber);
	}

	@Override
	public RbdRefRegistrationNumber getRbdRefRegistrationNumberById(String id) {
		  return rbdRefRegistrationNumberRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdRefDocsMap saveRbdRefDocsMap(RbdRefDocsMap rbdRefDocsMap) {
		 return rbdRefDocsMapRepo.save(rbdRefDocsMap);
	}

	@Override
	public RbdRefDocsMap getRbdRefDocsMapById(String id) {
		  return rbdRefDocsMapRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public HospitalInfo saveHospitalInfo(HospitalInfo hospitalInfo) {
		 return hospitalInfoRepo.save(hospitalInfo);
	}

	@Override
	public HospitalInfo getHospitalInfoById(String id) {
		  return hospitalInfoRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public RbdFeeRelaxationList saveRbdFeeRelaxationList(RbdFeeRelaxationList rbdFeeRelaxationList) {
		 return rbdFeeRelaxationListRepo.save(rbdFeeRelaxationList);
	}

	@Override
	public RbdFeeRelaxationList getRbdFeeRelaxationListById(String id) {
		  return rbdFeeRelaxationListRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyStructureFactor savePropertyStructureFactor(PropertyStructureFactor propertyStructureFactor) {
		 return propertyStructureFactorRepo.save(propertyStructureFactor);
	}

	@Override
	public PropertyStructureFactor getPropertyStructureFactorById(String id) {
		  return propertyStructureFactorRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyTaxCategory savePropertyTaxCategory(PropertyTaxCategory propertyTaxCategory) {
		 return propertyTaxCategoryRepo.save(propertyTaxCategory);
	}

	@Override
	public PropertyTaxCategory getPropertyTaxCategoryById(String id) {
		  return propertyTaxCategoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyUseFactor savePropertyUseFactor(PropertyUseFactor propertyUseFactor) {
		 return propertyUseFactorRepo.save(propertyUseFactor);
	}

	@Override
	public PropertyUseFactor getPropertyUseFactorById(String id) {
		  return propertyUseFactorRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyMasterRebate savePropertyMasterRebate(PropertyMasterRebate propertyMasterRebate) {
		 return propertyMasterRebateRepo.save(propertyMasterRebate);
	}

	@Override
	public PropertyMasterRebate getPropertyMasterRebateById(String id) {
		  return propertyMasterRebateRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyOtherCharges savePropertyOtherCharges(PropertyOtherCharges propertyOtherCharges) {
		 return propertyOtherChargesRepo.save(propertyOtherCharges);
	}

	@Override
	public PropertyOtherCharges getPropertyOtherChargesById(String id) {
		  return propertyOtherChargesRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public ManualReceiptSeries saveManualReceiptSeries(ManualReceiptSeries manualReceiptSeries) {
		 return manualReceiptSeriesRepo.save(manualReceiptSeries);

	}

	@Override
	public ManualReceiptSeries getManualReceiptSeriesById(String id) {
		  return manualReceiptSeriesRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	@Override
	public PropertyMstSr savePropertyMstSr(PropertyMstSr propertyMstSr) {
		 return propertyMstSrRepo.save(propertyMstSr);

	}

	@Override
	public PropertyMstSr getPropertyMstSrById(String id) {
		  return propertyMstSrRepo.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));

	}

	
	
	
	
}
