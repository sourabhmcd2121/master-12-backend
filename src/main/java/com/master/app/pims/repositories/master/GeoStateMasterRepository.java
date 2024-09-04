package com.master.app.pims.repositories.master;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeoStateMasterRepository extends JpaRepository<GeoStateMaster, String> {

    // For country code validation
    boolean existsByStateCodeIgnoreCaseAndStateMasterGuidNot(String stateCode, String stateMasterGuid);

    // For country name En validation
    boolean existsByStateNameEnIgnoreCaseAndStateMasterGuidNot(String stateNameEn, String stateMasterGuid);

    // For country name Hi validation
    boolean existsByStateNameHiIgnoreCaseAndStateMasterGuidNot(String stateNameHi, String stateMasterGuid);

    // For country name Rl validation
    boolean existsByStateNameRlIgnoreCaseAndStateMasterGuidNot(String stateNameRl, String stateMasterGuid);

    // For country code and guid validation
    Optional<GeoStateMaster> findByStateCodeAndStateMasterGuidNotIgnoreCase(String stateCode,
                                                                            String stateMasterGuid);

    // For country name En and guid validation
    Optional<GeoStateMaster> findByStateNameEnAndStateMasterGuidNotIgnoreCase(String stateNameEn,
                                                                              String stateMasterGuid);

    // For country name Hi and guid validation
    Optional<GeoStateMaster> findByStateNameHiAndStateMasterGuidNotIgnoreCase(String stateNameHi,
                                                                              String stateMasterGuid);

    // For country name Rl and guid validation
    Optional<GeoStateMaster> findByStateNameRlAndStateMasterGuidNotIgnoreCase(String stateNameRl,
                                                                              String stateMasterGuid);

}
