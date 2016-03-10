package org.web.report;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Set;

import org.web.acesslog.Stat;

/**
 * Text file implementation of IReport interface.
 * 
 * No support will be provided at the moment, this software is open for reuse/modification/distribution.
 * 
 * @author Senthil Balakrishnan
 */
public class TextReport implements IReport{

	/* 
	 * @see org.web.report.IReport#generate(java.util.Map)
	 */
	@Override
	public void generate(Map<String, Stat> stats) throws Exception {
		System.out.println("### Writing Text report..");
		File file = null;
		FileWriter writer = null;
		try {
			file = new File("accesslog_report.txt");
			System.out.println("### Stats succesfully written to: "+file.getAbsolutePath());
			writer = new FileWriter(file);
			Set<Map.Entry<String, Stat>> entrySet = stats.entrySet();
			for (Map.Entry<String, Stat> entry : entrySet) {
				writer.write(entry.getValue().toString());
			}
		} finally {
			writer.close();
		}
	}
}
