package com.master.app.pims.service.master.impl.common;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;
import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.ReligiousPlaces;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SmsEmailTemplate;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.repositories.mst.CommonMasterProcessStatusRepo;
import com.master.app.pims.repositories.mst.DocsCategoryInfoRepository;
import com.master.app.pims.repositories.mst.DocsSubmissionInfoRepository;
import com.master.app.pims.repositories.mst.EducationLevelRepository;
import com.master.app.pims.repositories.mst.GeoColonyCategoryRepository;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import com.master.app.pims.repositories.mst.MstChargeDetailsRepository;
import com.master.app.pims.repositories.mst.OccupationTypeRepository;
import com.master.app.pims.repositories.mst.ReligiousPlacesRepository;
import com.master.app.pims.repositories.mst.RequestSubmissionTypeRepository;
import com.master.app.pims.repositories.mst.SmsEmailTemplateRepository;
import com.master.app.pims.repositories.mst.SubmittedRequestStageRepository;
import com.master.app.pims.repositories.mst.UnitAreaRepository;
import com.master.app.pims.service.master.common.CommonMasterService;
import jakarta.transaction.Transactional;
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

	
	
}
