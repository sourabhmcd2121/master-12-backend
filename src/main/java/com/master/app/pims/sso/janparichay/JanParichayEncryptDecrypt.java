package com.master.app.pims.sso.janparichay;
import java.io.IOException; 
import org.json.JSONObject;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.master.app.pims.config.AppEnvConstant;


public class JanParichayEncryptDecrypt {

    public static String decrypt(byte[] byteCipherText, SecretKey secKey) throws Exception {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        return new String(bytePlainText);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static SecretKey getSecretKeyFromString(String stringKey) {
        byte[] encodedKey = stringKey.getBytes();
        //byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return secretKey;
    }

    public static String decryptResponse(String encrytext, String secretAPIKeyAsString) throws Exception {
        byte[] cipherTextFromHex = hexStringToByteArray(encrytext);
        SecretKey secretKeyConvertedFromStringKey = getSecretKeyFromString(secretAPIKeyAsString);
        String decryptedText = decrypt(cipherTextFromHex, secretKeyConvertedFromStringKey);
        return decryptedText;
    }

//     ---------------------------------------------------------------------------------
    
    
    public static String decryptFunc(String hs_resp) throws IOException, InterruptedException {
		String requestBody = "{\"EncryptedString\":\"" + hs_resp + "\"}";
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(AppEnvConstant.JAN_PARICHAY_DEC_URL, requestEntity, String.class);

		System.out.println("decryptFunc response" + response);
		if (response.getStatusCodeValue() != 200) {
			System.out.println("Call to decryption api failed");
			return "null";
		}
		String str = response.getBody();
		System.out.println(str);	    
		System.out.println("decryption func return resp" + str);
		return str;
	}
    
    
    
    
	public static String encryptFunc() throws IOException, InterruptedException {
		String requestBody = "{\"AESString\":\"Parichay12345\"}";
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(AppEnvConstant.JAN_PARICHAY_ENCR_URL, requestEntity, String.class);
		String str =response.getBody();
	    JSONObject json =new JSONObject(str)  ;
	    String encrptReturnReponse = ((JSONObject) json.get("data")).get("signature").toString();
	    System.out.println("encrptReturnReponse :- " + encrptReturnReponse);
		return encrptReturnReponse;
	}
    
    
    
    
//    ---------------------------------------------------------------------------------
    public static void main(String as[]){
    	final String secretKey = "0KNk7YX40+jzqvq0wYZJK58axONpIn2V";
        String originalString = "F551DEA9F34916CD683C630B0617B89561CD6A52ACC11EC915F213ADA0584D2A9CC06B94E90B35DC314EAFE8CEDF5A68C9B2F9693FCE83BED007A4D6FD9B1DEC4FEB03B36B5232EA7B3C25827A962CFC99DF1534D98A41EC897BBDD2AD08D8154B3D5327EDBDD64B65763251DE18B15AF69ECF980CDCC645F53BC3DEA1A5F54EF4066976B9DA54EEEE86DB76C1125FFA0E45C56EBEBB105786A8E0158DF3EC57034328601B338D2D806C621E3AA6B87C048DAEDA6A5F25AF0AC4D426F7D0AD68803FFDD52C22D76BAE488641F1CC9A3D9E52E862D9CF398AF7E3CC8A3430E7D136D6C5B46189194D7E3681A95CC100CEBEAF026C5063966C5CBA27C88E1A797B8DBF05D58025AD6E26552AA16777F93E78614EFC8C3878CA10E5B760CF68DDB0BC6B2DA1027DCDBE8F1AC8358CD4300C25D9301FC5B57C561853176B7F9AB2EAEBCF65C121DD2291D49E627E59294879869D7EB99BFFA27C70552D29018EFB6F0151AAE9BDEA8408FAB106B09A0947A7958C18C35D9494BE0F2D98428F3172EFD23C690F29D2904363EBDBFCC219AB3F96286E381A345F002139B525E80C6A81838E3DA2844789B586745FB9EEEEB399DF8FB29B3AED0BB91B36361010512D472A7C186C831777BD24D903A10CF95530ADCF0A598DA913222CED354D1E5E2A303B2ED4EE5941C60E89A7159D1B4435E67807E5A132340AFC4F38EA112B56586494E3610CB22B0D58945E2460FBBE23E1C30860A7F8142894235D1CF9C38EF3602F637636591439445C05FB04E6017E7498D01F8AAA6B6157436CFF9FF0325D05010CF55137C8C884E7601B711C86C7EFA4D1649E11017655BC6B37839275047F658FC64DF16AB63AF6E0EDBA2855A2A7AD9548AC7A23DDC982E6C06862BA19C5EF13869BEE2EACAE17B0C5B0A69BE3D2D4C7D7B87A9C4C0CBB04571454BA84D66AE2DDD4B53AA53279BD804EE025D03F648D0EAE3DA1C3C46C0498A874C4AFDC";
	    System.out.println("originalString:- "+originalString);
	    try{
	    String ss = JanParichayEncryptDecrypt.decryptResponse(originalString, secretKey);
	    System.out.println("ss:- "+ss);
	    }catch (Exception e) {
	    	// TODO: handle exception
	    	e.printStackTrace();
		}
    }
	
}