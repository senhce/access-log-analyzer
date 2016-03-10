package org.web.acesslog.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.web.acesslog.Access;



/**
 * @author MT
 * 
 */
public class ApacheParserImpl implements IParser {

    /* %a - Remote IP address
    * %A - Local IP address
    * %b - Bytes sent, excluding HTTP headers, or '-' if zero
    * %h - Remote host name (or IP address if resolveHosts is false)
    * %H - Request protocol
    * %l - Remote logical username from identd (always returns '-')
    * %m - Request method (GET, POST, etc.)
    * %p - Local port on which this request was received
    * %q - Query string (prepended with a '?' if it exists)
    * %r - First line of the request (method and request URI)
    * %s - HTTP status code of the response
    * %S - User session ID
    * %t - Date and time, in Common Log Format
    * %u - Remote user that was authenticated (if any), else '-'
    * %U - Requested URL path
    * %v - Local server name
    * %D - Time taken to process the request, in millis
    * %T - Time taken to process the request, in seconds
    * %I - current request thread name (can compare later with stacktraces)
	*/
	private List<String> formatLst = new ArrayList<String>();
	
	private String format = null;

	private String delimiter = " ";
	
	private final static String NONE = "NONE";

	/**
	 * Tokenize the given format with the delimiter provided.
	 * 
	 * @param format
	 */
	public ApacheParserImpl() {
	}
	
	/* )
	 * @see org.web.acesslog.parser.IParser#setFormat(java.lang.String)
	 */
	public void setFormat(String format) {
		this.format = format;
		populateFormatLst();
	}
	
	/**
	 * 
	 */
	private void populateFormatLst(){
		System.out.println("### Formatter Impl : "
				+ this.getClass().getCanonicalName() );
		System.out.println("### Format : " + format );
		StringTokenizer tokenizer = new StringTokenizer(format, delimiter);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			// System.out.println(token);
			this.formatLst.add(token);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acess.log.parser.Formatter#parse(java.lang.String)
	 */
	@Override
	public Access parse(String line,String includeExtn) {
		StringTokenizer tokenizer = new StringTokenizer(line, delimiter);
		Iterator<String> formatItr = formatLst.iterator();
		Access access = new Access();
		boolean isIncludeExtn = false;
		//int i = 0;
		while (formatItr.hasNext()) {
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				//System.out.println(token);
				String format = formatItr.next();
				if ("%a".equals(format)) {
					access.setRemoteIpAddr(token);
				}else if ("%A".equals(format)) {
					access.setLocalIpAddr(token);
				}else if ("%b".equals(format)) {
					access.setBytesSent(token);
				}else if ("%h".equals(format)) {
					access.setRemoteHostName(token);
				}else if ("%H".equals(format)) {
					access.setRequestProtocol(token);
				}else if ("%l".equals(format)) {
					access.setRemoteLogicalUserName(token);
				}else if ("%m".equals(format)) {
					access.setRequestMethod(token);
				}else if ("%p".equals(format)) {
					access.setLocalPort(token);
				}else if ("%q".equals(format)) {
					access.setQueryString(token);
				}else if ("%r".equals(format)){
					//Specific handling for url string.
					access.setRequestMethod(token);
					String url = tokenizer.nextToken();
					if(url.contains("?")){
						url = url.substring(0, url.indexOf("?"));
						access.setFirstLineOfRequest(url);
					}else{
						access.setFirstLineOfRequest(url);	
					}
					if(includeExtn==null || NONE.equalsIgnoreCase(includeExtn)){
						//Do nothing.
					}else{
						StringTokenizer includeExtnTokenizer = new StringTokenizer(includeExtn,",");
						while (includeExtnTokenizer.hasMoreTokens()) {
							String extension = includeExtnTokenizer.nextToken();
							if(url.contains(extension)){
								isIncludeExtn = true;
							}
						}
					}
					access.setRequestProtocol(tokenizer.nextToken());
				}else if ("%s".equals(format)) {
					access.setHttpStatusCode(token);
				}else if ("%S".equals(format)) {
					access.setUserSessionId(token);
				}else if ("%t".equals(format)) {
					//Specific handling for this required since [ is used with space for timezone offset
					access.setDateTime(token + tokenizer.nextToken());
				}else if ("%u".equals(format)) {
					access.setRemoteUser(token);
				}else if ("%U".equals(format)) {
					access.setRequestedUrlPath(token);
				}else if ("%v".equals(format)) {
					access.setLocalServerName(token);
				}else if ("%D".equals(format)) {
					access.setTimeTakeInMilliSec(token);
				}else if ("%T".equals(format)) {
					access.setTimeTakeInSec(token);
				}else if ("%I".equals(format)) {
					access.setCurrentRequestThreadName(token);
				}
			}
		}
		if(isIncludeExtn){
			return access;
		}else{
			return null;
		}
	}

	public String getFormat() {
		return format;
	}


	
}