package com.master.app.pims.helper;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class HttpSessionHelper {

	public static String getClientIPAddress(jakarta.servlet.http.HttpServletRequest request) {
	    String[] headerNames = {
	        "X-Forwarded-For",
	        "Proxy-Client-IP",
	        "WL-Proxy-Client-IP",
	        "HTTP_CLIENT_IP",
	        "HTTP_X_FORWARDED_FOR"
	    };

	    for (String header : headerNames) {
	        String ip = request.getHeader(header);
	        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
	            // Sometimes the header contains multiple IPs, take the first one
	            if (ip.contains(",")) {
	                ip = ip.split(",")[0].trim();
	            }
	            return ip;
	        }
	    }

	    // If none of the headers are present, fallback to remote address
	    return request.getRemoteAddr();
	}


    // Tries to get the MAC address (limited to local server requests, not reliable for clients)
    public static String getMacAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            // Log the exception if needed
        }
        return "UNKNOWN";
    }
}
