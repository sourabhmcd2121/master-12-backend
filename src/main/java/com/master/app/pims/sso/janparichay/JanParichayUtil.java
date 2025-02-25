package com.master.app.pims.sso.janparichay;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.master.app.pims.config.AppEnvConstant;
import com.master.app.pims.utils.Util;


public class JanParichayUtil implements Cloneable {
	 private static final Logger logger = LogManager.getLogger();
//  -------------------------------------------------------------------------------------------------------------------------
	private static String hmacFunc(long timeStamp, String SERVICE, String PARICHAY_URL)
			throws IOException, InterruptedException {
		String hmacString = "JanParichay" + timeStamp + PARICHAY_URL + "/v1/api/login" + SERVICE;
		System.out.println("hmacString :- " + hmacString);
		String requestBody = "{\"HmacString\":\"" + hmacString + "\"}";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(AppEnvConstant.JAN_PARICHAY_HMAC_URL,
				requestEntity, String.class);
		String str = response.getBody();
		System.out.println(str);
		Object obj = new JSONObject(str);
		JSONObject json = (JSONObject) obj;
		String encrptReturnReponse = ((JSONObject) json.get("data")).get("signature").toString();
		System.out.println("hmacReturnReponse :- " + encrptReturnReponse);
		return encrptReturnReponse;

	}

