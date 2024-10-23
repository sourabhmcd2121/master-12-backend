package com.master.app.pims.service.master.impl.common;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.repositories.ApplicationMasterRepository;
import com.master.app.pims.repositories.AssessmentYearRepository;
import com.master.app.pims.repositories.AssociatedChargesInfoRepository;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.repositories.mst.DocsSubmissionInfoRepository;
import com.master.app.pims.repositories.mst.GeoColonyCategoryRepository;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import com.master.app.pims.repositories.mst.RequestSubmissionTypeRepository;
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

	
	
}
