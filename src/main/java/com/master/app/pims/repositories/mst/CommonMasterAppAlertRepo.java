package com.master.app.pims.repositories.mst;

import com.master.app.pims.entities.schemas.mst.CommonMasterAppAlert;
import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonMasterAppAlertRepo extends JpaRepository<CommonMasterAppAlert,String> {
}
