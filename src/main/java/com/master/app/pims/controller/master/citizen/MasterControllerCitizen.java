package com.master.app.pims.controller.master.citizen;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.master.app.pims.config.FileStorageConfig;
import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.entities.schemas.citizenmaster.FooterMenu;
import com.master.app.pims.entities.schemas.citizenmaster.FooterRibbon;
import com.master.app.pims.entities.schemas.citizenmaster.HelplineNumbers;
import com.master.app.pims.entities.schemas.citizenmaster.LogoDeptName;
import com.master.app.pims.entities.schemas.citizenmaster.NoteMenu;
import com.master.app.pims.entities.schemas.citizenmaster.PhotoGallery;
import com.master.app.pims.entities.schemas.citizenmaster.SocialLinks;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.citizen.AdminDetailRepo;
import com.master.app.pims.repositories.citizen.FooterMenuRepo;
import com.master.app.pims.repositories.citizen.FooterRibbonRepo;
import com.master.app.pims.repositories.citizen.HelplineNumbersRepo;
import com.master.app.pims.repositories.citizen.LogoDeptNameRepo;
import com.master.app.pims.repositories.citizen.NoteMenuRepo;
import com.master.app.pims.repositories.citizen.PhotoGalleryRepo;
import com.master.app.pims.repositories.citizen.SocialLinksRepo;
import com.master.app.pims.service.master.CommonMasterServiceHandler;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class MasterControllerCitizen {
	@Autowired
	private Validator validator;

	@Autowired
	private CommonMasterService commonMasterService;
	
	@Autowired
	private FileStorageConfig fileStorageConfig;

	@Autowired
	private CommonMasterServiceHandler serviceHandler;

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
	
/////////////////////////////////////AdminDetail Start///////////////////////////////////
//get all data from table
	@GetMapping("/getAdminDetailList")
	public ResponseEntity<BaseResponse> getAdminDetailList() {
		BaseResponse response = new BaseResponse();
// Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<AdminDetail> list = adminDetailRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setAdminDetail(list);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/submitAdminDetail")
	public BaseResponse submitAdminDetail(@RequestBody AdminDetail adminDetail, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

		try {
			// Check if GUID is provided (indicating an update)
			if (adminDetail.getAdminDetailGuid() == null || adminDetail.getAdminDetailGuid().isEmpty()) {
				// Add new data
				adminDetail.setCreatedIpAddr(request.getRemoteAddr());
				adminDetail.setAdminDetailGuid(UUID.randomUUID().toString());
				adminDetail.setCreatedDate(new Date());
				adminDetail.setModifiedIpAddr(null);
				adminDetail.setModifiedBy(null);
				adminDetail.setModifiedDate(null);
				adminDetail.setCreatedBy(request.getRemoteAddr());

				if (adminDetail.getIsActive() == null)
					adminDetail.setIsActive(false);

				// Image upload code for creating new record
				if (adminDetail.getUserImage1Base64() != null && !adminDetail.getUserImage1Base64().isEmpty()) {
					try {
						// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
						String base64String = adminDetail.getUserImage1Base64();
						if (base64String.contains("base64,")) {
							base64String = base64String.split("base64,")[1]; // Remove the prefix
						}

						// Decode the base64 image string
						byte[] imageBytes = Base64.getDecoder().decode(base64String);
						adminDetail.setUserImage1(imageBytes); // Set image bytes to entity
					} catch (IllegalArgumentException e) {
						log.error("Invalid Base64 image data.", e);
						resultData.setStatus(false);
						resultData.setMessage("Invalid Base64 image data.");
						return resultData;
					}
				}

			} else {
				// Update existing data
				AdminDetail existingAdminDetail = commonMasterService
						.getAdminDetailById(adminDetail.getAdminDetailGuid());

				if (existingAdminDetail != null) {
					existingAdminDetail.setUserName(!Util.isNullOrEmpty(adminDetail.getUserName())
							? adminDetail.getUserName().toUpperCase().trim()
							: null);
					existingAdminDetail.setDepartmentName(!Util.isNullOrEmpty(adminDetail.getDepartmentName())
							? adminDetail.getDepartmentName().toUpperCase().trim()
							: null);
					existingAdminDetail.setEncryptedPwd(!Util.isNullOrEmpty(adminDetail.getEncryptedPwd())
							? adminDetail.getEncryptedPwd().toUpperCase().trim()
							: null);
					existingAdminDetail.setIpAddress(
							!Util.isNullOrEmpty(adminDetail.getIpAddress()) ? adminDetail.getIpAddress().trim() : null);
					existingAdminDetail.setActiveFromDate(adminDetail.getActiveFromDate());
					existingAdminDetail.setActiveTill(adminDetail.getActiveTill());
					existingAdminDetail.setIsActive(adminDetail.getIsActive() != null ? adminDetail.getIsActive()
							: existingAdminDetail.getIsActive());

					if (existingAdminDetail.getIsActive() == null)
						existingAdminDetail.setIsActive(false);

					// Handle image update for existing record
					if (adminDetail.getUserImage1Base64() != null && !adminDetail.getUserImage1Base64().isEmpty()) {
						try {
							// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
							String base64String = adminDetail.getUserImage1Base64();
							if (base64String.contains("base64,")) {
								base64String = base64String.split("base64,")[1]; // Remove the prefix
							}

							// Decode the base64 image string
							byte[] imageBytes = Base64.getDecoder().decode(base64String);
							existingAdminDetail.setUserImage1(imageBytes); // Update the image bytes in existing object
						} catch (IllegalArgumentException e) {
							log.error("Invalid Base64 image data.", e);
							resultData.setStatus(false);
							resultData.setMessage("Invalid Base64 image data.");
							return resultData;
						}
					} else if (adminDetail.getUserImage1() != null && adminDetail.getUserImage1().length > 0) {
						existingAdminDetail.setUserImage1(adminDetail.getUserImage1());
					}

					// Update modification details
					existingAdminDetail.setModifiedIpAddr(request.getRemoteAddr());
					existingAdminDetail.setModifiedDate(new Date());
					existingAdminDetail.setModifiedBy(request.getRemoteAddr());
					existingAdminDetail.setModifiedMacAddr(UUID.randomUUID().toString());
					adminDetail = existingAdminDetail; // Use the updated existing menu master object
				} else {
					log.error("AdminDetail Master not found");
					resultData.setStatus(false);
					resultData.setMessage("AdminDetail Master not found");
					return resultData;
				}
			}

			// Validation
			resultData = validator.validateAdminDetail(adminDetail);
			if (resultData != null && !resultData.getStatus()) {
				log.error("Validation failed: {}", resultData.getMessage());
				return resultData;
			}

			// Set some default values for empty fields
			if (adminDetail.getIsActive() == null)
				adminDetail.setIsActive(false);
			adminDetail.setUserName(
					!Util.isNullOrEmpty(adminDetail.getUserName()) ? adminDetail.getUserName().toUpperCase().trim()
							: null);
			adminDetail.setDepartmentName(!Util.isNullOrEmpty(adminDetail.getDepartmentName())
					? adminDetail.getDepartmentName().toUpperCase().trim()
					: null);
			adminDetail.setEncryptedPwd(
					!Util.isNullOrEmpty(adminDetail.getEncryptedPwd()) ? adminDetail.getEncryptedPwd().trim() : null);
			adminDetail.setIpAddress(
					!Util.isNullOrEmpty(adminDetail.getIpAddress()) ? adminDetail.getIpAddress().trim() : null);
			adminDetail.setActiveFromDate(adminDetail.getActiveFromDate());
			adminDetail.setActiveTill(adminDetail.getActiveTill());

			// Save or update the data
			try {
				adminDetailRepo.save(adminDetail);
				log.info("Record SaveOrUpdate Successfully");
				resultData.setStatus(true);
				resultData.setMessage("Record saved or updated successfully");
			} catch (Exception e) {
				log.error("Error saving or updating record: {}", e.getMessage(), e);
				resultData.setStatus(false);
				resultData.setMessage("Error saving or updating record: " + e.getMessage());
			}

		} catch (Exception e) {
			log.error("Unexpected error occurred: {}", e.getMessage(), e);
			resultData.setStatus(false);
			resultData.setMessage("Unexpected error occurred: " + e.getMessage());
		}

		return resultData;
	}

//get data by id
	@GetMapping("/getAdminDetailByGuid/{adminDetailGuid}")
	public ResponseEntity<AdminDetail> getAdminDetailByGuid(@PathVariable("adminDetailGuid") String adminDetailGuid) {
		AdminDetail adminDetail = adminDetailRepo.findById(adminDetailGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with menuMasterGuid : " + adminDetailGuid));
		return new ResponseEntity<>(adminDetail, HttpStatus.OK);
	}

/////////////////////////////////////AdminDetail  End///////////////////////////////////
	
	
/////////////////////////////////////FooterRibbon Start///////////////////////////////////
//get all data from table
@GetMapping("/getFooterRibbonList")
public ResponseEntity<BaseResponse> getFooterRibbonList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<FooterRibbon> list = footerRibbonRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setFooterRibbon(list);
return ResponseEntity.ok(response);
}

@PostMapping("/submitFooterRibbon")
public BaseResponse submitFooterRibbon(@RequestBody FooterRibbon footerRibbon, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();
try {
// Check if GUID is provided (indicating an update)
if (footerRibbon.getFooterRibbonGuid() == null || footerRibbon.getFooterRibbonGuid().isEmpty()) {
// Add new data
	footerRibbon.setCreatedIpAddr(request.getRemoteAddr());
	footerRibbon.setFooterRibbonGuid(UUID.randomUUID().toString());
	footerRibbon.setCreatedDate(new Date());
	footerRibbon.setModifiedIpAddr(null);
	footerRibbon.setModifiedBy(null);
	footerRibbon.setModifiedDate(null);
footerRibbon.setCreatedBy(request.getRemoteAddr());

if (footerRibbon.getIsActive() == null)
	footerRibbon.setIsActive(false);

// Image upload code for creating new record
if (footerRibbon.getUserImage1Base64() != null && !footerRibbon.getUserImage1Base64().isEmpty()) {
try {
// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = footerRibbon.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

// Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
footerRibbon.setImageBinary1(imageBytes); // Set image bytes to entity
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
}

} else {
// Update existing data
FooterRibbon existingFooterRibbon = commonMasterService.getFooterRibbonById(footerRibbon.getFooterRibbonGuid());

if (existingFooterRibbon != null) {
	existingFooterRibbon.setImageHeadingEn(!Util.isNullOrEmpty(footerRibbon.getImageHeadingEn())? footerRibbon.getImageHeadingEn().toUpperCase().trim(): null);
	
existingFooterRibbon.setImageHeadingHi(!Util.isNullOrEmpty(footerRibbon.getImageHeadingHi())? footerRibbon.getImageHeadingHi().toUpperCase().trim(): null);
	
existingFooterRibbon.setImageHeadingRl(!Util.isNullOrEmpty(footerRibbon.getImageHeadingRl())? footerRibbon.getImageHeadingRl().toUpperCase().trim(): null);
	
existingFooterRibbon.setFooterImageUrl(!Util.isNullOrEmpty(footerRibbon.getFooterImageUrl()) ? footerRibbon.getFooterImageUrl().trim() : null);
	
existingFooterRibbon.setOrderNumber(!Util.isNullOrZero(footerRibbon.getOrderNumber()) ? footerRibbon.getOrderNumber() : null);

existingFooterRibbon.setFooterContent(!Util.isNullOrEmpty(footerRibbon.getFooterContent())? footerRibbon.getFooterContent().toUpperCase().trim(): null);


existingFooterRibbon.setIsActive(footerRibbon.getIsActive() != null ? footerRibbon.getIsActive(): existingFooterRibbon.getIsActive());



if (existingFooterRibbon.getIsActive() == null)
	existingFooterRibbon.setIsActive(false);

// Handle image update for existing record
if (footerRibbon.getUserImage1Base64() != null && !footerRibbon.getUserImage1Base64().isEmpty()) {
try {
// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = footerRibbon.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

// Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
existingFooterRibbon.setImageBinary1(imageBytes); // Update the image bytes in existing object
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
} else if (footerRibbon.getImageBinary1() != null && footerRibbon.getImageBinary1().length > 0)
{
	existingFooterRibbon.setImageBinary1(footerRibbon.getImageBinary1());
}

// Update modification details
existingFooterRibbon.setModifiedIpAddr(request.getRemoteAddr());
existingFooterRibbon.setModifiedDate(new Date());
existingFooterRibbon.setModifiedBy(request.getRemoteAddr());
existingFooterRibbon.setModifiedMacAddr(UUID.randomUUID().toString());
footerRibbon = existingFooterRibbon; // Use the updated existing menu master object
}
else
{
log.error("FooterRibbon Master not found");
resultData.setStatus(false);
resultData.setMessage("FooterRibbon Master not found");
return resultData;
}
}

// Validation
resultData = validator.validateFooterRibbon(footerRibbon);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

// Set some default values for empty fields
if (footerRibbon.getIsActive() == null)
	footerRibbon.setIsActive(false);

footerRibbon.setImageHeadingEn(!Util.isNullOrEmpty(footerRibbon.getImageHeadingEn()) ? footerRibbon.getImageHeadingEn().toUpperCase().trim(): null);

footerRibbon.setImageHeadingHi(!Util.isNullOrEmpty(footerRibbon.getImageHeadingHi())? footerRibbon.getImageHeadingHi().toUpperCase().trim(): null);

footerRibbon.setImageHeadingRl(!Util.isNullOrEmpty(footerRibbon.getImageHeadingRl()) ? footerRibbon.getImageHeadingRl().trim() : null);

footerRibbon.setFooterImageUrl(!Util.isNullOrEmpty(footerRibbon.getFooterImageUrl()) ? footerRibbon.getFooterImageUrl().trim() : null);

footerRibbon.setOrderNumber(!Util.isNullOrZero(footerRibbon.getOrderNumber()) ? footerRibbon.getOrderNumber() : null);

footerRibbon.setFooterContent(!Util.isNullOrEmpty(footerRibbon.getFooterContent()) ? footerRibbon.getFooterContent().trim() : null);

// Save or update the data
try {
	footerRibbonRepo.save(footerRibbon);
log.info("Record SaveOrUpdate Successfully");
resultData.setStatus(true);
resultData.setMessage("Record saved or updated successfully");
} catch (Exception e) {
log.error("Error saving or updating record: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Error saving or updating record: " + e.getMessage());
}

} catch (Exception e) {
log.error("Unexpected error occurred: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Unexpected error occurred: " + e.getMessage());
}

return resultData;
}

//get data by id
@GetMapping("/getFooterRibbonByGuid/{footerRibbonGuid}")
public ResponseEntity<FooterRibbon> getFooterRibbonByGuid(@PathVariable("footerRibbonGuid") String footerRibbonGuid) {
	FooterRibbon footerRibbon = footerRibbonRepo.findById(footerRibbonGuid).orElseThrow(
() -> new ResourceNotFoundException("Resource not found with footerRibbonGuid : " + footerRibbonGuid));
return new ResponseEntity<>(footerRibbon, HttpStatus.OK);
}

/////////////////////////////////////FooterRibbon  End///////////////////////////////////

/////////////////////////////////////FooterMenu Start///////////////////////////////////
//get all data from table
@GetMapping("/getFooterMenuList")
public ResponseEntity<BaseResponse> getFooterMenuList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<FooterMenu> list = footerMenuRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setFooterMenu(list);
return ResponseEntity.ok(response);
}


//old code not working
//@PostMapping("/submitFooterMenu")
//public ResponseEntity<BaseResponse> submitFooterMenu(@RequestBody FooterMenu footerMenu, @RequestParam("pdfFile") MultipartFile pdfFile, HttpServletRequest request) {
//BaseResponse resultData = new BaseResponse();
//
//try {
//	
////Check if GUID is provided (indicating an update)
//if (footerMenu.getFooterMenuGuid() == null || footerMenu.getFooterMenuGuid().isEmpty()) {
////Add new data
//	footerMenu.setCreatedIpAddr(request.getRemoteAddr());
//	footerMenu.setFooterMenuGuid(UUID.randomUUID().toString());
//	footerMenu.setCreatedDate(new Date());
//	footerMenu.setModifiedIpAddr(null);
//	footerMenu.setModifiedBy(null);
//	footerMenu.setModifiedDate(null);
//	footerMenu.setCreatedBy(request.getRemoteAddr());
//
//if (footerMenu.getIsActive() == null)
//	footerMenu.setIsActive(false);
//
//// Handle PDF file upload
//if (pdfFile != null && !pdfFile.isEmpty()) {
//    try {
//        String storagePath = "D:\\backendmaster\\pdfData"; // You can make this configurable
//        String fileName = pdfFile.getOriginalFilename();
//        if (fileName == null) {
//            resultData.setStatus(false);
//            resultData.setMessage("File name is missing.");
//            return ResponseEntity.badRequest().body(resultData);
//        }
//        Path filePath = Paths.get(storagePath, fileName);
//        Files.createDirectories(filePath.getParent()); // Ensure the directory exists
//        Files.write(filePath, pdfFile.getBytes());
//
//        footerMenu.setPdfFileName1(fileName); // Save the filename in the database
//
//    } catch (IOException e) {
//        resultData.setStatus(false);
//        resultData.setMessage("Error saving the PDF file.");
//        return ResponseEntity.status(500).body(resultData);
//    }
//}
//
//} else {
////Update existing data
//	FooterMenu existingFooterMenu = commonMasterService.getFooterMenuById(footerMenu.getFooterMenuGuid());
//
//if (existingFooterMenu != null) {
//	existingFooterMenu.setFooterMenuNameEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameEn())? footerMenu.getFooterMenuNameEn().toUpperCase().trim(): null);
//
//	existingFooterMenu.setFooterMenuNameHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameHi())? footerMenu.getFooterMenuNameHi().toUpperCase().trim(): null);
//
//	existingFooterMenu.setFooterMenuNameRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameRl())? footerMenu.getFooterMenuNameRl().toUpperCase().trim(): null);
//
//	existingFooterMenu.setFooterMenuContentHtmlEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlEn()) ? footerMenu.getFooterMenuContentHtmlEn().trim() : null);
//
//	existingFooterMenu.setFooterMenuContentHtmlHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlHi()) ? footerMenu.getFooterMenuContentHtmlHi() : null);
//
//	existingFooterMenu.setFooterMenuContentHtmlRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlRl())? footerMenu.getFooterMenuContentHtmlRl().toUpperCase().trim(): null);
//
//	existingFooterMenu.setFooterMenuUrl(!Util.isNullOrEmpty(footerMenu.getFooterMenuUrl()) ? footerMenu.getFooterMenuUrl() : null);
//
//	existingFooterMenu.setOrderNumber(!Util.isNullOrZero(footerMenu.getOrderNumber())? footerMenu.getOrderNumber() : null);
//
//	existingFooterMenu.setIsActive(footerMenu.getIsActive() != null ? footerMenu.getIsActive(): existingFooterMenu.getIsActive());
//
//if (existingFooterMenu.getIsActive() == null)
//	existingFooterMenu.setIsActive(false);
//
//// Handle the PDF file for update
//if (pdfFile != null && !pdfFile.isEmpty()) {
//    try {
//        String storagePath = "D:\\backendmaster\\pdfData"; // Use the same path
//        String fileName = pdfFile.getOriginalFilename();
//        if (fileName == null) {
//            resultData.setStatus(false);
//            resultData.setMessage("File name is missing.");
//            return ResponseEntity.badRequest().body(resultData);
//        }
//        Path filePath = Paths.get(storagePath, fileName);
//        Files.createDirectories(filePath.getParent());
//        Files.write(filePath, pdfFile.getBytes());
//
//        existingFooterMenu.setPdfFileName1(fileName);
//    } catch (IOException e) {
//        resultData.setStatus(false);
//        resultData.setMessage("Error saving the PDF file.");
//        return ResponseEntity.status(500).body(resultData);
//    }
//}
//
////Update modification details
//existingFooterMenu.setModifiedIpAddr(request.getRemoteAddr());
//existingFooterMenu.setModifiedDate(new Date());
//existingFooterMenu.setModifiedBy(request.getRemoteAddr());
//existingFooterMenu.setModifiedMacAddr(UUID.randomUUID().toString());
//footerMenu = existingFooterMenu; // Use the updated existing menu master object
//}
//else
//{
//log.error("FooterMenu Master not found");
//resultData.setStatus(false);
//resultData.setMessage("FooterMenu Master not found");
//return ResponseEntity.status(404).body(resultData);
//}
//}
//
////Validation
//resultData = validator.validateFooterMenu(footerMenu);
//if (resultData != null && !resultData.getStatus()) {
//log.error("Validation failed: {}", resultData.getMessage());
//return ResponseEntity.badRequest().body(resultData);
//}
//
////Set some default values for empty fields
//if (footerMenu.getIsActive() == null)
//	footerMenu.setIsActive(false);
//
//footerMenu.setFooterMenuNameEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameEn())? footerMenu.getFooterMenuNameEn().toUpperCase().trim(): null);
//
//footerMenu.setFooterMenuNameHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameHi())? footerMenu.getFooterMenuNameHi().toUpperCase().trim(): null);
//
//footerMenu.setFooterMenuNameRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameRl())? footerMenu.getFooterMenuNameRl().toUpperCase().trim(): null);
//
//footerMenu.setFooterMenuContentHtmlEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlEn()) ? footerMenu.getFooterMenuContentHtmlEn().trim() : null);
//
//footerMenu.setFooterMenuContentHtmlHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlHi()) ? footerMenu.getFooterMenuContentHtmlHi() : null);
//
//footerMenu.setFooterMenuContentHtmlRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlRl())? footerMenu.getFooterMenuContentHtmlRl().toUpperCase().trim(): null);
//
//footerMenu.setFooterMenuUrl(!Util.isNullOrEmpty(footerMenu.getFooterMenuUrl()) ? footerMenu.getFooterMenuUrl() : null);
//
//footerMenu.setOrderNumber(!Util.isNullOrZero(footerMenu.getOrderNumber())? footerMenu.getOrderNumber() : null);
//
////Save or update the data
//try {
//	footerMenuRepo.save(footerMenu);
//log.info("Record SaveOrUpdate Successfully");
//resultData.setStatus(true);
//resultData.setMessage("Record saved or updated successfully");
//return ResponseEntity.ok(resultData);
//} catch (Exception e) {
//log.error("Error saving or updating record: {}", e.getMessage(), e);
//resultData.setStatus(false);
//resultData.setMessage("Error saving or updating record: " + e.getMessage());
//return ResponseEntity.status(500).body(resultData);
//}
//
//} catch (Exception e) {
//log.error("Unexpected error occurred: {}", e.getMessage(), e);
//resultData.setStatus(false);
//resultData.setMessage("Unexpected error occurred: " + e.getMessage());
//return ResponseEntity.status(500).body(resultData);
//}
//
//
//}

////////new code working fine
@PostMapping("/submitFooterMenu")
public BaseResponse submitFooterMenu(@ModelAttribute FooterMenu footerMenu, @RequestParam("pdfFile") MultipartFile pdfFile, HttpServletRequest request) {
    BaseResponse resultData = new BaseResponse();

    try {
        // Check if GUID is provided (indicating an update)
        if (footerMenu.getFooterMenuGuid() == null || footerMenu.getFooterMenuGuid().isEmpty()) {
            // Add new data
            footerMenu.setCreatedIpAddr(request.getRemoteAddr());
            footerMenu.setFooterMenuGuid(UUID.randomUUID().toString());
            footerMenu.setCreatedDate(new Date());
            footerMenu.setModifiedIpAddr(null);
            footerMenu.setModifiedBy(null);
            footerMenu.setModifiedDate(null);
            footerMenu.setCreatedBy(request.getRemoteAddr());

            if (footerMenu.getIsActive() == null) {
                footerMenu.setIsActive(false);
            }

            // Handling PDF file upload for new record
            if (pdfFile != null && !pdfFile.isEmpty()) {
                try {
                    // Set the storage path for the PDF file
                    String storagePath = "D:\\backendmaster\\pdfData"; // Use a dynamic path or config

                    // Get original file name (without extension)
                    String originalFileName = pdfFile.getOriginalFilename();
                    if (originalFileName == null) {
                        resultData.setStatus(false);
                        resultData.setMessage("File name is missing.");
                        return resultData;
                    }

                    // Extract file name without extension
                    String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf('.'));

                    // Generate a 10-digit unique number
                    String uniqueKey = generateUniqueKey();

                    // Combine file name with unique key to create the new file name
                    String newFileName = fileNameWithoutExtension + "_" + uniqueKey + ".pdf";

                    // Define the file path where PDF will be saved locally
                    Path filePath = Paths.get(storagePath, newFileName);

                    // Create directory if it doesn't exist
                    if (!Files.exists(filePath.getParent())) {
                        Files.createDirectories(filePath.getParent());
                    }

                    // Save the PDF file to the local storage
                    Files.write(filePath, pdfFile.getBytes());

                    // Update the file name in the DB model
                    footerMenu.setPdfFileName1(newFileName);

                } catch (IOException e) {
                    log.error("Error saving the PDF file: {}", e.getMessage());
                    resultData.setStatus(false);
                    resultData.setMessage("Error saving the PDF file.");
                    return resultData;
                }
            }

        } else {
            // Update existing data
            FooterMenu existingFooterMenu = commonMasterService.getFooterMenuById(footerMenu.getFooterMenuGuid());

            if (existingFooterMenu != null) {
                existingFooterMenu.setFooterMenuNameEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameEn()) ? footerMenu.getFooterMenuNameEn().toUpperCase().trim() : null);
                existingFooterMenu.setFooterMenuNameHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameHi()) ? footerMenu.getFooterMenuNameHi().toUpperCase().trim() : null);
                existingFooterMenu.setFooterMenuNameRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameRl()) ? footerMenu.getFooterMenuNameRl().toUpperCase().trim() : null);
                existingFooterMenu.setFooterMenuContentHtmlEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlEn()) ? footerMenu.getFooterMenuContentHtmlEn().trim() : null);
                existingFooterMenu.setFooterMenuContentHtmlHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlHi()) ? footerMenu.getFooterMenuContentHtmlHi() : null);
                existingFooterMenu.setFooterMenuContentHtmlRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlRl()) ? footerMenu.getFooterMenuContentHtmlRl().toUpperCase().trim() : null);
                existingFooterMenu.setFooterMenuUrl(!Util.isNullOrEmpty(footerMenu.getFooterMenuUrl()) ? footerMenu.getFooterMenuUrl() : null);
                existingFooterMenu.setOrderNumber(!Util.isNullOrZero(footerMenu.getOrderNumber()) ? footerMenu.getOrderNumber() : null);
                existingFooterMenu.setIsActive(footerMenu.getIsActive() != null ? footerMenu.getIsActive() : existingFooterMenu.getIsActive());

                if (existingFooterMenu.getIsActive() == null) {
                    existingFooterMenu.setIsActive(false);
                }

                // Handle PDF file update for the existing record
                if (pdfFile != null && !pdfFile.isEmpty()) {
                    try {
                        // Set the storage path for the PDF file
                        String storagePath = "D:\\backendmaster\\pdfData"; // Use a dynamic path or config

                        // Get original file name (without extension)
                        String originalFileName = pdfFile.getOriginalFilename();
                        if (originalFileName == null) {
                            resultData.setStatus(false);
                            resultData.setMessage("File name is missing.");
                            return resultData;
                        }

                        // Extract file name without extension
                        String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf('.'));

                        // Generate a 10-digit unique number
                        String uniqueKey = generateUniqueKey();

                        // Combine file name with unique key to create the new file name
                        String newFileName = fileNameWithoutExtension + "_" + uniqueKey + ".pdf";

                        // Define the file path where PDF will be saved locally
                        Path filePath = Paths.get(storagePath, newFileName);

                        // Create directory if it doesn't exist
                        if (!Files.exists(filePath.getParent())) {
                            Files.createDirectories(filePath.getParent());
                        }

                        // Save the PDF file to the local storage
                        Files.write(filePath, pdfFile.getBytes());

                        // Update the file name in the DB model
                        existingFooterMenu.setPdfFileName1(newFileName);

                    } catch (IOException e) {
                        log.error("Error saving the PDF file: {}", e.getMessage());
                        resultData.setStatus(false);
                        resultData.setMessage("Error saving the PDF file.");
                        return resultData;
                    }
                }

                // Update modification details
                existingFooterMenu.setModifiedIpAddr(request.getRemoteAddr());
                existingFooterMenu.setModifiedDate(new Date());
                existingFooterMenu.setModifiedBy(request.getRemoteAddr());
                existingFooterMenu.setModifiedMacAddr(UUID.randomUUID().toString());

                footerMenu = existingFooterMenu; // Use the updated existing menu master object

            } else {
                log.error("FooterMenu Master not found");
                resultData.setStatus(false);
                resultData.setMessage("FooterMenu Master not found");
                return resultData;
            }
        }

        // Validation
        resultData = validator.validateFooterMenu(footerMenu);
        if (resultData != null && !resultData.getStatus()) {
            log.error("Validation failed: {}", resultData.getMessage());
            return resultData;
        }

        // Set some default values for empty fields
        if (footerMenu.getIsActive() == null)
            footerMenu.setIsActive(false);

        footerMenu.setFooterMenuNameEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameEn()) ? footerMenu.getFooterMenuNameEn().toUpperCase().trim() : null);
        footerMenu.setFooterMenuNameHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameHi()) ? footerMenu.getFooterMenuNameHi().toUpperCase().trim() : null);
        footerMenu.setFooterMenuNameRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameRl()) ? footerMenu.getFooterMenuNameRl().toUpperCase().trim() : null);
        footerMenu.setFooterMenuContentHtmlEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlEn()) ? footerMenu.getFooterMenuContentHtmlEn().trim() : null);
        footerMenu.setFooterMenuContentHtmlHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlHi()) ? footerMenu.getFooterMenuContentHtmlHi() : null);
        footerMenu.setFooterMenuContentHtmlRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlRl()) ? footerMenu.getFooterMenuContentHtmlRl().toUpperCase().trim() : null);
        footerMenu.setFooterMenuUrl(!Util.isNullOrEmpty(footerMenu.getFooterMenuUrl()) ? footerMenu.getFooterMenuUrl() : null);
        footerMenu.setOrderNumber(!Util.isNullOrZero(footerMenu.getOrderNumber()) ? footerMenu.getOrderNumber() : null);

        // Save or update the data
        try {
            footerMenuRepo.save(footerMenu);
            log.info("Record SaveOrUpdate Successfully");
            resultData.setStatus(true);
            resultData.setMessage("Record saved or updated successfully");
        } catch (Exception e) {
            log.error("Error saving or updating record: {}", e.getMessage(), e);
            resultData.setStatus(false);
            resultData.setMessage("Error saving or updating record: " + e.getMessage());
        }

    } catch (Exception e) {
        log.error("Unexpected error occurred: {}", e.getMessage(), e);
        resultData.setStatus(false);
        resultData.setMessage("Unexpected error occurred: " + e.getMessage());
    }

    return resultData;
}


