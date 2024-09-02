package com.master.app.pims.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public final class Util {

	private Util() {
	}

//	public static String getValidFileName(String fileName) {
//		fileName = fileName.replace("./", "");
//		String pathName = FilenameUtils.getFullPath(fileName);
//		fileName = FilenameUtils.getName(fileName.replace("./", ""));
//		if (fileName != null && fileName.length() > 0) {
//			int decimalCount = StringUtils.countMatches(fileName, ".");
//			if (decimalCount <= 1) {
//				String REGEX = "[A-Z|a-z|0-9|_|\\-|\\.]*";
//				boolean checkStatus = fileName.matches(REGEX);
//				if (checkStatus)
//					fileName = pathName + fileName;
//				return fileName;
//
//			}
//		} else {
//			return fileName;
//		}
//		return "";
//
//	}

	/*
	 * #############################################################################
	 * ###
	 */
	/* ##### FUNCTIONS TO CHECK NULL OR ZERO ##### */
	public static boolean isNullOrZero(BigDecimal id) {
		BigDecimal zero = new BigDecimal(0);
		if (id == null || id.compareTo(zero) == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrZero(Long id) {
		Long zero = 0L;
		if (id == null || zero.equals(id)) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrZero(BigInteger id) {
		BigInteger zero = new BigInteger("0");
		if (id == null || zero.equals(id)) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrZero(Double id) {
		Double zero = 0D;
		if (id == null || zero.equals(id)) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrZero(Float id) {
		Float zero = 0F;
		if (id == null || zero.equals(id)) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrZeroOrNegative(Long id) {
		return isNullOrZero(id) || id.intValue() < 0;
	}

	public static boolean isNullOrZero(Integer id) {
		Integer zero = 0;
		if (id == null || zero.equals(id)) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(String string) {
		if (string == null || string.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(Character character) {
		if (character == null || character.equals(' ')) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(Object obj) {
		return (obj != null);
	}
	/*
	 * #############################################################################
	 * ###
	 */

	/*
	 * #############################################################################
	 * ###
	 */
	/* ##### FUNCTIONS TO GET STRING LIST FROM DELIMITED STRING ##### */
	public static List<String> getListFromDelimitedString(String delimitedStr, String delimiter) {
		if (delimitedStr == null || delimiter == null) {
			return Collections.emptyList();
		} else {
			String[] dataArray = delimitedStr.split(delimiter);
			List<String> dataList = Arrays.asList(dataArray);
			return dataList;
		}
	}
	/*
	 * #############################################################################
	 * ###
	 */

	/*
	 * #############################################################################
	 * ###
	 */
	/* ##### FUNCTIONS TO GET STRING LIST FROM DELIMITED STRING ##### */
	public static Set<String> getSetFromDelimitedString(String delimitedStr, String delimiter) {
		if (delimitedStr == null || delimiter == null) {
			return Collections.emptySet();
		} else {
			String[] dataArray = delimitedStr.split(delimiter);
			Set<String> dataList = new HashSet<String>();
			if (dataArray != null) {
				for (String data : dataArray) {
					dataList.add(data);
				}
			}
			return dataList;
		}
	}
	/*
	 * #############################################################################
	 * ###
	 */

	/*
	 * #############################################################################
	 * ###
	 */
	/* ##### FUNCTIONS TO GET ARRAY FROM THE STRING WITH DELIMITER ##### */
	public static String[] convertDelimitStringToArray(String string, String delimiter) {
		if (string != null && delimiter != null) {
			String[] strings = string.split(delimiter);
			return strings;
		}
		return new String[] {};
	}
	/*
	 * #############################################################################
	 * ###
	 */
	/* ##### FUNCTIONS TO GET drop down FROM THE STRING WITH DELIMITER ##### */
//	public static List<SelectOption> getDropDownFromDelimitedString(String delimitedStr, String delimiter) {
//		if(delimitedStr != null && delimiter != null)
//		{
//			String[] dataArray = delimitedStr.split(delimiter);
//	    	List<SelectOption> list = new ArrayList<SelectOption>();
//	    	for(String string : dataArray)
//	    	{
//	    		list.add(new SelectOption(string, string));
//	    	}
//			return list;
//		}
//		else
//		{
//			return Collections.emptyList();
//		}
//	}

	public static int calculateYearDifference(Date arg1, Date arg2) {

		if (isNotNull(arg1) && isNotNull(arg2)) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(arg1);
			int year1 = cal1.get(Calendar.YEAR);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(arg2);
			int year2 = cal2.get(Calendar.YEAR);

			return year1 - year2;
		}
		return 0;
	}

	public static String getIPAddress() {
		try {
			return Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "unknown";
		}
	}

	public static boolean isValidEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);

		if (pattern.matcher(email).matches()) {
			return true;
		} else {
			return false;
		}
	}
//	public static boolean isValidJson(String jsonStr) {
//	//String json=StringEscapeUtils.unescapeJava(jsonStr);
//	        try {
//	        	JsonParser jsonParser = new JsonParser();
//	        	jsonParser.parse(jsonStr);
//	        	
//	        	//JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonStr);
//	        		
//	        	return true;
//	        } catch (Exception ex1) {
//					try {
//						 PrintWriter writer;
//						writer = new PrintWriter("D:/chandan.txt", "UTF-8");
//						 writer.println(jsonStr); writer.close();
//					} catch (FileNotFoundException | UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//				 
//	            return false;
//	        }
//   		
//   	}

	public static String removeSqlInjection(String str) {

		if (str != null && str.length() > 0) {
			str = str.replace("--", "");
			str = str.replace("\\", "\\\\");
			str = str.replace("'", "\\'");
			str = str.replace("\0", "\\0");
			str = str.replace("\n", "\\n");
			str = str.replace("\r", "\\r");
			str = str.replace("\"", "\\\"");
			str = str.replace("\\x1a", "\\Z");

		}
		return str;
	}

	public static boolean validateLongValue(Long longValue) {
		if (longValue != null) {
			if (!(longValue instanceof Long))
				return false;
			if (longValue > Long.MAX_VALUE)
				return false;
			if (longValue < Long.MIN_VALUE)
				return false;
		}
		return true;
	}

//public static String validateStringValue(String stringValue){
//	if(stringValue!=null){
//		Jsoup.clean(stringValue, Whitelist.none());
//		stringValue.replaceAll("[\']", "");
//	}
//	return stringValue;
//}
	public static boolean isValidBoolean(Boolean booleanValue) {
		if (booleanValue != null) {
			if (!(booleanValue != true || booleanValue != false))
				return false;
			else
				return true;
		}
		return true;
	}

	public static boolean validateIntegerValue(Integer integerValue) {
		if (integerValue != null) {
			if (!(integerValue instanceof Integer))
				return false;
			if (integerValue > Integer.MAX_VALUE)
				return false;
			if (integerValue < Integer.MIN_VALUE)
				return false;
		}
		return true;
	}

}
