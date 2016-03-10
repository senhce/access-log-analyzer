package org.web.acesslog.parser;

import org.web.acesslog.Access;


/**
 * Generic Interface to parse any web server access log.
 * No support will be provided at the moment, this software is open for reuse/modification/distribution.
 * @author Senthil Balakrishnan
 *
 */
public interface IParser  {

	/**
	 * IMplement parse logic for a specific web server
	 * @param line
	 * @param includeExtn
	 * @return
	 */
	public Access parse(String line, String includeExtn);
	
	/**
	 * Set the format to be used as per user input.
	 * @param format
	 */
	public void setFormat(String format);

	
}