//get data by id
@GetMapping("/getFooterMenuByGuid/{footerMenuGuid}")
public ResponseEntity<FooterMenu> getFooterMenuByGuid(@PathVariable("footerMenuGuid") String footerMenuGuid) {
	FooterMenu footerMenu = footerMenuRepo.findById(footerMenuGuid).orElseThrow(
() -> new ResourceNotFoundException("Resource not found with footerMenuGuid : " + footerMenuGuid));
return new ResponseEntity<>(footerMenu, HttpStatus.OK);
}

/////////////////////////////////////FooterMenu End///////////////////////////////////

////////////////////////////////////////////HelplineNumbers Start //////////////////////////

//get all data from table
@GetMapping("/getHelplineNumbersList")
public ResponseEntity<BaseResponse> getHelplineNumbersList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<HelplineNumbers> list = helplineNumbersRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setHelplineNumbers(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitHelplineNumbers")
public BaseResponse submitHelplineNumbers(@RequestBody HelplineNumbers helplineNumbers,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (helplineNumbers.getHelplineNumbersGuid() == null || helplineNumbers.getHelplineNumbersGuid().isEmpty()) {
//Add new data
	helplineNumbers.setCreatedIpAddr(request.getRemoteAddr());
	helplineNumbers.setHelplineNumbersGuid(UUID.randomUUID().toString());
	helplineNumbers.setCreatedDate(new Date());
	helplineNumbers.setModifiedIpAddr(null);
	helplineNumbers.setModifiedBy(null);
	helplineNumbers.setModifiedDate(null);
	helplineNumbers.setCreatedBy(request.getRemoteAddr());

if (helplineNumbers.getIsActive() == null)
	helplineNumbers.setIsActive(false);
//helplineNumbers.setCreatedRemarks(userSessionParam.getUserFullName());
//helplineNumbers.setCreaterMacId(HttpSessionHelper.getMacAddress());
//helplineNumbers.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//helplineNumbers.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	HelplineNumbers existingHelplineNumbers = commonMasterService
.getHelplineNumbersById(helplineNumbers.getHelplineNumbersGuid());

if (existingHelplineNumbers != null) {
	existingHelplineNumbers.setHelplineNumbersNameEn(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNameEn())
? helplineNumbers.getHelplineNumbersNameEn().toUpperCase().trim()
: null);
	existingHelplineNumbers
.setHelplineNumbersNameHi(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNameHi())
? helplineNumbers.getHelplineNumbersNameHi().toUpperCase().trim()
: null);

	existingHelplineNumbers
.setHelplineNumbersNameRl(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNameRl())
? helplineNumbers.getHelplineNumbersNameRl().toUpperCase().trim()
: null);
	existingHelplineNumbers
.setHelplineNumbersNumber(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNumber())
? helplineNumbers.getHelplineNumbersNumber().trim()
: null);


	existingHelplineNumbers.setIsActive(helplineNumbers.getIsActive() != null ? helplineNumbers.getIsActive()
: existingHelplineNumbers.getIsActive());

	existingHelplineNumbers.setModifiedIpAddr(request.getRemoteAddr());
	existingHelplineNumbers.setModifiedDate(new Date());
