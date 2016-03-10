# access-log-analyzer
"Analyze web access logs" - Calculate throughput and performance of web components

Simple Utility to analyze your web access log to help you identify potential traffic, response times (min/max/avg), most used URL's etc.

Download the web-log-analyzer.jar & web-log-analyzer.sh from downloads link. Execute web-log-analyzer.sh the script with appropriate arguments as detailed out below.

Currently supported web servers - Tomcat, Apache, JBoss

web-log-analyzer.sh:

java -cp ./web-log-analyzer.jar org.web.acesslog.AccessReader "Time Taken: %T %h %l %u %t %r %s %b" org.web.acesslog.parser.ApacheParserImpl org.web.report.TextReport ".jsp,.html,.xhtml,ajax,AjaxAction" localhost_access_log.2011-06-02.log

org.web.acesslog.AccessReader - Java Main class for the module.

"Time Taken: %T %h %l %u %t %r %s %b" - Tomcat access log format, make sure to set this value same as your web server access log pattern.

org.web.acesslog.parser.ApacheParserImpl - Parser logic specific to tomcat, you can extend it or overwrite it as per your need.

org.web.report.TextReport - Type of report generate implementation to use, at the moment only text based reporting is supported.

".jsp,.html,.xhtml,ajax,AjaxAction" - File extensions & keywords on the url that you are interested in analyzing.

localhost_access_log.2011-06-02.log - your access log.
