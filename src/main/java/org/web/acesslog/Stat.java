package org.web.acesslog;

/**
 * Value Object to hold the stats content between calculation & writing stats to file.
 * No support will be provided at the moment, this software is open for reuse/modification/distribution.
 * @author Senthil Balakrishnan
 **/
public class Stat {
	
	private String url;
	
	private int numOfTrans = 0;
	
	private double min = 0;
	
	private String minDate = null;
	
	private double max = 0;
	
	private String maxDate = null;
	
	private double totalTimeTaken = 0;
	
	private String startDate = null;
	
	private String endDate = null;
	
	/**
	 * @param url
	 */
	public Stat(String url){
		this.url = url;
	}
	
	/**
	 * Add stats info & also calculate min/max & avg time take.
	 * @param response
	 * @param startDate
	 */
	public synchronized void addStat (double response, String startDate){
		this.numOfTrans++;
		if(this.startDate==null){
			this.startDate = startDate;
		}
		this.endDate = startDate;
		if((response <= min) || numOfTrans==1){
			min = response;
			minDate = startDate;
		}
		if(response >= max || numOfTrans==1){
			max = response;
			maxDate = startDate;
		}
		totalTimeTaken = totalTimeTaken + response;
	}

	public int getNumOfTrans() {
		return numOfTrans;
	}

	public void setNumOfTrans(int numOfTrans) {
		this.numOfTrans = numOfTrans;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getTotalTimeTaken() {
		return totalTimeTaken;
	}

	public void setTotalTimeTaken(double totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}
	
	public double getAvg(){
		if(numOfTrans > 0){
			return this.totalTimeTaken / numOfTrans;
		}else {
			return 0;
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("###############################################################################");
		toString.append("\n# " + url );
		toString.append("\n###############################################################################\n");
		toString.append("First Transaction       : "+startDate+"\n");
		toString.append("Last Transaction        : "+endDate+"\n");
		toString.append("Number of Transaction   : " + numOfTrans+"\n");
		toString.append("Total Timetaken(sec.ms) : "+totalTimeTaken+"\n");
		toString.append("Minimum time(sec.ms)    : "+min+" "+minDate+"\n");
		toString.append("Maximum time(sec.ms)    : "+max+" "+maxDate+"\n");
		toString.append("Average time(sec.ms)    : "+getAvg()+"\n");
		toString.append("###############################################################################\n\n");
		return toString.toString();
	}
	
}