if (existingHelplineNumbers.getIsActive() == null)
	existingHelplineNumbers.setIsActive(false);
//for now setting some dummy value to test
existingHelplineNumbers.setModifiedBy(UUID.randomUUID().toString());
existingHelplineNumbers.setModifiedMacAddr(UUID.randomUUID().toString());
helplineNumbers = existingHelplineNumbers; // Use the updated existing country object
} else {
log.error("Helpline Numbers not found");
resultData.setStatus(false);
resultData.setMessage("Helpline Numbers not found");
return resultData;
}
}

//Validation
resultData = validator.validateHelplineNumbers(helplineNumbers);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (helplineNumbers.getIsActive() == null)
	helplineNumbers.setIsActive(false);

helplineNumbers.setHelplineNumbersNameEn(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNameEn())
? helplineNumbers.getHelplineNumbersNameEn().toUpperCase().trim()
: null);
helplineNumbers.setHelplineNumbersNameHi(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNameHi())
? helplineNumbers.getHelplineNumbersNameHi().toUpperCase().trim()
: null);
helplineNumbers.setHelplineNumbersNameRl(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNameRl())
? helplineNumbers.getHelplineNumbersNameRl().trim()
: null);
helplineNumbers.setHelplineNumbersNumber(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNumber())
? helplineNumbers.getHelplineNumbersNumber().trim()
: null);


