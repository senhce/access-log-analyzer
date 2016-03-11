package org.web.accesslog;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.web.acesslog.Access;
import org.web.acesslog.AccessReader;
import org.web.acesslog.parser.ApacheParserImpl;
import org.web.acesslog.parser.IParser;
import org.web.report.TextReport;

import junit.framework.TestCase;

//Test case is incomplete, needs more scenarios to be covered.
public class AccessReaderTestcase extends TestCase {
	
	public void testTestRead() throws IOException {
		AccessReader accessReader = new AccessReader();
		IParser parser = new ApacheParserImpl();
		String includeExtn = ".jsp,.html,.xhtml,ajax,AjaxAction,service";
		String format = "%T %h %l %u %t %r %s %b";
		parser.setFormat(format);
		accessReader.setFormatter(parser);
		accessReader.setReport(new TextReport());
		
		StringBuilder contentStr = new StringBuilder()
				.append("0.034 10.50.76.140 - - [21/Jul/2011:17:28:02 -0400] POST /service/home/test1 HTTP/1.1 200 2049\n")
				.append("0.032 10.50.76.145 - - [21/Jul/2011:17:28:12 -0400] GET /service/home/test2 HTTP/1.1 400 2049\n")
				.append("1.034 10.50.76.141 - - [21/Jul/2011:17:28:22 -0400] POST /service/home/test1 HTTP/1.1 204 2049\n")
				.append("0.534 10.50.76.143 - - [21/Jul/2011:17:28:12 -0400] DELETE /service/home/test1 HTTP/1.1 200 2049\n")
				.append("0.634 10.50.76.142 - - [21/Jul/2011:17:28:32 -0400] PATCH /service/home/test2 HTTP/1.1 500 2049\n");
		
		Reader reader = new StringReader(contentStr.toString());
		
		// Read/Parser logic
		List<Access> accessList = accessReader.read(reader,includeExtn);
		assertEquals(5, accessList.size());
		assertEquals("/service/home/test1", accessList.get(0).getRequestedUrlPath());
		assertEquals("10.50.76.145", accessList.get(1).getRemoteHostName());
		assertEquals("POST", accessList.get(2).getRequestMethod());
		assertEquals("500", accessList.get(4).getHttpStatusCode());
		System.out.println(" here ! " +accessList.get(0));
		
		if(reader != null)
			reader.close();
	}

}
