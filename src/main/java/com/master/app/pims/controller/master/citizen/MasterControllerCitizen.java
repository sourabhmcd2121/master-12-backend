package com.master.app.pims.controller.master.citizen;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.master.app.pims.config.FileStorageConfig;
import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.entities.schemas.citizenmaster.BgImage;
import com.master.app.pims.entities.schemas.citizenmaster.FlashImage;
import com.master.app.pims.entities.schemas.citizenmaster.FooterMenu;
import com.master.app.pims.entities.schemas.citizenmaster.FooterRibbon;
import com.master.app.pims.entities.schemas.citizenmaster.HeaderRibbon;
//import com.master.app.pims.entities.schemas.citizenmaster.HeaderRibbon;
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
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.CommonMasterAppAlert;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.models.common.response.SelectOption;
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
import com.master.app.pims.repositories.citizen.TenderDetailsRepo;
import com.master.app.pims.repositories.citizen.TextFlashRepo;
import com.master.app.pims.repositories.citizen.VideoGalleryRepo;
import com.master.app.pims.repositories.citizen.WebInfoManagerRepo;
import com.master.app.pims.service.master.CommonMasterServiceHandler;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class MasterControllerCitizen {

	@Value("${docs.root.folder}")
	private String storagePath;

	private Logger logger = LoggerFactory.getLogger(MasterControllerCitizen.class);

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
	private MenuRepo menuRepo;

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
				FooterRibbon existingFooterRibbon = commonMasterService
						.getFooterRibbonById(footerRibbon.getFooterRibbonGuid());

				if (existingFooterRibbon != null) {
					existingFooterRibbon.setImageHeadingEn(!Util.isNullOrEmpty(footerRibbon.getImageHeadingEn())
							? footerRibbon.getImageHeadingEn().toUpperCase().trim()
							: null);

					existingFooterRibbon.setImageHeadingHi(!Util.isNullOrEmpty(footerRibbon.getImageHeadingHi())
							? footerRibbon.getImageHeadingHi().toUpperCase().trim()
							: null);

					existingFooterRibbon.setImageHeadingRl(!Util.isNullOrEmpty(footerRibbon.getImageHeadingRl())
							? footerRibbon.getImageHeadingRl().toUpperCase().trim()
							: null);

					existingFooterRibbon.setFooterImageUrl(!Util.isNullOrEmpty(footerRibbon.getFooterImageUrl())
							? footerRibbon.getFooterImageUrl().trim()
							: null);

					existingFooterRibbon.setOrderNumber(
							!Util.isNullOrZero(footerRibbon.getOrderNumber()) ? footerRibbon.getOrderNumber() : null);

					existingFooterRibbon.setFooterContent(!Util.isNullOrEmpty(footerRibbon.getFooterContent())
							? footerRibbon.getFooterContent().toUpperCase().trim()
							: null);

					existingFooterRibbon.setIsActive(footerRibbon.getIsActive() != null ? footerRibbon.getIsActive()
							: existingFooterRibbon.getIsActive());

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
							existingFooterRibbon.setImageBinary1(imageBytes); // Update the image bytes in existing
																				// object
						} catch (IllegalArgumentException e) {
							log.error("Invalid Base64 image data.", e);
							resultData.setStatus(false);
							resultData.setMessage("Invalid Base64 image data.");
							return resultData;
						}
					} else if (footerRibbon.getImageBinary1() != null && footerRibbon.getImageBinary1().length > 0) {
						existingFooterRibbon.setImageBinary1(footerRibbon.getImageBinary1());
					}