try {
	helplineNumbersRepo.save(helplineNumbers);
log.info("Record SaveOrUpdate Successfully");
resultData.setStatus(true);
resultData.setMessage("Record saved or updated successfully");
} catch (Exception e) {
log.error("Error saving or updating record: {}", e.getMessage());
resultData.setStatus(false);
resultData.setMessage("Error saving or updating record: " + e.getMessage());
}

return resultData;
}

//get data by id
@GetMapping("/getHelplineNumbersByGuid/{helplineNumbersGuid}")
public ResponseEntity<HelplineNumbers> getHelplineNumbersByGuid(
@PathVariable("helplineNumbersGuid") String helplineNumbersGuid) {
	HelplineNumbers helplineNumbers = helplineNumbersRepo.findById(helplineNumbersGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with helplineNumbersGuid : " + helplineNumbersGuid));
return new ResponseEntity<>(helplineNumbers, HttpStatus.OK);
}

////////////////////////////////////////////HelplineNumbers End //////////////////////////

/////////////////////////////////////LogoDeptName Start///////////////////////////////////
//get all data from table
@GetMapping("/getLogoDeptNameList")
public ResponseEntity<BaseResponse> getLogoDeptNameList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<LogoDeptName> list = logoDeptNameRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setLogoDeptName(list);
return ResponseEntity.ok(response);
}

