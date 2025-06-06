package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.ManualReceiptSeries;

public interface ManualReceiptSeriesRepo extends JpaRepository<ManualReceiptSeries, String>{
//	@Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM ManualReceiptSeries m WHERE m.receiptSeriesCode = :receiptSeriesCode AND m.receiptSeriesGuid ! :receiptSeriesGuid")
//		boolean isExistReceiptSeriesCode(@Param("receiptSeriesCode") String receiptSeriesCode,@Param("receiptSeriesGuid") String receiptSeriesGuid);

	@Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM ManualReceiptSeries m " +
		       "WHERE m.receiptSeriesFrom = :#{#manual.receiptSeriesFrom} " +
		       "AND m.receiptSeriesTo = :#{#manual.receiptSeriesTo} " +
		       "AND UPPER(m.receiptSeriesCode) = UPPER(:#{#manual.receiptSeriesCode}) " +
		       "AND m.receiptSeriesGuid <> :#{#manual.receiptSeriesGuid}")
		boolean isManualReceiptSeriesUk(@Param("manual") ManualReceiptSeries manual);

}