// Update modification details
					existingFooterRibbon.setModifiedIpAddr(request.getRemoteAddr());
					existingFooterRibbon.setModifiedDate(new Date());
					existingFooterRibbon.setModifiedBy(request.getRemoteAddr());
					existingFooterRibbon.setModifiedMacAddr(UUID.randomUUID().toString());
					footerRibbon = existingFooterRibbon; // Use the updated existing menu master object
				} else {
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

			footerRibbon.setImageHeadingEn(!Util.isNullOrEmpty(footerRibbon.getImageHeadingEn())
					? footerRibbon.getImageHeadingEn().toUpperCase().trim()
					: null);

			footerRibbon.setImageHeadingHi(!Util.isNullOrEmpty(footerRibbon.getImageHeadingHi())
					? footerRibbon.getImageHeadingHi().toUpperCase().trim()
					: null);

			footerRibbon.setImageHeadingRl(
					!Util.isNullOrEmpty(footerRibbon.getImageHeadingRl()) ? footerRibbon.getImageHeadingRl().trim()
							: null);

			footerRibbon.setFooterImageUrl(
					!Util.isNullOrEmpty(footerRibbon.getFooterImageUrl()) ? footerRibbon.getFooterImageUrl().trim()
							: null);

			footerRibbon.setOrderNumber(
					!Util.isNullOrZero(footerRibbon.getOrderNumber()) ? footerRibbon.getOrderNumber() : null);

			footerRibbon.setFooterContent(
					!Util.isNullOrEmpty(footerRibbon.getFooterContent()) ? footerRibbon.getFooterContent().trim()
							: null);

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
	public ResponseEntity<FooterRibbon> getFooterRibbonByGuid(
			@PathVariable("footerRibbonGuid") String footerRibbonGuid) {
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
	public BaseResponse submitFooterMenu(@ModelAttribute FooterMenu footerMenu,
			@RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile, HttpServletRequest request) {
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
						// String storagePath = "D:\\backendmaster\\pdfData"; // Use a dynamic path or
						// config

						// Get original file name (without extension)
						String originalFileName = pdfFile.getOriginalFilename();
						if (originalFileName == null) {
							resultData.setStatus(false);
							resultData.setMessage("File name is missing.");
							return resultData;
						}

						// Extract file name without extension
						String fileNameWithoutExtension = originalFileName.substring(0,
								originalFileName.lastIndexOf('.'));

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
					existingFooterMenu.setFooterMenuNameEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameEn())
							? footerMenu.getFooterMenuNameEn().toUpperCase().trim()
							: null);
					existingFooterMenu.setFooterMenuNameHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameHi())
							? footerMenu.getFooterMenuNameHi().toUpperCase().trim()
							: null);
					existingFooterMenu.setFooterMenuNameRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameRl())
							? footerMenu.getFooterMenuNameRl().toUpperCase().trim()
							: null);
					existingFooterMenu
							.setFooterMenuContentHtmlEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlEn())
									? footerMenu.getFooterMenuContentHtmlEn().trim()
									: null);
					existingFooterMenu
							.setFooterMenuContentHtmlHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlHi())
									? footerMenu.getFooterMenuContentHtmlHi()
									: null);
					existingFooterMenu
							.setFooterMenuContentHtmlRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlRl())
									? footerMenu.getFooterMenuContentHtmlRl().toUpperCase().trim()
									: null);
					existingFooterMenu.setFooterMenuUrl(
							!Util.isNullOrEmpty(footerMenu.getFooterMenuUrl()) ? footerMenu.getFooterMenuUrl() : null);
					existingFooterMenu.setOrderNumber(
							!Util.isNullOrZero(footerMenu.getOrderNumber()) ? footerMenu.getOrderNumber() : null);
					existingFooterMenu.setIsActive(footerMenu.getIsActive() != null ? footerMenu.getIsActive()
							: existingFooterMenu.getIsActive());

					if (existingFooterMenu.getIsActive() == null) {
						existingFooterMenu.setIsActive(false);
					}

					// Handle PDF file update for the existing record
					if (pdfFile != null && !pdfFile.isEmpty()) {
						try {
							// Set the storage path for the PDF file
							// String storagePath = "D:\\backendmaster\\pdfData"; // Use a dynamic path or
							// config

							// Get original file name (without extension)
							String originalFileName = pdfFile.getOriginalFilename();
							if (originalFileName == null || originalFileName.isEmpty()) {
								resultData.setStatus(false);
								resultData.setMessage("File name is missing.");
								return resultData;
							}

							// Ensure that the file name does not contain invalid characters
							originalFileName = originalFileName.replaceAll("[^a-zA-Z0-9.-]", "_");

							// Extract file name without extension
							String fileNameWithoutExtension = originalFileName.substring(0,
									originalFileName.lastIndexOf('.'));

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

							// Check if file already exists, regenerate if necessary
							while (Files.exists(filePath)) {
								uniqueKey = generateUniqueKey(); // Regenerate the unique key if the file already exists
								newFileName = fileNameWithoutExtension + "_" + uniqueKey + ".pdf";
								filePath = Paths.get(storagePath, newFileName);
							}

							// Log the file saving path
							log.info("Saving PDF file to: {}", filePath.toString());

							// Save the PDF file to the local storage
							Files.write(filePath, pdfFile.getBytes());

							// Update the file name in the DB model
							existingFooterMenu.setPdfFileName1(newFileName);

						} catch (FileAlreadyExistsException e) {
							log.error("File already exists: {}", e.getMessage());
							resultData.setStatus(false);
							resultData.setMessage("File already exists.");
							return resultData;
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

			footerMenu.setFooterMenuNameEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameEn())
					? footerMenu.getFooterMenuNameEn().toUpperCase().trim()
					: null);
			footerMenu.setFooterMenuNameHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameHi())
					? footerMenu.getFooterMenuNameHi().toUpperCase().trim()
					: null);
			footerMenu.setFooterMenuNameRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuNameRl())
					? footerMenu.getFooterMenuNameRl().toUpperCase().trim()
					: null);
			footerMenu.setFooterMenuContentHtmlEn(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlEn())
					? footerMenu.getFooterMenuContentHtmlEn().trim()
					: null);
			footerMenu.setFooterMenuContentHtmlHi(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlHi())
					? footerMenu.getFooterMenuContentHtmlHi()
					: null);
			footerMenu.setFooterMenuContentHtmlRl(!Util.isNullOrEmpty(footerMenu.getFooterMenuContentHtmlRl())
					? footerMenu.getFooterMenuContentHtmlRl().toUpperCase().trim()
					: null);
			footerMenu.setFooterMenuUrl(
					!Util.isNullOrEmpty(footerMenu.getFooterMenuUrl()) ? footerMenu.getFooterMenuUrl() : null);
			footerMenu.setOrderNumber(
					!Util.isNullOrZero(footerMenu.getOrderNumber()) ? footerMenu.getOrderNumber() : null);

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

				existingHelplineNumbers
						.setHelplineNumbersNameEn(!Util.isNullOrEmpty(helplineNumbers.getHelplineNumbersNameEn())
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

				existingHelplineNumbers
						.setIsActive(helplineNumbers.getIsActive() != null ? helplineNumbers.getIsActive()
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
							existingLogoDeptName.setLogoImageBinary1(imageBytes); // Update the image bytes in existing
																					// object
						} catch (IllegalArgumentException e) {
							log.error("Invalid Base64 image data.", e);
							resultData.setStatus(false);
							resultData.setMessage("Invalid Base64 image data.");
							return resultData;
						}
					} else if (logoDeptName.getLogoImageBinary1() != null
							&& logoDeptName.getLogoImageBinary1().length > 0) {
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

			logoDeptName.setDeptNameEn(!Util.isNullOrEmpty(logoDeptName.getDeptNameEn())
					? logoDeptName.getDeptNameEn().toUpperCase().trim()
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
	public ResponseEntity<LogoDeptName> getLogoDeptNameByGuid(
			@PathVariable("logoDeptNameGuid") String logoDeptNameGuid) {
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
	public BaseResponse submitNoteMenu(@RequestBody NoteMenu noteMenu, HttpServletRequest request) {
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
			NoteMenu existingNoteMenu = commonMasterService.getNoteMenuById(noteMenu.getNoteMenuGuid());

			if (existingNoteMenu != null) {
				existingNoteMenu.setNoteMenuNameEn(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameEn())
						? noteMenu.getNoteMenuNameEn().toUpperCase().trim()
						: null);
				existingNoteMenu.setNoteMenuNameHi(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameHi())
						? noteMenu.getNoteMenuNameHi().toUpperCase().trim()
						: null);

				existingNoteMenu.setNoteMenuNameRl(!Util.isNullOrEmpty(noteMenu.getNoteMenuNameRl())
						? noteMenu.getNoteMenuNameRl().toUpperCase().trim()
						: null);
				existingNoteMenu.setOrderNumber(
						!Util.isNullOrZero(noteMenu.getOrderNumber()) ? noteMenu.getOrderNumber() : null);

				existingNoteMenu.setIsActive(
						noteMenu.getIsActive() != null ? noteMenu.getIsActive() : existingNoteMenu.getIsActive());

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

		noteMenu.setNoteMenuNameEn(
				!Util.isNullOrEmpty(noteMenu.getNoteMenuNameEn()) ? noteMenu.getNoteMenuNameEn().toUpperCase().trim()
						: null);
		noteMenu.setNoteMenuNameHi(
				!Util.isNullOrEmpty(noteMenu.getNoteMenuNameHi()) ? noteMenu.getNoteMenuNameHi().toUpperCase().trim()
						: null);
		noteMenu.setNoteMenuNameRl(
				!Util.isNullOrEmpty(noteMenu.getNoteMenuNameRl()) ? noteMenu.getNoteMenuNameRl().trim() : null);
		noteMenu.setOrderNumber(!Util.isNullOrZero(noteMenu.getOrderNumber()) ? noteMenu.getOrderNumber() : null);

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
	public ResponseEntity<NoteMenu> getNoteMenuByGuid(@PathVariable("noteMenuGuid") String noteMenuGuid) {
		NoteMenu noteMenu = noteMenuRepo.findById(noteMenuGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with noteMenuGuid : " + noteMenuGuid));
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
					existingPhotoGallery.setImageHeadingEn(!Util.isNullOrEmpty(photoGallery.getImageHeadingEn())
							? photoGallery.getImageHeadingEn().trim()
							: null);

					existingPhotoGallery.setImageHeadingHi(!Util.isNullOrEmpty(photoGallery.getImageHeadingHi())
							? photoGallery.getImageHeadingHi().trim()
							: null);
					existingPhotoGallery.setImageHeadingRl(!Util.isNullOrEmpty(photoGallery.getImageHeadingRl())
							? photoGallery.getImageHeadingRl().trim()
							: null);
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
							existingPhotoGallery.setImageBinary1(imageBytes); // Update the image bytes in existing
																				// object
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
					!Util.isNullOrEmpty(photoGallery.getImageHeadingEn()) ? photoGallery.getImageHeadingEn().trim()
							: null);

			photoGallery.setImageHeadingHi(
					!Util.isNullOrEmpty(photoGallery.getImageHeadingHi()) ? photoGallery.getImageHeadingHi().trim()
							: null);
			photoGallery.setImageHeadingRl(
					!Util.isNullOrEmpty(photoGallery.getImageHeadingRl()) ? photoGallery.getImageHeadingRl().trim()
							: null);
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
	public ResponseEntity<PhotoGallery> getPhotoGalleryByGuid(
			@PathVariable("photoGalleryGuid") String photoGalleryGuid) {
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
					existingSocialLinks
							.setSocialLinksSubjectEn(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectEn())
									? socialLinks.getSocialLinksSubjectEn().toUpperCase().trim()
									: null);
					existingSocialLinks
							.setSocialLinksSubjectHi(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectHi())
									? socialLinks.getSocialLinksSubjectHi().toUpperCase().trim()
									: null);
					existingSocialLinks
							.setSocialLinksSubjectRl(!Util.isNullOrEmpty(socialLinks.getSocialLinksSubjectRl())
									? socialLinks.getSocialLinksSubjectRl().toUpperCase().trim()
									: null);
					existingSocialLinks.setSocialLinksUrl(!Util.isNullOrEmpty(socialLinks.getSocialLinksUrl())
							? socialLinks.getSocialLinksUrl().trim()
							: null);

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
							existingSocialLinks.setLinkLogoImg1(imageBytes); // Update the image bytes in existing
																				// object
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
					!Util.isNullOrEmpty(socialLinks.getSocialLinksUrl()) ? socialLinks.getSocialLinksUrl().trim()
							: null);

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

/////////////////////////////////////OfficerImageComment Start///////////////////////////////////
//get all data from table
	@GetMapping("/getOfficerImageCommentList")
	public ResponseEntity<BaseResponse> getOfficerImageCommentList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<OfficerImageComment> list = officerImageCommentRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setOfficerImageComment(list);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/submitOfficerImageComment")
	public BaseResponse submitOfficerImageComment(@RequestBody OfficerImageComment officerImageComment,
			HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

		try {
//Check if GUID is provided (indicating an update)
			if (officerImageComment.getOfficerImageCommentGuid() == null
					|| officerImageComment.getOfficerImageCommentGuid().isEmpty()) {
//Add new data
				officerImageComment.setCreatedIpAddr(request.getRemoteAddr());
				officerImageComment.setOfficerImageCommentGuid(UUID.randomUUID().toString());
				officerImageComment.setCreatedDate(new Date());
				officerImageComment.setModifiedIpAddr(null);
				officerImageComment.setModifiedBy(null);
				officerImageComment.setModifiedDate(null);
				officerImageComment.setCreatedBy(request.getRemoteAddr());

				if (officerImageComment.getIsActive() == null)
					officerImageComment.setIsActive(false);

//Image upload code for creating new record
				if (officerImageComment.getUserImage1Base64() != null
						&& !officerImageComment.getUserImage1Base64().isEmpty()) {
					try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
						String base64String = officerImageComment.getUserImage1Base64();
						if (base64String.contains("base64,")) {
							base64String = base64String.split("base64,")[1]; // Remove the prefix
						}

//Decode the base64 image string
						byte[] imageBytes = Base64.getDecoder().decode(base64String);
						officerImageComment.setImageBinary1(imageBytes); // Set image bytes to entity
					} catch (IllegalArgumentException e) {
						log.error("Invalid Base64 image data.", e);
						resultData.setStatus(false);
						resultData.setMessage("Invalid Base64 image data.");
						return resultData;
					}
				}

			} else {
//Update existing data
				OfficerImageComment existingOfficerImageComment = commonMasterService
						.getOfficerImageCommentById(officerImageComment.getOfficerImageCommentGuid());

				if (existingOfficerImageComment != null) {
					existingOfficerImageComment
							.setImageHeadingEn(!Util.isNullOrEmpty(officerImageComment.getImageHeadingEn())
									? officerImageComment.getImageHeadingEn().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setImageHeadingHi(!Util.isNullOrEmpty(officerImageComment.getImageHeadingHi())
									? officerImageComment.getImageHeadingHi().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setImageHeadingRl(!Util.isNullOrEmpty(officerImageComment.getImageHeadingRl())
									? officerImageComment.getImageHeadingRl().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setOfficerNameEn(!Util.isNullOrEmpty(officerImageComment.getOfficerNameEn())
									? officerImageComment.getOfficerNameEn().trim()
									: null);

					existingOfficerImageComment
							.setOfficerNameHi(!Util.isNullOrEmpty(officerImageComment.getOfficerNameHi())
									? officerImageComment.getOfficerNameHi().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setOfficerNameRl(!Util.isNullOrEmpty(officerImageComment.getOfficerNameRl())
									? officerImageComment.getOfficerNameRl().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setOfficerDesigEn(!Util.isNullOrEmpty(officerImageComment.getOfficerDesigEn())
									? officerImageComment.getOfficerDesigEn().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setOfficerDesigHi(!Util.isNullOrEmpty(officerImageComment.getOfficerDesigHi())
									? officerImageComment.getOfficerDesigHi().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setOfficerDesigRl(!Util.isNullOrEmpty(officerImageComment.getOfficerDesigRl())
									? officerImageComment.getOfficerDesigRl().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setOfficerCommentEn(!Util.isNullOrEmpty(officerImageComment.getOfficerCommentEn())
									? officerImageComment.getOfficerCommentEn().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setOfficerCommentHi(!Util.isNullOrEmpty(officerImageComment.getOfficerCommentHi())
									? officerImageComment.getOfficerCommentHi().toUpperCase().trim()
									: null);
					existingOfficerImageComment
							.setOfficerCommentRl(!Util.isNullOrEmpty(officerImageComment.getOfficerCommentRl())
									? officerImageComment.getOfficerCommentRl().toUpperCase().trim()
									: null);

					existingOfficerImageComment
							.setOtherHtmlContent(!Util.isNullOrEmpty(officerImageComment.getOtherHtmlContent())
									? officerImageComment.getOtherHtmlContent().toUpperCase().trim()
									: null);
					existingOfficerImageComment.setPortalUri(!Util.isNullOrEmpty(officerImageComment.getPortalUri())
							? officerImageComment.getPortalUri().toUpperCase().trim()
							: null);
					existingOfficerImageComment.setFacebookUri(!Util.isNullOrEmpty(officerImageComment.getFacebookUri())
							? officerImageComment.getFacebookUri().toUpperCase().trim()
							: null);
					existingOfficerImageComment.setTwitterUri(!Util.isNullOrEmpty(officerImageComment.getTwitterUri())
							? officerImageComment.getTwitterUri().toUpperCase().trim()
							: null);
					existingOfficerImageComment.setPriority(
							!Util.isNullOrZero(officerImageComment.getPriority()) ? officerImageComment.getPriority()
									: null);

					existingOfficerImageComment
							.setIsActive(officerImageComment.getIsActive() != null ? officerImageComment.getIsActive()
									: existingOfficerImageComment.getIsActive());

					if (existingOfficerImageComment.getIsActive() == null)
						existingOfficerImageComment.setIsActive(false);

//Handle image update for existing record
					if (officerImageComment.getUserImage1Base64() != null
							&& !officerImageComment.getUserImage1Base64().isEmpty()) {
						try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
							String base64String = officerImageComment.getUserImage1Base64();
							if (base64String.contains("base64,")) {
								base64String = base64String.split("base64,")[1]; // Remove the prefix
							}

//Decode the base64 image string
							byte[] imageBytes = Base64.getDecoder().decode(base64String);
							existingOfficerImageComment.setImageBinary1(imageBytes); // Update the image bytes in
																						// existing object
						} catch (IllegalArgumentException e) {
							log.error("Invalid Base64 image data.", e);
							resultData.setStatus(false);
							resultData.setMessage("Invalid Base64 image data.");
							return resultData;
						}
					} else if (officerImageComment.getImageBinary1() != null
							&& officerImageComment.getImageBinary1().length > 0) {
						existingOfficerImageComment.setImageBinary1(officerImageComment.getImageBinary1());
					}

//Update modification details
					existingOfficerImageComment.setModifiedIpAddr(request.getRemoteAddr());
					existingOfficerImageComment.setModifiedDate(new Date());
					existingOfficerImageComment.setModifiedBy(request.getRemoteAddr());
					existingOfficerImageComment.setModifiedMacAddr(UUID.randomUUID().toString());
					officerImageComment = existingOfficerImageComment; // Use the updated existing menu master object
				} else {
					log.error("OfficerImageComment  not found");
					resultData.setStatus(false);
					resultData.setMessage("OfficerImageComment  not found");
					return resultData;
				}
			}

//Validation
			resultData = validator.validateOfficerImageComment(officerImageComment);
			if (resultData != null && !resultData.getStatus()) {
				log.error("Validation failed: {}", resultData.getMessage());
				return resultData;
			}

//Set some default values for empty fields
			if (officerImageComment.getIsActive() == null)
				officerImageComment.setIsActive(false);
			officerImageComment.setImageHeadingEn(!Util.isNullOrEmpty(officerImageComment.getImageHeadingEn())
					? officerImageComment.getImageHeadingEn().toUpperCase().trim()
					: null);
			officerImageComment.setImageHeadingHi(!Util.isNullOrEmpty(officerImageComment.getImageHeadingHi())
					? officerImageComment.getImageHeadingHi().toUpperCase().trim()
					: null);
			officerImageComment.setImageHeadingRl(!Util.isNullOrEmpty(officerImageComment.getImageHeadingRl())
					? officerImageComment.getImageHeadingRl().toUpperCase().trim()
					: null);
			officerImageComment.setOfficerNameEn(!Util.isNullOrEmpty(officerImageComment.getOfficerNameEn())
					? officerImageComment.getOfficerNameEn().trim()
					: null);

			officerImageComment.setOfficerNameHi(!Util.isNullOrEmpty(officerImageComment.getOfficerNameHi())
					? officerImageComment.getOfficerNameHi().toUpperCase().trim()
					: null);
			officerImageComment.setOfficerNameRl(!Util.isNullOrEmpty(officerImageComment.getOfficerNameRl())
					? officerImageComment.getOfficerNameRl().toUpperCase().trim()
					: null);
			officerImageComment.setOfficerDesigEn(!Util.isNullOrEmpty(officerImageComment.getOfficerDesigEn())
					? officerImageComment.getOfficerDesigEn().toUpperCase().trim()
					: null);
			officerImageComment.setOfficerDesigHi(!Util.isNullOrEmpty(officerImageComment.getOfficerDesigHi())
					? officerImageComment.getOfficerDesigHi().toUpperCase().trim()
					: null);
			officerImageComment.setOfficerDesigRl(!Util.isNullOrEmpty(officerImageComment.getOfficerDesigRl())
					? officerImageComment.getOfficerDesigRl().toUpperCase().trim()
					: null);
			officerImageComment.setOfficerCommentEn(!Util.isNullOrEmpty(officerImageComment.getOfficerCommentEn())
					? officerImageComment.getOfficerCommentEn().toUpperCase().trim()
					: null);
			officerImageComment.setOfficerCommentHi(!Util.isNullOrEmpty(officerImageComment.getOfficerCommentHi())
					? officerImageComment.getOfficerCommentHi().toUpperCase().trim()
					: null);
			officerImageComment.setOfficerCommentRl(!Util.isNullOrEmpty(officerImageComment.getOfficerCommentRl())
					? officerImageComment.getOfficerCommentRl().toUpperCase().trim()
					: null);

			officerImageComment.setOtherHtmlContent(!Util.isNullOrEmpty(officerImageComment.getOtherHtmlContent())
					? officerImageComment.getOtherHtmlContent().toUpperCase().trim()
					: null);
			officerImageComment.setPortalUri(!Util.isNullOrEmpty(officerImageComment.getPortalUri())
					? officerImageComment.getPortalUri().toUpperCase().trim()
					: null);
			officerImageComment.setFacebookUri(!Util.isNullOrEmpty(officerImageComment.getFacebookUri())
					? officerImageComment.getFacebookUri().toUpperCase().trim()
					: null);
			officerImageComment.setTwitterUri(!Util.isNullOrEmpty(officerImageComment.getTwitterUri())
					? officerImageComment.getTwitterUri().toUpperCase().trim()
					: null);
			officerImageComment.setPriority(
					!Util.isNullOrZero(officerImageComment.getPriority()) ? officerImageComment.getPriority() : null);

//Save or update the data
			try {
				officerImageCommentRepo.save(officerImageComment);
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
	@GetMapping("/getOfficerImageCommentByGuid/{officerImageCommentGuid}")
	public ResponseEntity<OfficerImageComment> getOfficerImageCommentByGuid(
			@PathVariable("officerImageCommentGuid") String officerImageCommentGuid) {
		OfficerImageComment officerImageComment = officerImageCommentRepo.findById(officerImageCommentGuid)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Resource not found with officerImageCommentGuid : " + officerImageCommentGuid));
		return new ResponseEntity<>(officerImageComment, HttpStatus.OK);
	}

/////////////////////////////////////OfficerImageComment  End///////////////////////////////////

/////////////////////////////////////TextFlash Start///////////////////////////////////
//get all data from table
	@GetMapping("/getTextFlashList")
	public ResponseEntity<BaseResponse> getTextFlashList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<TextFlash> list = textFlashRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setTextFlash(list);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/submitTextFlash")
	public BaseResponse submitTextFlash(@ModelAttribute TextFlash textFlash,
			@RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

		try {
			System.out.println("aa gya");
// Check if GUID is provided (indicating an update)
			if (textFlash.getTextFlashGuid() == null || textFlash.getTextFlashGuid().isEmpty()) {
// Add new data
				textFlash.setCreatedIpAddr(request.getRemoteAddr());
				textFlash.setTextFlashGuid(UUID.randomUUID().toString());
				textFlash.setCreatedDate(new Date());
				textFlash.setModifiedIpAddr(null);
				textFlash.setModifiedBy(null);
				textFlash.setModifiedDate(null);
				textFlash.setCreatedBy(request.getRemoteAddr());

				if (textFlash.getIsActive() == null) {
					textFlash.setIsActive(false);
				}

// Handling PDF file upload for new record
				if (pdfFile != null && !pdfFile.isEmpty()) {
					try {
// Set the storage path for the PDF file
//String storagePath = "D:\\backendmaster\\pdfData"; // Use a dynamic path or config

// Get original file name (without extension)
						String originalFileName = pdfFile.getOriginalFilename();
						if (originalFileName == null) {
							resultData.setStatus(false);
							resultData.setMessage("File name is missing.");
							return resultData;
						}

// Extract file name without extension
						String fileNameWithoutExtension = originalFileName.substring(0,
								originalFileName.lastIndexOf('.'));

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
						textFlash.setPdfFileName1(newFileName);

					} catch (IOException e) {
						log.error("Error saving the PDF file: {}", e.getMessage());
						resultData.setStatus(false);
						resultData.setMessage("Error saving the PDF file.");
						return resultData;
					}
				}

			} else {
// Update existing data
				TextFlash existingTextFlash = commonMasterService.getTextFlashById(textFlash.getTextFlashGuid());

				if (existingTextFlash != null) {
					existingTextFlash.setTextFlashSubjectEn(!Util.isNullOrEmpty(textFlash.getTextFlashSubjectEn())
							? textFlash.getTextFlashSubjectEn().toUpperCase().trim()
							: null);
					existingTextFlash.setTextFlashSubjectHi(!Util.isNullOrEmpty(textFlash.getTextFlashSubjectHi())
							? textFlash.getTextFlashSubjectHi().toUpperCase().trim()
							: null);
					existingTextFlash.setTextFlashSubjectRl(!Util.isNullOrEmpty(textFlash.getTextFlashSubjectRl())
							? textFlash.getTextFlashSubjectRl().toUpperCase().trim()
							: null);
					existingTextFlash.setTextFlashUrl(
							!Util.isNullOrEmpty(textFlash.getTextFlashUrl()) ? textFlash.getTextFlashUrl().trim()
									: null);
					existingTextFlash
							.setTextFlashContentHtmlEn(!Util.isNullOrEmpty(textFlash.getTextFlashContentHtmlEn())
									? textFlash.getTextFlashContentHtmlEn()
									: null);
					existingTextFlash
							.setTextFlashContentHtmlHi(!Util.isNullOrEmpty(textFlash.getTextFlashContentHtmlHi())
									? textFlash.getTextFlashContentHtmlHi().toUpperCase().trim()
									: null);
					existingTextFlash
							.setTextFlashContentHtmlRl(!Util.isNullOrEmpty(textFlash.getTextFlashContentHtmlRl())
									? textFlash.getTextFlashContentHtmlRl()
									: null);
					existingTextFlash.setActiveFromDate(textFlash.getActiveFromDate());
					existingTextFlash.setActiveTill(textFlash.getActiveTill());
					existingTextFlash.setIsActive(textFlash.getIsActive() != null ? textFlash.getIsActive()
							: existingTextFlash.getIsActive());

					if (existingTextFlash.getIsActive() == null) {
						existingTextFlash.setIsActive(false);
					}

// Handle PDF file update for the existing record
					if (pdfFile != null && !pdfFile.isEmpty()) {
						try {
// Set the storage path for the PDF file
//String storagePath = "D:\\backendmaster\\pdfData"; // Use a dynamic path or config

// Get original file name (without extension)
							String originalFileName = pdfFile.getOriginalFilename();
							if (originalFileName == null || originalFileName.isEmpty()) {
								resultData.setStatus(false);
								resultData.setMessage("File name is missing.");
								return resultData;
							}

// Ensure that the file name does not contain invalid characters
							originalFileName = originalFileName.replaceAll("[^a-zA-Z0-9.-]", "_");

// Extract file name without extension
							String fileNameWithoutExtension = originalFileName.substring(0,
									originalFileName.lastIndexOf('.'));

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

// Check if file already exists, regenerate if necessary
							while (Files.exists(filePath)) {
								uniqueKey = generateUniqueKey(); // Regenerate the unique key if the file already exists
								newFileName = fileNameWithoutExtension + "_" + uniqueKey + ".pdf";
								filePath = Paths.get(storagePath, newFileName);
							}

// Log the file saving path
							log.info("Saving PDF file to: {}", filePath.toString());

// Save the PDF file to the local storage
							Files.write(filePath, pdfFile.getBytes());

// Update the file name in the DB model
							existingTextFlash.setPdfFileName1(newFileName);

						} catch (FileAlreadyExistsException e) {
							log.error("File already exists: {}", e.getMessage());
							resultData.setStatus(false);
							resultData.setMessage("File already exists.");
							return resultData;
						} catch (IOException e) {
							log.error("Error saving the PDF file: {}", e.getMessage());
							resultData.setStatus(false);
							resultData.setMessage("Error saving the PDF file.");
							return resultData;
						}
					}

// Update modification details
					existingTextFlash.setModifiedIpAddr(request.getRemoteAddr());
					existingTextFlash.setModifiedDate(new Date());
					existingTextFlash.setModifiedBy(request.getRemoteAddr());
					existingTextFlash.setModifiedMacAddr(UUID.randomUUID().toString());

					textFlash = existingTextFlash; // Use the updated existing menu master object

				} else {
					log.error("TextFlash Master not found");
					resultData.setStatus(false);
					resultData.setMessage("TextFlash Master not found");
					return resultData;
				}
			}

// Validation
			resultData = validator.validateTextFlash(textFlash);
			if (resultData != null && !resultData.getStatus()) {
				log.error("Validation failed: {}", resultData.getMessage());
				return resultData;
			}

// Set some default values for empty fields
			if (textFlash.getIsActive() == null)
				textFlash.setIsActive(false);

			textFlash.setTextFlashSubjectEn(!Util.isNullOrEmpty(textFlash.getTextFlashSubjectEn())
					? textFlash.getTextFlashSubjectEn().toUpperCase().trim()
					: null);
			textFlash.setTextFlashSubjectHi(!Util.isNullOrEmpty(textFlash.getTextFlashSubjectHi())
					? textFlash.getTextFlashSubjectHi().toUpperCase().trim()
					: null);
			textFlash.setTextFlashSubjectRl(!Util.isNullOrEmpty(textFlash.getTextFlashSubjectRl())
					? textFlash.getTextFlashSubjectRl().toUpperCase().trim()
					: null);
			textFlash.setTextFlashUrl(
					!Util.isNullOrEmpty(textFlash.getTextFlashUrl()) ? textFlash.getTextFlashUrl().trim() : null);
			textFlash.setTextFlashContentHtmlEn(
					!Util.isNullOrEmpty(textFlash.getTextFlashContentHtmlEn()) ? textFlash.getTextFlashContentHtmlEn()
							: null);
			textFlash.setTextFlashContentHtmlHi(!Util.isNullOrEmpty(textFlash.getTextFlashContentHtmlHi())
					? textFlash.getTextFlashContentHtmlHi().toUpperCase().trim()
					: null);
			textFlash.setTextFlashContentHtmlRl(
					!Util.isNullOrEmpty(textFlash.getTextFlashContentHtmlRl()) ? textFlash.getTextFlashContentHtmlRl()
							: null);
			textFlash.setActiveFromDate(textFlash.getActiveFromDate());
			textFlash.setActiveTill(textFlash.getActiveTill());
// Save or update the data
			try {
				textFlashRepo.save(textFlash);
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
	@GetMapping("/getTextFlashByGuid/{textFlashGuid}")
	public ResponseEntity<TextFlash> getTextFlashByGuid(@PathVariable("textFlashGuid") String textFlashGuid) {
		TextFlash textFlash = textFlashRepo.findById(textFlashGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with textFlashGuid : " + textFlashGuid));
		return new ResponseEntity<>(textFlash, HttpStatus.OK);
	}

/////////////////////////////////////TextFlash End///////////////////////////////////

/////////////////////////////////////FlashImage Start///////////////////////////////////
//get all data from table
	@GetMapping("/getFlashImageList")
	public ResponseEntity<BaseResponse> getFlashImageList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<FlashImage> list = flashImageRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setFlashImage(list);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/submitFlashImage")
	public BaseResponse submitFlashImage(@RequestBody FlashImage flashImage, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();
		try {
//Check if GUID is provided (indicating an update)
			if (flashImage.getFlashImageGuid() == null || flashImage.getFlashImageGuid().isEmpty()) {
//Add new data
				flashImage.setCreatedIpAddr(request.getRemoteAddr());
				flashImage.setFlashImageGuid(UUID.randomUUID().toString());
				flashImage.setCreatedDate(new Date());
				flashImage.setModifiedIpAddr(null);
				flashImage.setModifiedBy(null);
				flashImage.setModifiedDate(null);
				flashImage.setCreatedBy(request.getRemoteAddr());

				if (flashImage.getIsActive() == null)
					flashImage.setIsActive(false);

//Image upload code for creating new record
				if (flashImage.getUserImage1Base64() != null && !flashImage.getUserImage1Base64().isEmpty()) {
					try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
						String base64String = flashImage.getUserImage1Base64();
						if (base64String.contains("base64,")) {
							base64String = base64String.split("base64,")[1]; // Remove the prefix
						}

//Decode the base64 image string
						byte[] imageBytes = Base64.getDecoder().decode(base64String);
						flashImage.setImageBinary1(imageBytes); // Set image bytes to entity
					} catch (IllegalArgumentException e) {
						log.error("Invalid Base64 image data.", e);
						resultData.setStatus(false);
						resultData.setMessage("Invalid Base64 image data.");
						return resultData;
					}
				}

			} else {
//Update existing data
				FlashImage existingFlashImage = commonMasterService.getFlashImageById(flashImage.getFlashImageGuid());

				if (existingFlashImage != null) {
					existingFlashImage.setImageHeadingEn(!Util.isNullOrEmpty(flashImage.getImageHeadingEn())
							? flashImage.getImageHeadingEn().toUpperCase().trim()
							: null);

					existingFlashImage.setImageHeadingHi(!Util.isNullOrEmpty(flashImage.getImageHeadingHi())
							? flashImage.getImageHeadingHi().toUpperCase().trim()
							: null);

					existingFlashImage.setImageHeadingRl(!Util.isNullOrEmpty(flashImage.getImageHeadingRl())
							? flashImage.getImageHeadingRl().toUpperCase().trim()
							: null);

					existingFlashImage.setImageUrl(
							!Util.isNullOrEmpty(flashImage.getImageUrl()) ? flashImage.getImageUrl().trim() : null);

					existingFlashImage.setOrderNumber(
							!Util.isNullOrZero(flashImage.getOrderNumber()) ? flashImage.getOrderNumber() : null);

					existingFlashImage.setIsActive(flashImage.getIsActive() != null ? flashImage.getIsActive()
							: existingFlashImage.getIsActive());

					if (existingFlashImage.getIsActive() == null)
						existingFlashImage.setIsActive(false);

//Handle image update for existing record
					if (flashImage.getUserImage1Base64() != null && !flashImage.getUserImage1Base64().isEmpty()) {
						try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
							String base64String = flashImage.getUserImage1Base64();
							if (base64String.contains("base64,")) {
								base64String = base64String.split("base64,")[1]; // Remove the prefix
							}

//Decode the base64 image string
							byte[] imageBytes = Base64.getDecoder().decode(base64String);
							existingFlashImage.setImageBinary1(imageBytes); // Update the image bytes in existing object
						} catch (IllegalArgumentException e) {
							log.error("Invalid Base64 image data.", e);
							resultData.setStatus(false);
							resultData.setMessage("Invalid Base64 image data.");
							return resultData;
						}
					} else if (flashImage.getImageBinary1() != null && flashImage.getImageBinary1().length > 0) {
						existingFlashImage.setImageBinary1(flashImage.getImageBinary1());
					}

//Update modification details
					existingFlashImage.setModifiedIpAddr(request.getRemoteAddr());
					existingFlashImage.setModifiedDate(new Date());
					existingFlashImage.setModifiedBy(request.getRemoteAddr());
					existingFlashImage.setModifiedMacAddr(UUID.randomUUID().toString());
					flashImage = existingFlashImage; // Use the updated existing menu master object
				} else {
					log.error("FlashImage Master not found");
					resultData.setStatus(false);
					resultData.setMessage("FlashImage Master not found");
					return resultData;
				}
			}

//Validation
			resultData = validator.validateFlashImage(flashImage);
			if (resultData != null && !resultData.getStatus()) {
				log.error("Validation failed: {}", resultData.getMessage());
				return resultData;
			}

//Set some default values for empty fields
			if (flashImage.getIsActive() == null)
				flashImage.setIsActive(false);

			flashImage.setImageHeadingEn(!Util.isNullOrEmpty(flashImage.getImageHeadingEn())
					? flashImage.getImageHeadingEn().toUpperCase().trim()
					: null);

			flashImage.setImageHeadingHi(!Util.isNullOrEmpty(flashImage.getImageHeadingHi())
					? flashImage.getImageHeadingHi().toUpperCase().trim()
					: null);

			flashImage.setImageHeadingRl(!Util.isNullOrEmpty(flashImage.getImageHeadingRl())
					? flashImage.getImageHeadingRl().toUpperCase().trim()
					: null);

			flashImage.setImageUrl(
					!Util.isNullOrEmpty(flashImage.getImageUrl()) ? flashImage.getImageUrl().trim() : null);

			flashImage.setOrderNumber(
					!Util.isNullOrZero(flashImage.getOrderNumber()) ? flashImage.getOrderNumber() : null);

//Save or update the data
			try {
				flashImageRepo.save(flashImage);
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
	@GetMapping("/getFlashImageByGuid/{flashImageGuid}")
	public ResponseEntity<FlashImage> getFlashImageByGuid(@PathVariable("flashImageGuid") String flashImageGuid) {
		FlashImage flashImage = flashImageRepo.findById(flashImageGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with flashImageGuid : " + flashImageGuid));
		return new ResponseEntity<>(flashImage, HttpStatus.OK);
	}

/////////////////////////////////////FlashImage  End///////////////////////////////////

/////////////////////////////////////BgImage Start///////////////////////////////////
//get all data from table
	@GetMapping("/getBgImageList")
	public ResponseEntity<BaseResponse> getBgImageList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<BgImage> list = bgImageRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setBgImage(list);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/submitBgImage")
	public BaseResponse submitBgImage(@RequestBody BgImage bgImage, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();
		try {
//Check if GUID is provided (indicating an update)
			if (bgImage.getBgImageGuid() == null || bgImage.getBgImageGuid().isEmpty()) {
//Add new data
				bgImage.setCreatedIpAddr(request.getRemoteAddr());
				bgImage.setBgImageGuid(UUID.randomUUID().toString());
				bgImage.setCreatedDate(new Date());
				bgImage.setModifiedIpAddr(null);
				bgImage.setModifiedBy(null);
				bgImage.setModifiedDate(null);
				bgImage.setCreatedBy(request.getRemoteAddr());

				if (bgImage.getIsActive() == null)
					bgImage.setIsActive(false);

//Image upload code for creating new record
				if (bgImage.getUserImage1Base64() != null && !bgImage.getUserImage1Base64().isEmpty()) {
					try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
						String base64String = bgImage.getUserImage1Base64();
						if (base64String.contains("base64,")) {
							base64String = base64String.split("base64,")[1]; // Remove the prefix
						}

//Decode the base64 image string
						byte[] imageBytes = Base64.getDecoder().decode(base64String);
						bgImage.setImageBinary1(imageBytes); // Set image bytes to entity
					} catch (IllegalArgumentException e) {
						log.error("Invalid Base64 image data.", e);
						resultData.setStatus(false);
						resultData.setMessage("Invalid Base64 image data.");
						return resultData;
					}
				}

			} else {
//Update existing data
				BgImage existingBgImage = commonMasterService.getBgImageById(bgImage.getBgImageGuid());

				if (existingBgImage != null) {
					existingBgImage.setImageHeadingEn(!Util.isNullOrEmpty(bgImage.getImageHeadingEn())
							? bgImage.getImageHeadingEn().toUpperCase().trim()
							: null);

					existingBgImage.setImageHeadingHi(!Util.isNullOrEmpty(bgImage.getImageHeadingHi())
							? bgImage.getImageHeadingHi().toUpperCase().trim()
							: null);

					existingBgImage.setImageHeadingRl(!Util.isNullOrEmpty(bgImage.getImageHeadingRl())
							? bgImage.getImageHeadingRl().toUpperCase().trim()
							: null);

					existingBgImage.setOrderNumber(
							!Util.isNullOrZero(bgImage.getOrderNumber()) ? bgImage.getOrderNumber() : null);

					existingBgImage.setIsActive(
							bgImage.getIsActive() != null ? bgImage.getIsActive() : existingBgImage.getIsActive());

					if (existingBgImage.getIsActive() == null)
						existingBgImage.setIsActive(false);

//Handle image update for existing record
					if (bgImage.getUserImage1Base64() != null && !bgImage.getUserImage1Base64().isEmpty()) {
						try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
							String base64String = bgImage.getUserImage1Base64();
							if (base64String.contains("base64,")) {
								base64String = base64String.split("base64,")[1]; // Remove the prefix
							}

//Decode the base64 image string
							byte[] imageBytes = Base64.getDecoder().decode(base64String);
							existingBgImage.setImageBinary1(imageBytes); // Update the image bytes in existing object
						} catch (IllegalArgumentException e) {
							log.error("Invalid Base64 image data.", e);
							resultData.setStatus(false);
							resultData.setMessage("Invalid Base64 image data.");
							return resultData;
						}
					} else if (bgImage.getImageBinary1() != null && bgImage.getImageBinary1().length > 0) {
						existingBgImage.setImageBinary1(bgImage.getImageBinary1());
					}

//Update modification details
					existingBgImage.setModifiedIpAddr(request.getRemoteAddr());
					existingBgImage.setModifiedDate(new Date());
					existingBgImage.setModifiedBy(request.getRemoteAddr());
					existingBgImage.setModifiedMacAddr(UUID.randomUUID().toString());
					bgImage = existingBgImage; // Use the updated existing menu master object
				} else {
					log.error("BgImage Master not found");
					resultData.setStatus(false);
					resultData.setMessage("BgImage Master not found");
					return resultData;
				}
			}

//Validation
			resultData = validator.validateBgImage(bgImage);
			if (resultData != null && !resultData.getStatus()) {
				log.error("Validation failed: {}", resultData.getMessage());
				return resultData;
			}

//Set some default values for empty fields
			if (bgImage.getIsActive() == null)
				bgImage.setIsActive(false);

			bgImage.setImageHeadingEn(
					!Util.isNullOrEmpty(bgImage.getImageHeadingEn()) ? bgImage.getImageHeadingEn().toUpperCase().trim()
							: null);

			bgImage.setImageHeadingHi(
					!Util.isNullOrEmpty(bgImage.getImageHeadingHi()) ? bgImage.getImageHeadingHi().toUpperCase().trim()
							: null);

			bgImage.setImageHeadingRl(
					!Util.isNullOrEmpty(bgImage.getImageHeadingRl()) ? bgImage.getImageHeadingRl().toUpperCase().trim()
							: null);

			bgImage.setOrderNumber(!Util.isNullOrZero(bgImage.getOrderNumber()) ? bgImage.getOrderNumber() : null);

//Save or update the data
			try {
				bgImageRepo.save(bgImage);
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
	@GetMapping("/getBgImageByGuid/{bgImageGuid}")
	public ResponseEntity<BgImage> getBgImageByGuid(@PathVariable("bgImageGuid") String bgImageGuid) {
		BgImage bgImage = bgImageRepo.findById(bgImageGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with bgImageGuid : " + bgImageGuid));
		return new ResponseEntity<>(bgImage, HttpStatus.OK);
	}

//////////////////////////////////////////////BgImage End////////////////////////////

//////////////////////////////////////////////TenderDetails Start////////////////////////////

//get all data from table
	@GetMapping("/getTenderDetailsList")
	public ResponseEntity<BaseResponse> getTenderDetailsList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<TenderDetails> list = tenderDetailsRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setTenderDetails(list);
		return ResponseEntity.ok(response);
	}

//Create New Data And Update
	@PostMapping("/submitTenderDetails")
	public BaseResponse submitTenderDetails(@RequestBody TenderDetails tenderDetails, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();
		System.out.println("Received TenderDetails: " + tenderDetails); // Debugging output

//Check if guid is provided (indicating an update)
		if (tenderDetails.getTenderGuid() == null || tenderDetails.getTenderGuid().isEmpty()) {
//Add new data
			tenderDetails.setCreatedIpAddr(request.getRemoteAddr());
			tenderDetails.setTenderGuid(UUID.randomUUID().toString());
			tenderDetails.setCreatedDate(new Date());
			tenderDetails.setModifiedIpAddr(null);
			tenderDetails.setModifiedBy(null);
			tenderDetails.setModifiedDate(null);
			tenderDetails.setCreatedBy(request.getRemoteAddr());
//tenderDetails.setCreatedBy(userSessionParam.getEmpBasicGUID());
//tenderDetails.setCreaterRemarks(userSessionParam.getUserFullName());
//tenderDetails.setCreaterMacId(HttpSessionHelper.getMacAddress());
			// for dropdown
			tenderDetails.setOrgPrimary(tenderDetails.getOrgPrimaryGuid());

			if (tenderDetails.getOrgPrimary() != null && !tenderDetails.getOrgPrimary().isEmpty()) {
				tenderDetails.setOrgPrimaryMaster(new OrgPrimary(tenderDetails.getOrgPrimary()));
			}

			if (tenderDetails.getIsActive() == null)
				tenderDetails.setIsActive(false);
		} else {
//Update existing data
			TenderDetails existingTenderDetails = commonMasterService
					.getTenderDetailsById(tenderDetails.getTenderGuid());

			if (existingTenderDetails != null) {

				existingTenderDetails.setTenders(!Util.isNullOrEmpty(tenderDetails.getTenders())
						? tenderDetails.getTenders().toUpperCase().trim()
						: null);
				existingTenderDetails.setTRefNo(
						!Util.isNullOrEmpty(tenderDetails.getTRefNo()) ? tenderDetails.getTRefNo().toUpperCase().trim()
								: null);
				existingTenderDetails.setTTitle(
						!Util.isNullOrEmpty(tenderDetails.getTTitle()) ? tenderDetails.getTTitle().toUpperCase().trim()
								: null);
				existingTenderDetails.setTLocation(!Util.isNullOrEmpty(tenderDetails.getTLocation())
						? tenderDetails.getTLocation().toUpperCase().trim()
						: null);
				existingTenderDetails.setTInvitingOffAddress(!Util.isNullOrEmpty(tenderDetails.getTInvitingOffAddress())
						? tenderDetails.getTInvitingOffAddress().toUpperCase().trim()
						: null);
				existingTenderDetails.setTReturnUrl(!Util.isNullOrEmpty(tenderDetails.getTReturnUrl())
						? tenderDetails.getTReturnUrl().toUpperCase().trim()
						: null);

				existingTenderDetails.setTPubDate(tenderDetails.getTPubDate());

				existingTenderDetails.setIsActive(tenderDetails.getIsActive() != null ? tenderDetails.getIsActive()
						: existingTenderDetails.getIsActive());

				existingTenderDetails.setModifiedIpAddr(request.getRemoteAddr());
				existingTenderDetails.setModifiedDate(new Date());
				existingTenderDetails.setModifiedBy("admin");

//dropdown
				existingTenderDetails.setOrgPrimary(tenderDetails.getOrgPrimaryGuid());
				if (existingTenderDetails.getOrgPrimary() != null && !existingTenderDetails.getOrgPrimary().isEmpty()) {
					existingTenderDetails.setOrgPrimaryMaster(new OrgPrimary(existingTenderDetails.getOrgPrimary()));
				}

				if (existingTenderDetails.getIsActive() == null)
					existingTenderDetails.setIsActive(false);

//existingTenderDetails.setModifiedBy(UUID.randomUUID().toString());
//existingTenderDetails.setModifiedMacAddr(UUID.randomUUID().toString());

//existingTenderDetails.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
//existingTenderDetails.setModifierMacId(HttpSessionHelper.getMacAddress());
				tenderDetails = existingTenderDetails; // Use the updated existing assessmentYear object
			} else {
				log.error("TenderDetails not found");
				resultData.setStatus(false);
				resultData.setMessage("TenderDetails not found");
				return resultData;
			}
		}

//Validation
		resultData = validator.validateTenderDetails(tenderDetails);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", resultData.getMessage());
			return resultData;
		}

//If validation passes, proceed to save or update
		if (tenderDetails.getIsActive() == null)
			tenderDetails.setIsActive(false);

		tenderDetails.setTenders(
				!Util.isNullOrEmpty(tenderDetails.getTenders()) ? tenderDetails.getTenders().toUpperCase().trim()
						: null);
		tenderDetails.setTRefNo(
				!Util.isNullOrEmpty(tenderDetails.getTRefNo()) ? tenderDetails.getTRefNo().toUpperCase().trim() : null);
		tenderDetails.setTTitle(
				!Util.isNullOrEmpty(tenderDetails.getTTitle()) ? tenderDetails.getTTitle().toUpperCase().trim() : null);
		tenderDetails.setTLocation(
				!Util.isNullOrEmpty(tenderDetails.getTLocation()) ? tenderDetails.getTLocation().toUpperCase().trim()
						: null);
		tenderDetails.setTInvitingOffAddress(!Util.isNullOrEmpty(tenderDetails.getTInvitingOffAddress())
				? tenderDetails.getTInvitingOffAddress().toUpperCase().trim()
				: null);
		tenderDetails.setTReturnUrl(
				!Util.isNullOrEmpty(tenderDetails.getTReturnUrl()) ? tenderDetails.getTReturnUrl().toUpperCase().trim()
						: null);

		tenderDetails.setTPubDate(tenderDetails.getTPubDate());
		try {
			tenderDetailsRepo.save(tenderDetails);
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
	@GetMapping("/getTenderDetailsByGuid/{tenderGuid}")
	public ResponseEntity<TenderDetails> getTenderDetailsByGuid(@PathVariable("tenderGuid") String tenderGuid) {
		TenderDetails tenderDetails = tenderDetailsRepo.findById(tenderGuid)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found with tenderGuid : " + tenderGuid));
		return new ResponseEntity<>(tenderDetails, HttpStatus.OK);
	}

////////////////////////////////TenderDetails End////////////////////////////

////////////////////////////////////////////WebInfoManager Start //////////////////////////

//get all data from table
	@GetMapping("/getWebInfoManagerList")
	public ResponseEntity<BaseResponse> getWebInfoManagerList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<WebInfoManager> list = webInfoManagerRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setWebInfoManager(list);
		return ResponseEntity.ok(response);
	}

//Create New Data And Update
	@PostMapping("/submitWebInfoManager")
	public BaseResponse submitWebInfoManager(@RequestBody WebInfoManager webInfoManager, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
		if (webInfoManager.getInfoManagerGuid() == null || webInfoManager.getInfoManagerGuid().isEmpty()) {
//Add new data
			webInfoManager.setCreatedIpAddr(request.getRemoteAddr());
			webInfoManager.setInfoManagerGuid(UUID.randomUUID().toString());
			webInfoManager.setCreatedDate(new Date());
			webInfoManager.setModifiedIpAddr(null);
			webInfoManager.setModifiedBy(null);
			webInfoManager.setModifiedDate(null);
			webInfoManager.setCreatedBy(request.getRemoteAddr());

			if (webInfoManager.getIsActive() == null)
				webInfoManager.setIsActive(false);
//webInfoManager.setCreatedRemarks(userSessionParam.getUserFullName());
//webInfoManager.setCreaterMacId(HttpSessionHelper.getMacAddress());
//webInfoManager.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//webInfoManager.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

		} else {
//Update existing data
			WebInfoManager existingWebInfoManager = commonMasterService
					.getWebInfoManagerById(webInfoManager.getInfoManagerGuid());

			if (existingWebInfoManager != null) {

				existingWebInfoManager.setOfficerName(!Util.isNullOrEmpty(webInfoManager.getOfficerName())
						? webInfoManager.getOfficerName().toUpperCase().trim()
						: null);

				existingWebInfoManager.setTelNumber(!Util.isNullOrEmpty(webInfoManager.getTelNumber())
						? webInfoManager.getTelNumber().toUpperCase().trim()
						: null);

				existingWebInfoManager.setEmailId(!Util.isNullOrEmpty(webInfoManager.getEmailId())
						? webInfoManager.getEmailId().toUpperCase().trim()
						: null);

				existingWebInfoManager.setDesignation(
						!Util.isNullOrEmpty(webInfoManager.getDesignation()) ? webInfoManager.getDesignation().trim()
								: null);

				existingWebInfoManager.setAddress(
						!Util.isNullOrEmpty(webInfoManager.getAddress()) ? webInfoManager.getAddress().trim() : null);

				existingWebInfoManager.setIsActive(webInfoManager.getIsActive() != null ? webInfoManager.getIsActive()
						: existingWebInfoManager.getIsActive());

				existingWebInfoManager.setModifiedIpAddr(request.getRemoteAddr());
				existingWebInfoManager.setModifiedDate(new Date());
				if (existingWebInfoManager.getIsActive() == null)
					existingWebInfoManager.setIsActive(false);

//for now setting some dummy value to test
				existingWebInfoManager.setModifiedBy(UUID.randomUUID().toString());
				existingWebInfoManager.setModifiedMacAddr(UUID.randomUUID().toString());
				webInfoManager = existingWebInfoManager; // Use the updated existing country object
			} else {
				log.error("WebInfo Manager not found");
				resultData.setStatus(false);
				resultData.setMessage("WebInfo Manager not found");
				return resultData;
			}
		}

//Validation
		resultData = validator.validateWebInfoManager(webInfoManager);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", resultData.getMessage());
			return resultData;
		}

//If validation passes, proceed to save or update
		if (webInfoManager.getIsActive() == null)
			webInfoManager.setIsActive(false);

		webInfoManager.setOfficerName(!Util.isNullOrEmpty(webInfoManager.getOfficerName())
				? webInfoManager.getOfficerName().toUpperCase().trim()
				: null);

		webInfoManager.setTelNumber(
				!Util.isNullOrEmpty(webInfoManager.getTelNumber()) ? webInfoManager.getTelNumber().toUpperCase().trim()
						: null);

		webInfoManager.setEmailId(
				!Util.isNullOrEmpty(webInfoManager.getEmailId()) ? webInfoManager.getEmailId().toUpperCase().trim()
						: null);

		webInfoManager.setDesignation(
				!Util.isNullOrEmpty(webInfoManager.getDesignation()) ? webInfoManager.getDesignation().trim() : null);

		webInfoManager.setAddress(
				!Util.isNullOrEmpty(webInfoManager.getAddress()) ? webInfoManager.getAddress().trim() : null);

		try {
			webInfoManagerRepo.save(webInfoManager);
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
	@GetMapping("/getWebInfoManagerByGuid/{infoManagerGuid}")
	public ResponseEntity<WebInfoManager> getWebInfoManagerByGuid(
			@PathVariable("infoManagerGuid") String infoManagerGuid) {
		WebInfoManager webInfoManager = webInfoManagerRepo.findById(infoManagerGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with infoManagerGuid : " + infoManagerGuid));
		return new ResponseEntity<>(webInfoManager, HttpStatus.OK);
	}

////////////////////////////////////////////WebInfoManager End //////////////////////////

////////////////////////////////////////////VideoGallery Start //////////////////////////

//get all data from table
	@GetMapping("/getVideoGalleryList")
	public ResponseEntity<BaseResponse> getVideoGalleryList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<VideoGallery> list = videoGalleryRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setVideoGallery(list);
		return ResponseEntity.ok(response);
	}

//Create New Data And Update
	@PostMapping("/submitVideoGallery")
	public BaseResponse submitVideoGallery(@RequestBody VideoGallery videoGallery, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

//Check if guid is provided (indicating an update)
		if (videoGallery.getVideoGalleryGuid() == null || videoGallery.getVideoGalleryGuid().isEmpty()) {
//Add new data
			videoGallery.setCreatedIpAddr(request.getRemoteAddr());
			videoGallery.setVideoGalleryGuid(UUID.randomUUID().toString());
			videoGallery.setCreatedDate(new Date());
			videoGallery.setModifiedIpAddr(null);
			videoGallery.setModifiedBy(null);
			videoGallery.setModifiedDate(null);
			videoGallery.setCreatedBy(request.getRemoteAddr());

			if (videoGallery.getIsActive() == null)
				videoGallery.setIsActive(false);
//videoGallery.setCreatedRemarks(userSessionParam.getUserFullName());
//videoGallery.setCreaterMacId(HttpSessionHelper.getMacAddress());
//videoGallery.setCreatedIpAddr(HttpSessionHelper.getClientIPAddress(request));
//videoGallery.setCreatedMacAddr(HttpSessionHelper.getMacAddress());

		} else {
//Update existing data
			VideoGallery existingVideoGallery = commonMasterService
					.getVideoGalleryById(videoGallery.getVideoGalleryGuid());

			if (existingVideoGallery != null) {

				existingVideoGallery.setVideoFile(!Util.isNullOrEmpty(videoGallery.getVideoFile())
						? videoGallery.getVideoFile().toUpperCase().trim()
						: null);

				existingVideoGallery.setVideoGalleryNameEn(!Util.isNullOrEmpty(videoGallery.getVideoGalleryNameEn())
						? videoGallery.getVideoGalleryNameEn().toUpperCase().trim()
						: null);

				existingVideoGallery.setVideoGalleryNameHi(!Util.isNullOrEmpty(videoGallery.getVideoGalleryNameHi())
						? videoGallery.getVideoGalleryNameHi().toUpperCase().trim()
						: null);

				existingVideoGallery.setVideoGalleryNameRl(!Util.isNullOrEmpty(videoGallery.getVideoGalleryNameRl())
						? videoGallery.getVideoGalleryNameRl().trim()
						: null);

				existingVideoGallery.setVideoGalleryUrl(!Util.isNullOrEmpty(videoGallery.getVideoGalleryUrl())
						? videoGallery.getVideoGalleryUrl().trim()
						: null);

				existingVideoGallery.setVideoHeadingEn(
						!Util.isNullOrEmpty(videoGallery.getVideoHeadingEn()) ? videoGallery.getVideoHeadingEn().trim()
								: null);

				existingVideoGallery.setVideoHeadingHi(
						!Util.isNullOrEmpty(videoGallery.getVideoHeadingHi()) ? videoGallery.getVideoHeadingHi().trim()
								: null);

				existingVideoGallery.setVideoHeadingRl(
						!Util.isNullOrEmpty(videoGallery.getVideoHeadingRl()) ? videoGallery.getVideoHeadingRl().trim()
								: null);

				existingVideoGallery.setOrderNumber(
						!Util.isNullOrZero(videoGallery.getOrderNumber()) ? videoGallery.getOrderNumber() : null);

				existingVideoGallery.setIsActive(videoGallery.getIsActive() != null ? videoGallery.getIsActive()
						: existingVideoGallery.getIsActive());

				existingVideoGallery.setModifiedIpAddr(request.getRemoteAddr());
				existingVideoGallery.setModifiedDate(new Date());
				if (existingVideoGallery.getIsActive() == null)
					existingVideoGallery.setIsActive(false);

//for now setting some dummy value to test
				existingVideoGallery.setModifiedBy(UUID.randomUUID().toString());
				existingVideoGallery.setModifiedMacAddr(UUID.randomUUID().toString());
				videoGallery = existingVideoGallery; // Use the updated existing videoGallery object
			} else {
				log.error("Video Gallery not found");
				resultData.setStatus(false);
				resultData.setMessage("Video Gallery not found");
				return resultData;
			}
		}

//Validation
		resultData = validator.validateVideoGallery(videoGallery);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", resultData.getMessage());
			return resultData;
		}

//If validation passes, proceed to save or update
		if (videoGallery.getIsActive() == null)
			videoGallery.setIsActive(false);

		videoGallery.setVideoFile(
				!Util.isNullOrEmpty(videoGallery.getVideoFile()) ? videoGallery.getVideoFile().toUpperCase().trim()
						: null);

		videoGallery.setVideoGalleryNameEn(!Util.isNullOrEmpty(videoGallery.getVideoGalleryNameEn())
				? videoGallery.getVideoGalleryNameEn().toUpperCase().trim()
				: null);

		videoGallery.setVideoGalleryNameHi(!Util.isNullOrEmpty(videoGallery.getVideoGalleryNameHi())
				? videoGallery.getVideoGalleryNameHi().toUpperCase().trim()
				: null);

		videoGallery.setVideoGalleryNameRl(
				!Util.isNullOrEmpty(videoGallery.getVideoGalleryNameRl()) ? videoGallery.getVideoGalleryNameRl().trim()
						: null);

		videoGallery.setVideoGalleryUrl(
				!Util.isNullOrEmpty(videoGallery.getVideoGalleryUrl()) ? videoGallery.getVideoGalleryUrl().trim()
						: null);

		videoGallery.setVideoHeadingEn(
				!Util.isNullOrEmpty(videoGallery.getVideoHeadingEn()) ? videoGallery.getVideoHeadingEn().trim() : null);

		videoGallery.setVideoHeadingHi(
				!Util.isNullOrEmpty(videoGallery.getVideoHeadingHi()) ? videoGallery.getVideoHeadingHi().trim() : null);

		videoGallery.setVideoHeadingRl(
				!Util.isNullOrEmpty(videoGallery.getVideoHeadingRl()) ? videoGallery.getVideoHeadingRl().trim() : null);

		videoGallery.setOrderNumber(
				!Util.isNullOrZero(videoGallery.getOrderNumber()) ? videoGallery.getOrderNumber() : null);

		try {
			videoGalleryRepo.save(videoGallery);
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
	@GetMapping("/getVideoGalleryByGuid/{videoGalleryGuid}")
	public ResponseEntity<VideoGallery> getVideoGalleryByGuid(
			@PathVariable("videoGalleryGuid") String videoGalleryGuid) {
		VideoGallery videoGallery = videoGalleryRepo.findById(videoGalleryGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with videoGalleryGuid : " + videoGalleryGuid));
		return new ResponseEntity<>(videoGallery, HttpStatus.OK);
	}

////////////////////////////////////////////VideoGallery End //////////////////////////

/////////////////////////////////////HeaderRibbon Start///////////////////////////////////
//get all data from table
	@GetMapping("/getHeaderRibbonList")
	public ResponseEntity<BaseResponse> getHeaderRibbonList() {
		BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<HeaderRibbon> list = headerRibbonRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setHeaderRibbon(list);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/submitHeaderRibbon")
	public BaseResponse submitHeaderRibbon(@ModelAttribute HeaderRibbon headerRibbon,
			@RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

		try {
//System.out.println("aa gya");
//Check if GUID is provided (indicating an update)
			if (headerRibbon.getHeaderRibbonGuid() == null || headerRibbon.getHeaderRibbonGuid().isEmpty()) {
//Add new data
				headerRibbon.setCreatedIpAddr(request.getRemoteAddr());
				headerRibbon.setHeaderRibbonGuid(UUID.randomUUID().toString());
				headerRibbon.setCreatedDate(new Date());
				headerRibbon.setModifiedIpAddr(null);
				headerRibbon.setModifiedBy(null);
				headerRibbon.setModifiedDate(null);
				headerRibbon.setCreatedBy(request.getRemoteAddr());

				if (headerRibbon.getIsActive() == null) {
					headerRibbon.setIsActive(false);
				}

//Image upload code for creating new record
				if (headerRibbon.getUserImage1Base64() != null && !headerRibbon.getUserImage1Base64().isEmpty()) {
					try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
						String base64String = headerRibbon.getUserImage1Base64();
						if (base64String.contains("base64,")) {
							base64String = base64String.split("base64,")[1]; // Remove the prefix
						}

//Decode the base64 image string
						byte[] imageBytes = Base64.getDecoder().decode(base64String);
						headerRibbon.setImageBinary1(imageBytes); // Set image bytes to entity
					} catch (IllegalArgumentException e) {
						log.error("Invalid Base64 image data.", e);
						resultData.setStatus(false);
						resultData.setMessage("Invalid Base64 image data.");
						return resultData;
					}
				}

//Handling PDF file upload for new record
				if (pdfFile != null && !pdfFile.isEmpty()) {
					try {
//Set the storage path for the PDF file
//String storagePath = "D:\\backendmaster\\pdfData"; // Use a dynamic path or config

//Get original file name (without extension)
						String originalFileName = pdfFile.getOriginalFilename();
						if (originalFileName == null) {
							resultData.setStatus(false);
							resultData.setMessage("File name is missing.");
							return resultData;
						}

//Extract file name without extension
						String fileNameWithoutExtension = originalFileName.substring(0,
								originalFileName.lastIndexOf('.'));

//Generate a 10-digit unique number
						String uniqueKey = generateUniqueKey();

//Combine file name with unique key to create the new file name
						String newFileName = fileNameWithoutExtension + "_" + uniqueKey + ".pdf";

//Define the file path where PDF will be saved locally
						Path filePath = Paths.get(storagePath, newFileName);

//Create directory if it doesn't exist
						if (!Files.exists(filePath.getParent())) {
							Files.createDirectories(filePath.getParent());
						}

//Save the PDF file to the local storage
						Files.write(filePath, pdfFile.getBytes());

//Update the file name in the DB model
						headerRibbon.setHeaderImagePdf1(newFileName);

					} catch (IOException e) {
						log.error("Error saving the PDF file: {}", e.getMessage());
						resultData.setStatus(false);
						resultData.setMessage("Error saving the PDF file.");
						return resultData;
					}
				}

			} else {
//Update existing data
				HeaderRibbon existingHeaderRibbon = commonMasterService
						.getHeaderRibbonById(headerRibbon.getHeaderRibbonGuid());

				if (existingHeaderRibbon != null) {
					existingHeaderRibbon.setImageHeadingEn(!Util.isNullOrEmpty(headerRibbon.getImageHeadingEn())
							? headerRibbon.getImageHeadingEn().toUpperCase().trim()
							: null);
					existingHeaderRibbon.setImageHeadingHi(!Util.isNullOrEmpty(headerRibbon.getImageHeadingHi())
							? headerRibbon.getImageHeadingHi().toUpperCase().trim()
							: null);
					existingHeaderRibbon.setImageHeadingRl(!Util.isNullOrEmpty(headerRibbon.getImageHeadingRl())
							? headerRibbon.getImageHeadingRl().toUpperCase().trim()
							: null);
					existingHeaderRibbon.setOrderNumber(
							!Util.isNullOrZero(headerRibbon.getOrderNumber()) ? headerRibbon.getOrderNumber() : null);
					existingHeaderRibbon.setHeaderImageUrl(
							!Util.isNullOrEmpty(headerRibbon.getHeaderImageUrl()) ? headerRibbon.getHeaderImageUrl()
									: null);

					existingHeaderRibbon.setIsActive(headerRibbon.getIsActive() != null ? headerRibbon.getIsActive()
							: existingHeaderRibbon.getIsActive());

					if (existingHeaderRibbon.getIsActive() == null) {
						existingHeaderRibbon.setIsActive(false);
					}

//Handle image update for existing record
					if (headerRibbon.getUserImage1Base64() != null && !headerRibbon.getUserImage1Base64().isEmpty()) {
						try {
//Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
							String base64String = headerRibbon.getUserImage1Base64();
							if (base64String.contains("base64,")) {
								base64String = base64String.split("base64,")[1]; // Remove the prefix
							}

//Decode the base64 image string
							byte[] imageBytes = Base64.getDecoder().decode(base64String);
							existingHeaderRibbon.setImageBinary1(imageBytes); // Update the image bytes in existing
																				// object
						} catch (IllegalArgumentException e) {
							log.error("Invalid Base64 image data.", e);
							resultData.setStatus(false);
							resultData.setMessage("Invalid Base64 image data.");
							return resultData;
						}
					} else if (headerRibbon.getImageBinary1() != null && headerRibbon.getImageBinary1().length > 0) {
						existingHeaderRibbon.setImageBinary1(headerRibbon.getImageBinary1());
					}

//Handle PDF file update for the existing record
					if (pdfFile != null && !pdfFile.isEmpty()) {
						try {
//Set the storage path for the PDF file
//String storagePath = "D:\\backendmaster\\pdfData"; // Use a dynamic path or config

//Get original file name (without extension)
							String originalFileName = pdfFile.getOriginalFilename();
							if (originalFileName == null || originalFileName.isEmpty()) {
								resultData.setStatus(false);
								resultData.setMessage("File name is missing.");
								return resultData;
							}

//Ensure that the file name does not contain invalid characters
							originalFileName = originalFileName.replaceAll("[^a-zA-Z0-9.-]", "_");

//Extract file name without extension
							String fileNameWithoutExtension = originalFileName.substring(0,
									originalFileName.lastIndexOf('.'));

//Generate a 10-digit unique number
							String uniqueKey = generateUniqueKey();

//Combine file name with unique key to create the new file name
							String newFileName = fileNameWithoutExtension + "_" + uniqueKey + ".pdf";

//Define the file path where PDF will be saved locally
							Path filePath = Paths.get(storagePath, newFileName);

//Create directory if it doesn't exist
							if (!Files.exists(filePath.getParent())) {
								Files.createDirectories(filePath.getParent());
							}

//Check if file already exists, regenerate if necessary
							while (Files.exists(filePath)) {
								uniqueKey = generateUniqueKey(); // Regenerate the unique key if the file already exists
								newFileName = fileNameWithoutExtension + "_" + uniqueKey + ".pdf";
								filePath = Paths.get(storagePath, newFileName);
							}

//Log the file saving path
							log.info("Saving PDF file to: {}", filePath.toString());

//Save the PDF file to the local storage
							Files.write(filePath, pdfFile.getBytes());

//Update the file name in the DB model
							existingHeaderRibbon.setHeaderImagePdf1(newFileName);

						} catch (FileAlreadyExistsException e) {
							log.error("File already exists: {}", e.getMessage());
							resultData.setStatus(false);
							resultData.setMessage("File already exists.");
							return resultData;
						} catch (IOException e) {
							log.error("Error saving the PDF file: {}", e.getMessage());
							resultData.setStatus(false);
							resultData.setMessage("Error saving the PDF file.");
							return resultData;
						}
					}

//Update modification details
					existingHeaderRibbon.setModifiedIpAddr(request.getRemoteAddr());
					existingHeaderRibbon.setModifiedDate(new Date());
					existingHeaderRibbon.setModifiedBy(request.getRemoteAddr());
					existingHeaderRibbon.setModifiedMacAddr(UUID.randomUUID().toString());

					headerRibbon = existingHeaderRibbon; // Use the updated existing menu master object

				} else {
					log.error("Headr Ribbon Master not found");
					resultData.setStatus(false);
					resultData.setMessage("Headr Ribbon Master not found");
					return resultData;
				}
			}

//Validation
			resultData = validator.validateHeaderRibbon(headerRibbon);
			if (resultData != null && !resultData.getStatus()) {
				log.error("Validation failed: {}", resultData.getMessage());
				return resultData;
			}

//Set some default values for empty fields
			if (headerRibbon.getIsActive() == null)
				headerRibbon.setIsActive(false);

			headerRibbon.setImageHeadingEn(!Util.isNullOrEmpty(headerRibbon.getImageHeadingEn())
					? headerRibbon.getImageHeadingEn().toUpperCase().trim()
					: null);
			headerRibbon.setImageHeadingHi(!Util.isNullOrEmpty(headerRibbon.getImageHeadingHi())
					? headerRibbon.getImageHeadingHi().toUpperCase().trim()
					: null);
			headerRibbon.setImageHeadingRl(!Util.isNullOrEmpty(headerRibbon.getImageHeadingRl())
					? headerRibbon.getImageHeadingRl().toUpperCase().trim()
					: null);
			headerRibbon.setOrderNumber(
					!Util.isNullOrZero(headerRibbon.getOrderNumber()) ? headerRibbon.getOrderNumber() : null);
			headerRibbon.setHeaderImageUrl(
					!Util.isNullOrEmpty(headerRibbon.getHeaderImageUrl()) ? headerRibbon.getHeaderImageUrl() : null);

			headerRibbon.setIsActive(
					headerRibbon.getIsActive() != null ? headerRibbon.getIsActive() : headerRibbon.getIsActive());

//Save or update the data
			try {
				headerRibbonRepo.save(headerRibbon);
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
	@GetMapping("/getHeaderRibbonByGuid/{headerRibbonGuid}")
	public ResponseEntity<HeaderRibbon> getHeaderRibbonByGuid(
			@PathVariable("headerRibbonGuid") String headerRibbonGuid) {
		HeaderRibbon headerRibbon = headerRibbonRepo.findById(headerRibbonGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with headerRibbonGuid : " + headerRibbonGuid));
		return new ResponseEntity<>(headerRibbon, HttpStatus.OK);
	}

/////////////////////////////////////HeaderRibbon End///////////////////////////////////

/////////////////////////////////////Menu Start///////////////////////////////////

//get all data from table
	@GetMapping("/getMenuList")
	public ResponseEntity<BaseResponse> getMenuList() {
		BaseResponse response = new BaseResponse();
// Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<Menu> list = menuRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setMenu(list);
		return ResponseEntity.ok(response);
	}

// Create New Data And Update
	@PostMapping("/submitMenu")
	public BaseResponse submitMenu(@RequestBody Menu menu, HttpServletRequest request) {
 		BaseResponse resultData = new BaseResponse();
		// Fetch active primary menus for dropdown or reference
		//List<Menu> primaryMenus = getPrimaryMenu();
		// Check if guid is provided (indicating an update)
		if (menu.getMenuGuid() == null || menu.getMenuGuid().isEmpty()) {

			// Add new data menu.setCreatedIpAddr(request.getRemoteAddr());
			menu.setMenuGuid(UUID.randomUUID().toString());
			menu.setCreatedDate(new Date());
			menu.setModifiedIpAddr(null);
			menu.setModifiedBy(null);
			menu.setModifiedDate(null);
			menu.setCreatedBy(request.getRemoteAddr());
			menu.setCreatedIpAddr(request.getRemoteAddr());

			if (menu.getIsActive() == null)
				menu.setIsActive(false);
			
			if (menu.getPrimaryMenuGuid() == null || menu.getPrimaryMenuGuid().equalsIgnoreCase("")) {
			    menu.setPrimaryMenuGuid(null);
			} else {
			    if (menu.getSecondaryMenuGuid() == null || menu.getSecondaryMenuGuid().equalsIgnoreCase("")) {
			        menu.setPrimaryMenuGuid(menu.getPrimaryMenuGuid());
			    } else {
			        if (menu.getTernaryMenuGuid() == null || menu.getTernaryMenuGuid().equalsIgnoreCase("")) {
			            menu.setPrimaryMenuGuid(menu.getSecondaryMenuGuid());
			        } else {
			            if (menu.getFourthMenuGuid() == null || menu.getFourthMenuGuid().equalsIgnoreCase("")) {
			                menu.setPrimaryMenuGuid(menu.getTernaryMenuGuid());
			            } else {
			                menu.setPrimaryMenuGuid(menu.getFourthMenuGuid());
			            }
			        }
			    }
			}


			// menu.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
			// menu.setCreaterRemarks(userSessionParam.getUserFullName());
			// menu.setCreaterMacId(HttpSessionHelper.getMacAddress());
			// menu.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
			// Sethe primary menu for dropdown reference
			// Set primary menu if provided

		} else {
			// Update existing data
			Menu existingMenu = commonMasterService.getMenuById(menu.getMenuGuid());

			if (existingMenu != null) {
				existingMenu.setMenuNameEn(
						!Util.isNullOrEmpty(menu.getMenuNameEn()) ? menu.getMenuNameEn().toUpperCase().trim() : null);
				existingMenu.setMenuNameHi(
						!Util.isNullOrEmpty(menu.getMenuNameHi()) ? menu.getMenuNameHi().toUpperCase().trim() : null);
				existingMenu.setMenuNameRl(
						!Util.isNullOrEmpty(menu.getMenuNameRl()) ? menu.getMenuNameRl().toUpperCase().trim() : null);
				existingMenu.setMenuUrl(!Util.isNullOrEmpty(menu.getMenuUrl()) ? menu.getMenuUrl().trim() : null);
				existingMenu.setOrderNumber(!Util.isNullOrZero(menu.getOrderNumber()) ? menu.getOrderNumber() : null);
				existingMenu.setIsAuthorized(
						!Util.isNullOrEmpty(menu.getIsAuthorized()) ? menu.getIsAuthorized().trim() : null);
				existingMenu.setIsActive(menu.getIsActive() != null ? menu.getIsActive() : existingMenu.getIsActive());
				if (existingMenu.getIsActive() == null)
					existingMenu.setIsActive(false);

				existingMenu.setModifiedIpAddr(request.getRemoteAddr());
				existingMenu.setModifiedDate(new Date());
				existingMenu.setModifiedBy("admin");
				menu = existingMenu; // Use the updated existing country object }
			} else {
				log.error("Menu not found");
				resultData.setStatus(false);
				resultData.setMessage("Menu not found");
				return resultData;
			}
		}

		// Validation
		resultData = validator.validateMenu(menu);
		if (resultData != null && !resultData.getStatus()) {
			log.error("Validation failed: {}", 
			resultData.getMessage());
			return resultData;
		}

		// If validation passes, proceed to save or update
		if (menu.getIsActive() == null)
			menu.setIsActive(false);
		
		
		
		menu.setMenuNameEn(
				!Util.isNullOrEmpty(menu.getMenuNameEn()) ? menu.getMenuNameEn().toUpperCase().trim() : null);
		menu.setMenuNameHi(
				!Util.isNullOrEmpty(menu.getMenuNameHi()) ? menu.getMenuNameHi().toUpperCase().trim() : null);

		menu.setMenuNameRl(
				!Util.isNullOrEmpty(menu.getMenuNameRl()) ? menu.getMenuNameRl().toUpperCase().trim() : null);
		menu.setMenuUrl(!Util.isNullOrEmpty(menu.getMenuUrl()) ? menu.getMenuUrl().trim() : null);
		menu.setOrderNumber(!Util.isNullOrZero(menu.getOrderNumber()) ? menu.getOrderNumber() : null);
		menu.setIsAuthorized(!Util.isNullOrEmpty(menu.getIsAuthorized()) ? menu.getIsAuthorized().trim() : null);

		try {
			menuRepo.save(menu);
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

	

//primary menu 
	@GetMapping("/getPrimaryMenu")
	public @ResponseBody List<Menu> getPrimaryMenu() {
		try {
			return menuRepo.findByPrimaryMenuGuidIsNullAndIsActiveTrue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//SecondryMenu 
	@GetMapping("/getAllMappedMenuForSecondryMenu/{primaryMenuGuid}")
	public @ResponseBody List<Menu> getAllMappedSecondryMenu(@PathVariable("primaryMenuGuid") String primaryMenuGuid) {
		try {
			return menuRepo.getSecondryMenuList(primaryMenuGuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Ternary Menu Controller */
	@GetMapping("/getAllMappedMenuForTernaryMenu/{secondryMenuGuid}")
	public @ResponseBody List<Menu> getAllMappedTernaryMenu(@PathVariable("secondryMenuGuid") String secondryMenuGuid) {
		try {
			return menuRepo.getTernaryMenuList(secondryMenuGuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Fourth Menu Controller */
	@GetMapping("/getAllMappedMenuForFourthMenu/{ternaryMenuGuid}")
	public @ResponseBody List<Menu> getAllMappedFourthMenu(@PathVariable("ternaryMenuGuid") String ternaryMenuGuid) {
		try {
			return menuRepo.getFourthMenuList(ternaryMenuGuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//get data by id
	@GetMapping("/getMenuByGuid/{menuGuid}")
	public ResponseEntity<Menu> geMenuByGuid(@PathVariable("menuGuid") String menuGuid) {
		Menu menu = menuRepo.findById(menuGuid)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found with menuGuid : " + menuGuid));
		return new ResponseEntity<>(menu, HttpStatus.OK);
	}

/////////////////////////////////////Menu End///////////////////////////////////

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