@PostMapping("/submitLogoDeptName")
public BaseResponse submitLogoDeptName(@RequestBody LogoDeptName logoDeptName, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

try {
// Check if GUID is provided (indicating an update)
if (logoDeptName.getLogoDeptNameGuid() == null || logoDeptName.getLogoDeptNameGuid().isEmpty()) {
// Add new data
	logoDeptName.setCreatedIpAddr(request.getRemoteAddr());
	logoDeptName.setLogoDeptNameGuid(UUID.randomUUID().toString());
	logoDeptName.setCreatedDate(new Date());
	logoDeptName.setModifiedIpAddr(null);
	logoDeptName.setModifiedBy(null);
	logoDeptName.setModifiedDate(null);
	logoDeptName.setCreatedBy(request.getRemoteAddr());

//if (logoDeptName.getIsActive() == null)
//	logoDeptName.setIsActive(false);

// Image upload code for creating new record
if (logoDeptName.getUserImage1Base64() != null && !logoDeptName.getUserImage1Base64().isEmpty()) {
try {
// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = logoDeptName.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

// Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
logoDeptName.setLogoImageBinary1(imageBytes); // Set image bytes to entity
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
}

} else {
// Update existing data
	LogoDeptName existingLogoDeptName = commonMasterService
.getLogoDeptNameById(logoDeptName.getLogoDeptNameGuid());

if (existingLogoDeptName != null) {
	existingLogoDeptName.setDeptNameEn(!Util.isNullOrEmpty(logoDeptName.getDeptNameEn())
? logoDeptName.getDeptNameEn().toUpperCase().trim()
: null);
	
	existingLogoDeptName.setDeptNameHi(!Util.isNullOrEmpty(logoDeptName.getDeptNameHi())
? logoDeptName.getDeptNameHi().toUpperCase().trim()
: null);
	
	existingLogoDeptName.setDeptNameRl(!Util.isNullOrEmpty(logoDeptName.getDeptNameRl())
? logoDeptName.getDeptNameRl().toUpperCase().trim()
: null);
	
//	existingLogoDeptName.setIsActive(logoDeptName.getIsActive() != null ? logoDeptName.getIsActive()
//: existingLogoDeptName.getIsActive());

//if (existingLogoDeptName.getIsActive() == null)
//	existingLogoDeptName.setIsActive(false);

// Handle image update for existing record
if (logoDeptName.getUserImage1Base64() != null && !logoDeptName.getUserImage1Base64().isEmpty()) {
try {
// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = logoDeptName.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

// Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
existingLogoDeptName.setLogoImageBinary1(imageBytes); // Update the image bytes in existing object
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
} else if (logoDeptName.getLogoImageBinary1() != null && logoDeptName.getLogoImageBinary1().length > 0) {
	existingLogoDeptName.setLogoImageBinary1(logoDeptName.getLogoImageBinary1());
}

// Update modification details
existingLogoDeptName.setModifiedIpAddr(request.getRemoteAddr());
existingLogoDeptName.setModifiedDate(new Date());
existingLogoDeptName.setModifiedBy(request.getRemoteAddr());
existingLogoDeptName.setModifiedMacAddr(UUID.randomUUID().toString());
logoDeptName = existingLogoDeptName; // Use the updated existing menu master object
} else {
log.error("Logo DeptName not found");
resultData.setStatus(false);
resultData.setMessage("Logo DeptName not found");
return resultData;
}
}

// Validation
resultData = validator.validateLogoDeptName(logoDeptName);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

// Set some default values for empty fields
//if (logoDeptName.getIsActive() == null)
//	logoDeptName.setIsActive(false);

logoDeptName.setDeptNameEn(
!Util.isNullOrEmpty(logoDeptName.getDeptNameEn()) ? logoDeptName.getDeptNameEn().toUpperCase().trim()
: null);

logoDeptName.setDeptNameHi(!Util.isNullOrEmpty(logoDeptName.getDeptNameHi())
? logoDeptName.getDeptNameHi().toUpperCase().trim()
: null);

logoDeptName.setDeptNameRl(
!Util.isNullOrEmpty(logoDeptName.getDeptNameRl()) ? logoDeptName.getDeptNameRl().trim() : null);


// Save or update the data
try {
	logoDeptNameRepo.save(logoDeptName);
log.info("Record SaveOrUpdate Successfully");
resultData.setStatus(true);
resultData.setMessage("Record saved or updated successfully");
} catch (Exception e) {
log.error("Error saving or updating record: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Error saving or updating record: " + e.getMessage());
}

} catch (Exception e) {
log.error("Unexpected error occurred: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Unexpected error occurred: " + e.getMessage());
}

return resultData;
}

//get data by id
@GetMapping("/getLogoDeptNameByGuid/{logoDeptNameGuid}")
public ResponseEntity<LogoDeptName> getLogoDeptNameByGuid(@PathVariable("logoDeptNameGuid") String logoDeptNameGuid) {
	LogoDeptName logoDeptName = logoDeptNameRepo.findById(logoDeptNameGuid).orElseThrow(
() -> new ResourceNotFoundException("Resource not found with logoDeptNameGuid : " + logoDeptNameGuid));
return new ResponseEntity<>(logoDeptName, HttpStatus.OK);
}

/////////////////////////////////////LogoDeptName  End///////////////////////////////////

////////////////////////////////////////////NoteMenu Start //////////////////////////

//get all data from table
@GetMapping("/getNoteMenuList")
public ResponseEntity<BaseResponse> getNoteMenuList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<NoteMenu> list = noteMenuRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setNoteMenu(list);
return ResponseEntity.ok(response);
}

//Create New Data And Update
@PostMapping("/submitNoteMenu")
public BaseResponse submitNoteMenu(@RequestBody NoteMenu noteMenu,
HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
if (noteMenu.getNoteMenuGuid() == null || noteMenu.getNoteMenuGuid().isEmpty()) {
//Add new data
	noteMenu.setCreatedIpAddr(request.getRemoteAddr());
	noteMenu.setNoteMenuGuid(UUID.randomUUID().toString());
	noteMenu.setCreatedDate(new Date());
noteMenu.setModifiedIpAddr(null);
noteMenu.setModifiedBy(null);
noteMenu.setModifiedDate(null);
noteMenu.setCreatedBy(request.getRemoteAddr());

if (noteMenu.getIsActive() == null)
	noteMenu.setIsActive(false);
//noteMenu.setCreatedRemarks(userSessionParam.getUserFullName());
//noteMenu.setCreaterMacId(HttpSessionHelper.getMacAddress());
//noteMenu.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//noteMenu.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

} else {
//Update existing data
	NoteMenu existingNoteMenu = commonMasterService
.getNoteMenuById(noteMenu.getNoteMenuGuid());

if (existingNoteMenu != null) {
	existingNoteMenu.setNoteMenuNameEn(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameEn())
? noteMenu.getNoteMenuNameEn().toUpperCase().trim()
: null);
	existingNoteMenu
.setNoteMenuNameHi(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameHi())
? noteMenu.getNoteMenuNameHi().toUpperCase().trim()
: null);

	existingNoteMenu
.setNoteMenuNameRl(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameRl())
? noteMenu.getNoteMenuNameRl().toUpperCase().trim()
: null);
	existingNoteMenu
.setOrderNumber(!Util.isNullOrZero(noteMenu.getOrderNumber())
? noteMenu.getOrderNumber()
: null);


	existingNoteMenu.setIsActive(noteMenu.getIsActive() != null ? noteMenu.getIsActive()
: existingNoteMenu.getIsActive());

	existingNoteMenu.setModifiedIpAddr(request.getRemoteAddr());
	existingNoteMenu.setModifiedDate(new Date());
if (existingNoteMenu.getIsActive() == null)
	existingNoteMenu.setIsActive(false);
//for now setting some dummy value to test
existingNoteMenu.setModifiedBy(UUID.randomUUID().toString());
existingNoteMenu.setModifiedMacAddr(UUID.randomUUID().toString());
noteMenu = existingNoteMenu; // Use the updated existing country object
} else {
log.error("Note Menu not found");
resultData.setStatus(false);
resultData.setMessage("Note Menu not found");
return resultData;
}
}

