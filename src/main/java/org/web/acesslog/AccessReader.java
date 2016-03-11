package org.web.acesslog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.web.acesslog.parser.ApacheParserImpl;
import org.web.acesslog.parser.IParser;
import org.web.report.IReport;


/**
 * Main/Init for the Utility, we try to support a list of web servers but at the moment only tomcat access log is supported.
 * It's important to use the same access format used in the access log & this Utility to get an accurate output.
 * 
 * No support will be provided at the moment, this software is open for reuse/modification/distribution.
 * 
 * @author Senthil Balakrishnan
 */
public class AccessReader {
	
	/**
	 * Default TestReport
	 */
	public IParser formatter = new ApacheParserImpl();

	/**
	 * 
	 */
	public IReport report = null;
	

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		AccessReader reader = new AccessReader();
		System.out.println("######### Access Log Reader Started #############\n");
		if(args==null || args.length < 5){
			System.out.println("Ex: \"Time Taken: %T %h %l %u %t %r %s %b\" org.web.acesslog.parser.ApacheParserImpl org.web.report.TextReport \".html,.xhtml,.jsp,ajax,service\" C:\\sen\\access.log");
			System.err.println("Error: No sufficient input arguments to execute...");
			System.exit(0);
		}
		//Ex: format = "Time Taken: %T %h %l %u %t %r %s %b"
		String format = args[0];
		if(format==null || format.isEmpty()){
			System.err.println("Error: Log file Format can't be empty, sample value could be \"Time Taken: %T %h %l %u %t %r %s %b\"");
			System.exit(0);
		}
		//Ex: formatterClass = org.web.acesslog.parser.ApacheParserImpl
		String formatterClassStr = args[1];
		if(formatterClassStr==null || formatterClassStr.isEmpty()){
			System.err.println("Error: Formatter Class can't be empty, sample value could be org.web.acesslog.parser.ApacheParserImpl");
			System.exit(0);
		}else {
			// Parser class
			Class formatterClazz;
			try {
				formatterClazz = Class.forName(formatterClassStr);
				IParser parser = (IParser) formatterClazz.newInstance();
				parser.setFormat(format);
				reader.setFormatter(parser);
			} catch (Exception e) {
				System.err.println("Error: Couldn't instanitate formatter class, check the input - "+formatterClassStr);
				System.exit(0);
			}
		}
		String reportClassStr = args[2];
		if(reportClassStr==null || reportClassStr.isEmpty()){
			System.out.println("Error: FileName can't be empty, sample value could be output");
			System.exit(0);
		}else{
			// Report class
			Class reportClazz;
			try {
				reportClazz = Class.forName(reportClassStr);
				reader.setReport((IReport) reportClazz.newInstance());
			} catch (Exception e) {
				System.err.println("Error: Couldn't instanitate report class, check the input - "+reportClassStr);
				System.exit(0);
			}
		}
		String includeExtn = args[3];
		if(includeExtn==null || includeExtn.isEmpty()){
			System.out.println("Error: IncludeExtn can't be empty, Ex: \".html,.xhtml,.jsp,.js,ajax,service\"");
			System.exit(0);
		}
		String fileName = args[4];
		if(fileName==null || fileName.isEmpty()){
			System.err.println("Error: Please provide the access log to be parsed");
			System.exit(0);
		}
		// Read/Parser logic
		File file = new File(fileName);
		Reader readerIO = new FileReader(fileName);
		List<Access> accessList = reader.read(readerIO,includeExtn);
		// Stats computation Logic
		Map<String, Stat> stats = calcStats(accessList);
		// Generate Report
		reader.getReport().generate(stats);
		
		if(readerIO != null)
			readerIO.close();
		System.out.println("\n######### Access Log Reader Exited #############");
	}

	/**
	 * @param accessList
	 */
	private static Map<String, Stat> calcStats(List<Access> accessList) {
		System.out.println("### Calculating Stats...");
		Map<String, Stat> stats = new HashMap<String, Stat>();
		for (Access access : accessList) {
			// URL is mandatory, this the only way to uniqiely identify a
			// request.
			String key = access.getFirstLineOfRequest();
			Stat stat;
			//System.out.println(access);
			if (stats.containsKey(key)) {
				stat = stats.get(key);
			} else {
				stat = new Stat(key);
			}
			stat.addStat(Double.parseDouble(access.getTimeTakeInSec()),
					access.getDateTime());
			stats.put(key, stat);
			//System.out.println("Stat:" + stat);
		}
		return stats;
	}

	/**
	 * Read the access log & parse each row.
	 * 
	 * @param fileName
	 * @return
	 */
	public List<Access> read(Reader reader, String ignoreExt) {
		List<Access> accessList = new ArrayList<Access>();
		BufferedReader buffReader = null;
		try {
			buffReader = new BufferedReader(reader);
			String line = buffReader.readLine();
			while (line != null) {
				Access access = formatter.parse(line, ignoreExt);
				if(access!=null)
				accessList.add(access);
				line = buffReader.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (buffReader != null) {
				try {
					buffReader.close();
				} catch (IOException e) {
					// suppressed
				}
			}
		}
		return accessList;
	}

	public IParser getFormatter() {
		return formatter;
	}

	public void setFormatter(IParser formatter) {
		this.formatter = formatter;
	}

	public IReport getReport() {
		return report;
	}

	public  void setReport(IReport report) {
		this.report = report;
	}
	
}
