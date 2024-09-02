//package com.master.app.pims.repositories;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.master.app.pims.entities.commonmaster.AssociatedChargesInfo;
//@Repository
//public interface AssociatedChargesInfoRepository extends JpaRepository<AssociatedChargesInfo, String> {
//	//AssociatedChargesInfoCode validator
//	boolean existsByChargeCodeIgnoreCaseAndAssociatedChargesInfoGuidNot(String chargeCode, String associatedChargesInfoGuid);
//
//	//AssociatedChargesInfo NameEn validator
//	boolean existsByChargeNameEnIgnoreCaseAndAssociatedChargesInfoGuidNot(String chargeNameEn, String associatedChargesInfoGuid);
//
//	//AssociatedChargesInfo NameHi validator
//	boolean existsByChargeNameHiIgnoreCaseAndAssociatedChargesInfoGuidNot(String chargeNameHi, String associatedChargesInfoGuid);
//
//	//AssociatedChargesInfo NameRl validator
//	boolean existsByChargeNameRlIgnoreCaseAndAssociatedChargesInfoGuidNot(String chargeNameRl, String associatedChargesInfoGuid);
//
//
//
//}
//