//Validation
resultData = validator.validateNoteMenu(noteMenu);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

//If validation passes, proceed to save or update
if (noteMenu.getIsActive() == null)
	noteMenu.setIsActive(false);

noteMenu.setNoteMenuNameEn(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameEn())
? noteMenu.getNoteMenuNameEn().toUpperCase().trim()
: null);
noteMenu.setNoteMenuNameHi(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameHi())
? noteMenu.getNoteMenuNameHi().toUpperCase().trim()
: null);
noteMenu.setNoteMenuNameRl(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameRl())
? noteMenu.getNoteMenuNameRl().trim()
: null);
noteMenu.setOrderNumber(!Util.isNullOrZero(noteMenu.getOrderNumber())
? noteMenu.getOrderNumber()
: null);


try {
	noteMenuRepo.save(noteMenu);
log.info("Record SaveOrUpdate Successfully");
resultData.setStatus(true);
resultData.setMessage("Record saved or updated successfully");
} catch (Exception e) {
log.error("Error saving or updating record: {}", e.getMessage());
resultData.setStatus(false);
resultData.setMessage("Error saving or updating record: " + e.getMessage());
}

return resultData;
}

//get data by id
@GetMapping("/getNoteMenuByGuid/{noteMenuGuid}")
public ResponseEntity<NoteMenu> getNoteMenuByGuid(
@PathVariable("noteMenuGuid") String noteMenuGuid) {
	NoteMenu noteMenu = noteMenuRepo.findById(noteMenuGuid)
.orElseThrow(() -> new ResourceNotFoundException(
"Resource not found with noteMenuGuid : " + noteMenuGuid));
return new ResponseEntity<>(noteMenu, HttpStatus.OK);
}

