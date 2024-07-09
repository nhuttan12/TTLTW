package log;

import jakarta.servlet.http.HttpServletRequest;

public class IP {
	public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getRemoteAddr();
        }
        return ip;
    }
}
