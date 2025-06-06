package com.master.app.pims.validators;
import org.springframework.beans.factory.annotation.Autowired;

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
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.citizen.HelplineNumbersRepo;

public interface Validator {
    
	//mst country validation
    BaseResponse validateMstCountry(GeoCountryMst country);
    
    //state master validation
    BaseResponse validateMasterState(GeoStateMaster state);
    
    //validate Colony Category
    BaseResponse validateColonyCategory(GeoColonyCategory colonyCategory);
    
    //validate PersRelation
    BaseResponse validatePersRelation(PersRelation persRelation);
    
    
    //ApplicationMaster validation
    BaseResponse validateApplicationMaster(ApplicationMaster appMaster);
    
    //AssessmentYear validation
    BaseResponse validateAssessmentYear(AssessmentYear assessmentYear);
    
    //AssociatedChargesInfo validation
    BaseResponse validateAssociatedChargesInfo(AssociatedChargesInfo associatedChargesInfo);
    
    //DocsSubmissionInfo validation
    BaseResponse validateDocsSubmissionInfo(DocsSubmissionInfo docsSubmissionInfo);
    
    //RequestSubmissionType validation
    BaseResponse validateRequestSubmissionType(RequestSubmissionType requestSubmissionType);
    
    //SubmittedRequestStage validation
    BaseResponse validateSubmittedRequestStage(SubmittedRequestStage submittedRequestStage);
    
    //UnitArea validation
    BaseResponse validateUnitArea(UnitArea unitArea);
    
    //MstChargeDetails validation
    BaseResponse validateMstChargeDetails(MstChargeDetails mstChargeDetails);
    
    //OccupationType validation
    BaseResponse validateOccupationType(OccupationType occupationType);

    //EducationLevel validation
    BaseResponse validateEducationLevel(EducationLevel educationLevel);
    
    //ReligiousPlaces validation
    BaseResponse validateReligiousPlaces(ReligiousPlaces religiousPlaces);

    //DocsCategoryInfo validation
    BaseResponse validateDocsCategoryInfo(DocsCategoryInfo docsCategoryInfo);
    
    //CommonMasterProcessStatus validation
    BaseResponse validateCommonMasterProcessStatus(CommonMasterProcessStatus commonMasterProcessStatus);
    
    //SmsEmailTemplate validation
    BaseResponse validateSmsEmailTemplate(SmsEmailTemplate smsEmailTemplate);

    //OrgPrimary validation
    BaseResponse validateOrgPrimary(OrgPrimary orgPrimary);
    
    //OrgWrapper validation
    BaseResponse validateOrgWrapper(OrgWrapper orgWrapper);
    
  //GeoZoneMCD validation
    BaseResponse validateGeoZoneMCD(GeoZoneMCD geoZoneMcd);
    
    //OrgRadius validation
    BaseResponse validateOrgRadius(OrgRadius orgRadius);
    
    //RefDocsCategoryMap validation
    BaseResponse validateRefDocsCategoryMap(RefDocsCategoryMap refDocsCategoryMap);
    
    //RefDocsCategoryMap validation
    BaseResponse validateRefUserDocsMap(RefUserDocsMap refUserDocsMap);
    
    //GeoWardMCD validation
    BaseResponse validateGeoWardMCD(GeoWardMCD geoWardMCD);
    
    //GeoColonyMCD validation
    BaseResponse validateGeoColonyMCD(GeoColonyMCD geoColonyMCD);
    
    //CommonMasterAppAlert validation
    BaseResponse validateCommonMasterAppAlert(CommonMasterAppAlert commonMasterAppAlert);
    
    //IntramcMenuMaster validation
    BaseResponse validateIntramcMenuMaster(IntramcMenuMaster intramcMenuMaster);
    
    //IntramcRoleMenuMap validation
    BaseResponse validateIntramcRoleMenuMap(IntramcRoleMenuMap intramcRoleMenuMap);
    
    //CommonMasterTradeClassification validation
    BaseResponse validateCommonMasterTradeClassification(CommonMasterTradeClassification tradeClassification);
    
    //CommonMasterTradeType validation
    BaseResponse validateCommonMasterTradeType(CommonMasterTradeType commonMasterTradeType);
    
    //CommonMasterIndustryArea validation
    BaseResponse validateCommonMasterIndustryArea(CommonMasterIndustryArea industryArea);
    
    //MstRefSla validation
    BaseResponse validateMstRefSla(MstRefSla refSla);
    
    //AdminDetail validation
    BaseResponse validateAdminDetail(AdminDetail adminDetail);
    
    //FooterRibbon validation
    BaseResponse validateFooterRibbon(FooterRibbon footerRibbon);
    
    //FooterMenu validation
    BaseResponse validateFooterMenu(FooterMenu footerMenu);
    
    //HelplineNumbers validation
    BaseResponse validateHelplineNumbers(HelplineNumbers helplineNumbers);
    
    //LogoDeptName validation
    BaseResponse validateLogoDeptName(LogoDeptName logoDeptName);
    
    //NoteMenu validation
    BaseResponse validateNoteMenu(NoteMenu noteMenu);
    