	public static String handShakingFunc(String encrString)
			throws IOException, InterruptedException, URISyntaxException {
		URL url = new URL(AppEnvConstant.JAN_PARICHAY_HANDSHAKING_URL
				+ "?handshakingId="+encrString+"&sid="+AppEnvConstant.JAN_PARICHAY_SERVICE_NAME);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//System.out.println(JanParichayEncryptDecrypt.decryptFunc(encrString));
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output = br.readLine();
		System.out.println(output);
		System.out.println("Output from Server .... \n");
		return output;

	}
//----------------------------------------------------------------------------------------------------------------------  

	
	public static String createLoginURL() {
		try {
			System.out.println("Login Func called.");
			long timeStamp = System.currentTimeMillis();
			System.out.println("timeStamp : " + timeStamp);
			String service = AppEnvConstant.JAN_PARICHAY_SERVICE_NAME;
			System.out.println("Service Name : " + service);
			String parichayUrl = AppEnvConstant.JAN_PARICHAY_URL;
			System.out.println("ParichayUrl : " + parichayUrl);
			String hmac_sign = hmacFunc(timeStamp, service, parichayUrl);
			System.out.println("hmac_response : " + hmac_sign);
			String encrpt_sign = JanParichayEncryptDecrypt.encryptFunc();
			System.out.println("encryption_response : " + encrpt_sign);
			String loginURL = parichayUrl + "/v1/api/login?sid=" + service + "&tid="
					+ timeStamp + "&cs=" + hmac_sign + "&string=" + encrpt_sign + "&lang=en";
			return loginURL.trim();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}

	public static String createClientTokenValidatorURL(String encrpytedToken) {
		 String serviceName = AppEnvConstant.JAN_PARICHAY_SERVICE_NAME;
		    if (Util.isNullOrEmpty(encrpytedToken) || 
		      Util.isNullOrEmpty(serviceName))
		      throw new RuntimeException("PARAMETER EMPTY"); 
		    StringBuilder ssoClientTokenValidatorURLBuilder = new StringBuilder(AppEnvConstant.JAN_PARICHAY_TOKEN_VALIDATE);
		    if (!AppEnvConstant.JAN_PARICHAY_TOKEN_VALIDATE.endsWith("/"))
		      ssoClientTokenValidatorURLBuilder.append("/"); 
		    ssoClientTokenValidatorURLBuilder.append(encrpytedToken);
		    ssoClientTokenValidatorURLBuilder.append("/");
		    ssoClientTokenValidatorURLBuilder.append(serviceName);
		    return ssoClientTokenValidatorURLBuilder.toString();
	}

	public static JanParichayResponse parseResponse(String encryptedToken) {
		String secretAPIKey = AppEnvConstant.JAN_PARICHAY_SERVICE_API_KEY;
	    if (Util.isNullOrEmpty(encryptedToken) || Util.isNullOrEmpty(secretAPIKey))
	      return null; 
	    try {
	      String decryptToken = JanParichayEncryptDecrypt.decryptResponse(encryptedToken, secretAPIKey);
	      Map<String, Object> parichayMap = convertJSONToMap(decryptToken);
	      if (parichayMap != null && parichayMap.size() > 0) {
	        JanParichayResponse parichayResponse = new JanParichayResponse();
	        parichayResponse.setAuthenticated(true);
	        parichayResponse.setEncrpytedToken(encryptedToken);
	        if (parichayMap.containsKey("firstName"))
	          parichayResponse.setFirstName((String)parichayMap.get("firstName")); 
	        if (parichayMap.containsKey("lastName"))
	          parichayResponse.setLastName((String)parichayMap.get("lastName")); 
	        if (parichayMap.containsKey("fullName"))
	          parichayResponse.setFullName((String)parichayMap.get("fullName")); 
	        if (parichayMap.containsKey("email"))
	          parichayResponse.setEmail((String)parichayMap.get("email")); 
	        if (parichayMap.containsKey("mobileNo"))
	          parichayResponse.setMobileNumber((String)parichayMap.get("mobileNo")); 
	        if (parichayMap.containsKey("designation"))
	          parichayResponse.setDesignation((String)parichayMap.get("designation")); 
	        if (parichayMap.containsKey("employeeCode"))
	          parichayResponse.setEmployeeCode((String)parichayMap.get("employeeCode")); 
	        if (parichayMap.containsKey("status"))
	          parichayResponse.setStatus((String)parichayMap.get("status")); 
	        if (parichayMap.containsKey("user_id"))
	          parichayResponse.setUserID((String)parichayMap.get("user_id")); 
	        if (parichayMap.containsKey("sessionId"))
	          parichayResponse.setSessionID((String)parichayMap.get("sessionId")); 
	        if (parichayMap.containsKey("localTokenId"))
	          parichayResponse.setLocalTokenID((String)parichayMap.get("localTokenId")); 
	        if (parichayMap.containsKey("browserId"))
	          parichayResponse.setBrowserID((String)parichayMap.get("browserId")); 
	        if (parichayMap.containsKey("ip"))
	          parichayResponse.setIpAddr((String)parichayMap.get("ip")); 
	        if (parichayMap.containsKey("ua"))
	          parichayResponse.setUserAgent((String)parichayMap.get("ua")); 
	        if (parichayMap.containsKey("userName"))
	          parichayResponse.setUserName((String)parichayMap.get("userName")); 
	        if (parichayMap.containsKey("expiresAt") && 
	          parichayMap.get("expiresAt") != null && !parichayMap.get("expiresAt").toString().contains("No")) {
	        	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	          Date date = dateFormat.parse((String)parichayMap.get("expiresAt"));
	          parichayResponse.setExpiryDate(date);
	        } 
	        if (parichayMap.containsKey("subservice"))
	          parichayResponse.setSubService((String)parichayMap.get("subservice")); 
	        return parichayResponse;
	      } 
	    } catch (Exception e) {
	    	e.printStackTrace();
	      throw new RuntimeException(e.getMessage());
	    } 
	    return null;
	}

	private static Map<String, Object> convertJSONToMap(String jsonString) {
		if (Util.isNullOrEmpty(jsonString))
		      return null; 
		    ObjectMapper mapper = new ObjectMapper();
		    Map<String, Object> map = null;
		    try {
		      map = (Map<String, Object>)mapper.readValue(jsonString, Map.class);
		    } catch (JsonParseException jsonParseException) {
		      logger.error("JAN PARICHAY JSON PARSE EXCEPTION", (Throwable)jsonParseException);
		    } catch (JsonMappingException jsonMappingException) {
		      logger.error("JAN PARICHAY JSON MAPPING EXCEPTION", (Throwable)jsonMappingException);
		    } catch (IOException ioException) {
		      logger.error("JAN PARICHAY JSON IO EXCEPTION", ioException);
		    } 
		    return map;
	}

	public static String getURLResponse(String callURL) {
		 if (Util.isNullOrEmpty(callURL))
		      throw new RuntimeException("EMPTY SSO URL"); 
		    URL url = null;
		    HttpURLConnection connection = null;
		    try {
		      url = new URL(callURL);
		      connection = (HttpURLConnection)url.openConnection();
		      connection.setRequestMethod("GET");
		      connection.connect();
		      String serviceContent = getStringFromInputStream(connection.getInputStream());
		      if(serviceContent.contains(""))
		      {
		    	  serviceContent = serviceContent.replaceAll("^\"|\"$", "");
		      }
		      return serviceContent;
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      if (connection != null)
		        connection.disconnect(); 
		    } 
		    return null;
	}

	private static String getStringFromInputStream(InputStream inputStream) {
		try {
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder(); 
			String line;
			try { 
				br = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				} 
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} 
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JanParichayTokenValidResponse parseTokenValidResponse(String validTokenResponse) {
		 if (Util.isNullOrEmpty(validTokenResponse))
		      return null; 
		    try {
		      Map<String, Object> parichayTokenValidMap = convertJSONToMap(validTokenResponse);
		      if (parichayTokenValidMap != null && parichayTokenValidMap.size() > 0) {
		    	  JanParichayTokenValidResponse janParichayTokenValidResponse = new JanParichayTokenValidResponse();
		        if (parichayTokenValidMap.containsKey("tokenValid") && 
		          "true".equals(parichayTokenValidMap.get("tokenValid")))
		        	janParichayTokenValidResponse.setTokenValid(true); 
		        if (parichayTokenValidMap.containsKey("status"))
		        	janParichayTokenValidResponse.setStatus((String)parichayTokenValidMap.get("status")); 
		        return janParichayTokenValidResponse;
		      } 
		    } catch (Exception e) {
		      throw new RuntimeException(e.getMessage());
		    } 
		    return null;
	}

	public static String createTokenValidURL(String localTokenID, String userName, String browserID, String sessionID) {
		 String serviceName = AppEnvConstant.JAN_PARICHAY_SERVICE_NAME;
		    if (Util.isNullOrEmpty(localTokenID) || 
		      Util.isNullOrEmpty(userName) || 
		      Util.isNullOrEmpty(serviceName) || 
		      Util.isNullOrEmpty(browserID))
		      throw new RuntimeException("JAN PARAMETER EMPTY"); 
		    StringBuilder ssoTokenValidURLBuilder = new StringBuilder(AppEnvConstant.JAN_PARICHAY_TOKEN_VALID);
		    ssoTokenValidURLBuilder.append("?");
		    ssoTokenValidURLBuilder.append("localTokenId=" + localTokenID);
		    ssoTokenValidURLBuilder.append("&userName=" + userName);
		    ssoTokenValidURLBuilder.append("&service=" + serviceName);
		    ssoTokenValidURLBuilder.append("&browserId=" + browserID);
		    ssoTokenValidURLBuilder.append("&sessionId=" + sessionID);
		    return ssoTokenValidURLBuilder.toString();
	}

	public static String hmacFunc_logout(long timeStamp, String SERVICE, String PARICHAY_URL, String clientToken,
			String sessionId) {
		try {
			String hmacString = "JanParichay" + timeStamp + PARICHAY_URL + "/v1/salt/api/client/logout" + clientToken+SERVICE+sessionId;
			System.out.println("hmacString :- " + hmacString);
			String requestBody = "{\"HmacString\":\"" + hmacString + "\"}";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(AppEnvConstant.JAN_PARICHAY_HMAC_URL,
					requestEntity, String.class);
			String str = response.getBody();
			System.out.println(str);
			Object obj = new JSONObject(str);
			JSONObject json = (JSONObject) obj;
			String encrptReturnReponse = ((JSONObject) json.get("data")).get("signature").toString();
			System.out.println("hmacReturnReponse :- " + encrptReturnReponse);
			return encrptReturnReponse;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
  
}