////////////////////////////////////////////NoteMenu End //////////////////////////

/////////////////////////////////////PhotoGallery Start///////////////////////////////////
//get all data from table
@GetMapping("/getPhotoGalleryList")
public ResponseEntity<BaseResponse> getPhotoGalleryList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<PhotoGallery> list = photoGalleryRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setPhotoGallery(list);
return ResponseEntity.ok(response);
}

@PostMapping("/submitPhotoGallery")
public BaseResponse submitPhotoGallery(@RequestBody PhotoGallery photoGallery, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

try {
// Check if GUID is provided (indicating an update)
if (photoGallery.getPhotoGalleryGuid() == null || photoGallery.getPhotoGalleryGuid().isEmpty()) {
// Add new data
	photoGallery.setCreatedIpAddr(request.getRemoteAddr());
	photoGallery.setPhotoGalleryGuid(UUID.randomUUID().toString());
	photoGallery.setCreatedDate(new Date());
	photoGallery.setModifiedIpAddr(null);
	photoGallery.setModifiedBy(null);
	photoGallery.setModifiedDate(null);
	photoGallery.setCreatedBy(request.getRemoteAddr());

if (photoGallery.getIsActive() == null)
	photoGallery.setIsActive(false);

// Image upload code for creating new record
if (photoGallery.getUserImage1Base64() != null && !photoGallery.getUserImage1Base64().isEmpty()) {
try {
// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = photoGallery.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

// Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
photoGallery.setImageBinary1(imageBytes); // Set image bytes to entity
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
}

} else {
// Update existing data
	PhotoGallery existingPhotoGallery = commonMasterService
.getPhotoGalleryById(photoGallery.getPhotoGalleryGuid());

if (existingPhotoGallery != null) {
	existingPhotoGallery.setPhotoGalleryNameEn(!Util.isNullOrEmpty(photoGallery.getPhotoGalleryNameEn())
? photoGallery.getPhotoGalleryNameEn().toUpperCase().trim()
: null);
	existingPhotoGallery.setPhotoGalleryNameHi(!Util.isNullOrEmpty(photoGallery.getPhotoGalleryNameHi())
? photoGallery.getPhotoGalleryNameHi().toUpperCase().trim()
: null);
	existingPhotoGallery.setPhotoGalleryNameRl(!Util.isNullOrEmpty(photoGallery.getPhotoGalleryNameRl())
? photoGallery.getPhotoGalleryNameRl().toUpperCase().trim()
: null);
	existingPhotoGallery.setImageHeadingEn(
!Util.isNullOrEmpty(photoGallery.getImageHeadingEn()) ? photoGallery.getImageHeadingEn().trim() : null);
	
	existingPhotoGallery.setImageHeadingHi(
			!Util.isNullOrEmpty(photoGallery.getImageHeadingHi()) ? photoGallery.getImageHeadingHi().trim() : null);
	existingPhotoGallery.setImageHeadingRl(
			!Util.isNullOrEmpty(photoGallery.getImageHeadingRl()) ? photoGallery.getImageHeadingRl().trim() : null);
	existingPhotoGallery.setOrderNumber(
			!Util.isNullOrZero(photoGallery.getOrderNumber()) ? photoGallery.getOrderNumber() : null);
		
	existingPhotoGallery.setIsActive(photoGallery.getIsActive() != null ? photoGallery.getIsActive()
: existingPhotoGallery.getIsActive());

if (existingPhotoGallery.getIsActive() == null)
	existingPhotoGallery.setIsActive(false);

// Handle image update for existing record
if (photoGallery.getUserImage1Base64() != null && !photoGallery.getUserImage1Base64().isEmpty()) {
try {
// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = photoGallery.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

// Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
existingPhotoGallery.setImageBinary1(imageBytes); // Update the image bytes in existing object
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
} else if (photoGallery.getImageBinary1() != null && photoGallery.getImageBinary1().length > 0) {
	existingPhotoGallery.setImageBinary1(photoGallery.getImageBinary1());
}

// Update modification details
existingPhotoGallery.setModifiedIpAddr(request.getRemoteAddr());
existingPhotoGallery.setModifiedDate(new Date());
existingPhotoGallery.setModifiedBy(request.getRemoteAddr());
existingPhotoGallery.setModifiedMacAddr(UUID.randomUUID().toString());
photoGallery = existingPhotoGallery; // Use the updated existing menu master object
} else {
log.error("PhotoGallery  not found");
resultData.setStatus(false);
resultData.setMessage("PhotoGallery  not found");
return resultData;
}
}

// Validation
resultData = validator.validatePhotoGallery(photoGallery);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

// Set some default values for empty fields
if (photoGallery.getIsActive() == null)
	photoGallery.setIsActive(false);

photoGallery.setPhotoGalleryNameEn(!Util.isNullOrEmpty(photoGallery.getPhotoGalleryNameEn())
? photoGallery.getPhotoGalleryNameEn().toUpperCase().trim()
: null);
photoGallery.setPhotoGalleryNameHi(!Util.isNullOrEmpty(photoGallery.getPhotoGalleryNameHi())
? photoGallery.getPhotoGalleryNameHi().toUpperCase().trim()
: null);
photoGallery.setPhotoGalleryNameRl(!Util.isNullOrEmpty(photoGallery.getPhotoGalleryNameRl())
? photoGallery.getPhotoGalleryNameRl().toUpperCase().trim()
: null);
photoGallery.setImageHeadingEn(
!Util.isNullOrEmpty(photoGallery.getImageHeadingEn()) ? photoGallery.getImageHeadingEn().trim() : null);
	
photoGallery.setImageHeadingHi(
			!Util.isNullOrEmpty(photoGallery.getImageHeadingHi()) ? photoGallery.getImageHeadingHi().trim() : null);
photoGallery.setImageHeadingRl(
			!Util.isNullOrEmpty(photoGallery.getImageHeadingRl()) ? photoGallery.getImageHeadingRl().trim() : null);
photoGallery.setOrderNumber(
			!Util.isNullOrZero(photoGallery.getOrderNumber()) ? photoGallery.getOrderNumber() : null);

// Save or update the data
try {
	photoGalleryRepo.save(photoGallery);
log.info("Record SaveOrUpdate Successfully");
resultData.setStatus(true);
resultData.setMessage("Record saved or updated successfully");
} catch (Exception e) {
log.error("Error saving or updating record: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Error saving or updating record: " + e.getMessage());
}

} catch (Exception e) {
log.error("Unexpected error occurred: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Unexpected error occurred: " + e.getMessage());
}

return resultData;
}

//get data by id
@GetMapping("/getPhotoGalleryByGuid/{photoGalleryGuid}")
public ResponseEntity<PhotoGallery> getPhotoGalleryByGuid(@PathVariable("photoGalleryGuid") String photoGalleryGuid) {
	PhotoGallery photoGallery = photoGalleryRepo.findById(photoGalleryGuid).orElseThrow(
() -> new ResourceNotFoundException("Resource not found with photoGalleryGuid : " + photoGalleryGuid));
return new ResponseEntity<>(photoGallery, HttpStatus.OK);
}

/////////////////////////////////////PhotoGallery  End///////////////////////////////////


/////////////////////////////////////SocialLinks Start///////////////////////////////////
//get all data from table
@GetMapping("/getSocialLinksList")
public ResponseEntity<BaseResponse> getSocialLinksList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<SocialLinks> list = socialLinksRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setSocialLinks(list);
return ResponseEntity.ok(response);
}

@PostMapping("/submitSocialLinks")
public BaseResponse submitSocialLinks(@RequestBody SocialLinks socialLinks, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();

try {
//Check if GUID is provided (indicating an update)
if (socialLinks.getSocialLinksGuid() == null || socialLinks.getSocialLinksGuid().isEmpty()) {
//Add new data
	socialLinks.setCreatedIpAddr(request.getRemoteAddr());
	socialLinks.setSocialLinksGuid(UUID.randomUUID().toString());
	socialLinks.setCreatedDate(new Date());
	socialLinks.setModifiedIpAddr(null);
	socialLinks.setModifiedBy(null);
	socialLinks.setModifiedDate(null);
	socialLinks.setCreatedBy(request.getRemoteAddr());

if (socialLinks.getIsActive() == null)
	socialLinks.setIsActive(false);

//Image upload code for creating new record
if (socialLinks.getUserImage1Base64() != null && !socialLinks.getUserImage1Base64().isEmpty()) {
try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = socialLinks.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

//Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
socialLinks.setLinkLogoImg1(imageBytes); // Set image bytes to entity
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
}

} else {
//Update existing data
	SocialLinks existingSocialLinks = commonMasterService
.getSocialLinksById(socialLinks.getSocialLinksGuid());

if (existingSocialLinks != null) {
	existingSocialLinks.setSocialLinksSubjectEn(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectEn())
? socialLinks.getSocialLinksSubjectEn().toUpperCase().trim()
: null);
	existingSocialLinks.setSocialLinksSubjectHi(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectHi())
? socialLinks.getSocialLinksSubjectHi().toUpperCase().trim()
: null);
	existingSocialLinks.setSocialLinksSubjectRl(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectRl())
? socialLinks.getSocialLinksSubjectRl().toUpperCase().trim()
: null);
	existingSocialLinks.setSocialLinksUrl(
!Util.isNullOrEmpty(socialLinks.getSocialLinksUrl()) ? socialLinks.getSocialLinksUrl().trim() : null);

	existingSocialLinks.setIsActive(socialLinks.getIsActive() != null ? socialLinks.getIsActive()
: existingSocialLinks.getIsActive());

if (existingSocialLinks.getIsActive() == null)
	existingSocialLinks.setIsActive(false);

//Handle image update for existing record
if (socialLinks.getUserImage1Base64() != null && !socialLinks.getUserImage1Base64().isEmpty()) {
try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = socialLinks.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

//Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
existingSocialLinks.setLinkLogoImg1(imageBytes); // Update the image bytes in existing object
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
} else if (socialLinks.getLinkLogoImg1() != null && socialLinks.getLinkLogoImg1().length > 0) {
	existingSocialLinks.setLinkLogoImg1(socialLinks.getLinkLogoImg1());
}

//Update modification details
existingSocialLinks.setModifiedIpAddr(request.getRemoteAddr());
existingSocialLinks.setModifiedDate(new Date());
existingSocialLinks.setModifiedBy(request.getRemoteAddr());
existingSocialLinks.setModifiedMacAddr(UUID.randomUUID().toString());
socialLinks = existingSocialLinks; // Use the updated existing menu master object
} else {
log.error("SocialLinks  not found");
resultData.setStatus(false);
resultData.setMessage("SocialLinks  not found");
return resultData;
}
}

