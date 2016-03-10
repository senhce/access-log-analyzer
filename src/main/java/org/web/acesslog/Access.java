package org.web.acesslog;

/**
 * 
 * Entity class to hold every single row in the access log in a generic way.
 * No support will be provided at the moment, this software is opensource for reuse/modification/distribution.
 * @author Senthil Balakrishnan
 */
public class Access {
	
	
	private String remoteIpAddr;
	
	private String localIpAddr;
	
	private String bytesSent;
	
	private String remoteHostName;
	
	private String requestProtocol;
	
	private String remoteLogicalUserName;
	
	private String requestMethod;
	
	private String localPort;
	
	private String queryString;
	
	private String firstLineOfRequest;
	
	private String httpStatusCode;
	
	private String userSessionId;
	
	private String dateTime;
	
	private String remoteUser;
	
	private String requestedUrlPath;
	
	private String localServerName;
	
	private String timeTakeInMilliSec;
	
	private String timeTakeInSec;
	
	private String currentRequestThreadName;

	public String getRemoteIpAddr() {
		return remoteIpAddr;
	}

	public void setRemoteIpAddr(String remoteIpAddr) {
		this.remoteIpAddr = remoteIpAddr;
	}

	public String getLocalIpAddr() {
		return localIpAddr;
	}

	public void setLocalIpAddr(String localIpAddr) {
		this.localIpAddr = localIpAddr;
	}

	public String getBytesSent() {
		return bytesSent;
	}

	public void setBytesSent(String bytesSent) {
		this.bytesSent = bytesSent;
	}

	public String getRemoteHostName() {
		return remoteHostName;
	}

	public void setRemoteHostName(String remoteHostName) {
		this.remoteHostName = remoteHostName;
	}

	public String getRequestProtocol() {
		return requestProtocol;
	}

	public void setRequestProtocol(String requestProtocol) {
		this.requestProtocol = requestProtocol;
	}

	public String getRemoteLogicalUserName() {
		return remoteLogicalUserName;
	}

	public void setRemoteLogicalUserName(String remoteLogicalUserName) {
		this.remoteLogicalUserName = remoteLogicalUserName;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getLocalPort() {
		return localPort;
	}

	public void setLocalPort(String localPort) {
		this.localPort = localPort;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getFirstLineOfRequest() {
		return firstLineOfRequest;
	}

	public void setFirstLineOfRequest(String firstLineOfRequest) {
		this.firstLineOfRequest = firstLineOfRequest;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public String getRequestedUrlPath() {
		return requestedUrlPath;
	}

	public void setRequestedUrlPath(String requestedUrlPath) {
		this.requestedUrlPath = requestedUrlPath;
	}

	public String getLocalServerName() {
		return localServerName;
	}

	public void setLocalServerName(String localServerName) {
		this.localServerName = localServerName;
	}

	public String getTimeTakeInMilliSec() {
		return timeTakeInMilliSec;
	}

	public void setTimeTakeInMilliSec(String timeTakeInMilliSec) {
		this.timeTakeInMilliSec = timeTakeInMilliSec;
	}

	public String getTimeTakeInSec() {
		return timeTakeInSec;
	}

	public void setTimeTakeInSec(String timeTakeInSec) {
		this.timeTakeInSec = timeTakeInSec;
	}

	public String getCurrentRequestThreadName() {
		return currentRequestThreadName;
	}

	public void setCurrentRequestThreadName(String currentRequestThreadName) {
		this.currentRequestThreadName = currentRequestThreadName;
	}

	@Override
	public String toString() {
		return "AccessLog [remoteIpAddr=" + remoteIpAddr + ", localIpAddr="
				+ localIpAddr + ", bytesSent=" + bytesSent
				+ ", remoteHostName=" + remoteHostName + ", requestProtocol="
				+ requestProtocol + ", remoteLogicalUserName="
				+ remoteLogicalUserName + ", requestMethod=" + requestMethod
				+ ", localPort=" + localPort + ", queryString=" + queryString
				+ ", firstLineOfRequest=" + firstLineOfRequest
				+ ", httpStatusCode=" + httpStatusCode + ", userSessionId="
				+ userSessionId + ", dateTime=" + dateTime + ", remoteUser="
				+ remoteUser + ", requestedUrlPath=" + requestedUrlPath
				+ ", localServerName=" + localServerName
				+ ", timeTakeInMilliSec=" + timeTakeInMilliSec
				+ ", timeTakeInSec=" + timeTakeInSec
				+ ", currentRequestThreadName=" + currentRequestThreadName
				+ "]";
	}

	
}