    //PhotoGallery validation
    BaseResponse validatePhotoGallery(PhotoGallery photoGallery);
    
    //SocialLinks validation
    BaseResponse validateSocialLinks(SocialLinks socialLinks);
    
    //OfficerImageComment validation
    BaseResponse validateOfficerImageComment(OfficerImageComment officerImageComment);
    
    //TextFlash validation
    BaseResponse validateTextFlash(TextFlash textFlash);
    
    //FlashImage validation
    BaseResponse validateFlashImage(FlashImage flashImage);
    
    //BgImage validation
    BaseResponse validateBgImage(BgImage bgImage);
    
    //TenderDetails validation
    BaseResponse validateTenderDetails(TenderDetails tenderDetails);
    
    //WebInfoManager validation
    BaseResponse validateWebInfoManager(WebInfoManager webInfoManager);
    
    //VideoGallery validation
    BaseResponse validateVideoGallery(VideoGallery videoGallery);
    
    //HeaderRibbon validation
    BaseResponse validateHeaderRibbon(HeaderRibbon headerRibbon);
    
    //Menu validation
    BaseResponse validateMenu(Menu menu);
    
    ////////////////////////////////////////////Property Master/////////////////////////////
    
    //PropertyAgeFactor validation
    BaseResponse validatePropertyAgeFactor(PropertyAgeFactor propertyAgeFactor);
    
    //PropertyExemption validation
    BaseResponse validatePropertyExemption(PropertyExemption propertyExemption);
    
  //PropertyFloor validation
    BaseResponse validatePropertyFloor(PropertyFloor propertyFloor);
    
    //PropertyOccupancyFactor validation
    BaseResponse validatePropertyOccupancyFactor(PropertyOccupancyFactor propertyOccupancyFactor);
    
    //OwnerCategory validation
    BaseResponse validateOwnerCategory(OwnerCategory ownerCategory);
    
    //OwnerType validation
    BaseResponse validateOwnerType(OwnerType ownerType);
    
    //PropertyCategory validation
    BaseResponse validatePropertyCategory(PropertyCategory propertyCategory);
    
    //PropertyType validation
    BaseResponse validatePropertyType(PropertyType propertyType);
    
    //PropertyStructureFactor validation
    BaseResponse validatePropertyStructureFactor(PropertyStructureFactor propertyStructureFactor);
    
    //PropertyTaxCategory validation
    BaseResponse validatePropertyTaxCategory(PropertyTaxCategory propertyTaxCategory);
    
    //PropertyUseFactor validation
    BaseResponse validatePropertyUseFactor(PropertyUseFactor propertyUseFactor);
    
    //PropertyMasterRebate validation
    BaseResponse validatePropertyMasterRebate(PropertyMasterRebate propertyMasterRebate);
    
    //PropertyOtherCharges validation
    BaseResponse validatePropertyOtherCharges(PropertyOtherCharges propertyOtherCharges);
    
    //ManualReceiptSeries validation
    BaseResponse validateManualReceiptSeries(ManualReceiptSeries manualReceiptSeries);
    
    //ManualReceiptSeries validation
    BaseResponse validatePropertyMstSr(PropertyMstSr propertyMstSr);
    
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////////////////////////////RBD Master////////////////////////////////
   
    //RbdMstCommonList validation
    BaseResponse validateRbdMstCommonList(RbdMstCommonList rbdMstCommonList);
    
    //RbdMstDocsCategory validation
    BaseResponse validateRbdMstDocsCategory(RbdMstDocsCategory rbdMstDocsCategory);
    
    //RbdRefBirthDocsMap validation
    BaseResponse validateRbdRefBirthDocsMap(RbdRefBirthDocsMap rbdRefBirthDocsMap);
    
    //RbdRefDeathDocsMap validation
    BaseResponse validateRbdRefDeathDocsMap(RbdRefDeathDocsMap rbdRefDeathDocsMap);
    
    //RbdRefChargeMap validation
    BaseResponse validateRbdRefChargeMap(RbdRefChargeMap rbdRefChargeMap);
    
    //RbdRefRelationMap validation
    BaseResponse validateRbdRefRelationMap(RbdRefRelationMap rbdRefRelationMap);
    
    //RbdRefOccupationMap validation
    BaseResponse validateRbdRefOccupationMap(RbdRefOccupationMap rbdRefOccupationMap);
    
    //RbdRefEducationMap validation
    BaseResponse validateRbdRefEducationMap(RbdRefEducationMap rbdRefEducationMap);
    
    //RbdRefRegistrationNumber validation
    BaseResponse validateRbdRefRegistrationNumber(RbdRefRegistrationNumber rbdRefRegistrationNumber);
    
    //RbdRefDocsMap validation
    BaseResponse validateRbdRefDocsMap(RbdRefDocsMap rbdRefDocsMap);
    
    //RbdRefDocsMap validation
    BaseResponse validateRbdFeeRelaxationList(RbdFeeRelaxationList rbdFeeRelaxationList);
    
    
    
    
    ////////////////////////////////////////////HospitalInfo Master////////////////////////////////
    //HospitalInfo validation
    BaseResponse validateHospitalInfo(HospitalInfo hospitalInfo);
    
    
    
}