//Validation
resultData = validator.validateSocialLinks(socialLinks);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}







//Set some default values for empty fields
if (socialLinks.getIsActive() == null)
	socialLinks.setIsActive(false);
socialLinks.setSocialLinksSubjectEn(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectEn())
? socialLinks.getSocialLinksSubjectEn().toUpperCase().trim()
: null);
socialLinks.setSocialLinksSubjectHi(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectHi())
? socialLinks.getSocialLinksSubjectHi().toUpperCase().trim()
: null);
socialLinks.setSocialLinksSubjectRl(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectRl())
? socialLinks.getSocialLinksSubjectRl().toUpperCase().trim()
: null);
socialLinks.setSocialLinksUrl(
!Util.isNullOrEmpty(socialLinks.getSocialLinksUrl()) ? socialLinks.getSocialLinksUrl().trim() : null);


//Save or update the data
try {
	socialLinksRepo.save(socialLinks);
log.info("Record SaveOrUpdate Successfully");
resultData.setStatus(true);
resultData.setMessage("Record saved or updated successfully");
} catch (Exception e) {
log.error("Error saving or updating record: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Error saving or updating record: " + e.getMessage());
}

} catch (Exception e) {
log.error("Unexpected error occurred: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Unexpected error occurred: " + e.getMessage());
}

return resultData;
}

//get data by id
@GetMapping("/getSocialLinksByGuid/{socialLinksGuid}")
public ResponseEntity<SocialLinks> getSocialLinksByGuid(@PathVariable("socialLinksGuid") String socialLinksGuid) {
	SocialLinks socialLinks = socialLinksRepo.findById(socialLinksGuid).orElseThrow(
() -> new ResourceNotFoundException("Resource not found with socialLinksGuid : " + socialLinksGuid));
return new ResponseEntity<>(socialLinks, HttpStatus.OK);
}

/////////////////////////////////////SocialLinks  End///////////////////////////////////

//Function to  convert image in Base64
	public String convertImageToBase64(byte[] imageBytes) {
		return Base64.getEncoder().encodeToString(imageBytes);
	}
	
	  // Method to generate a 10-digit unique number (random)
    private String generateUniqueKey() {
        Random random = new Random();
        int uniqueNumber = 1000000000 + random.nextInt(900000000); // Generates a 10-digit number
        return String.valueOf(uniqueNumber);
    }

}
