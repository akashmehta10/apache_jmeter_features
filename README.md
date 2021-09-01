# Apache Jmeter Features
Apache JMeter is an Apache project that can be used as a unit-test tool for JDBC database connections, FTP, LDAP, web services, JMS, HTTP, generic TCP connections and OS-native processes. One can also configure JMeter as a monitor, although this is typically used as a basic monitoring solution rather than advanced monitoring. It can be used for some functional testing as well.

Full Description: 

In this exercise, I have tried exploring many great features offered by Jmeter and provided code for the same. The features explored are:

- Configuring a Python based web server and hitting GET/POST APIs utilizing Jmeter functions: RequestHeaders, HTTPRequest, ResponseAssertions, JSONParsing, Bean Shell pre-processor/post-processor, etc.
- Firing SQL Queries to Mysql server and asserting response using Bean Shell post-processor, Response Parsing, etc.
- Writing a sample custom JAVA code to generate random images and exporting that JAR to be utilized by Jmeter for image uploading
- Viewing Results/Graphs from Jmeter runs
- Running Jmeter in non-GUI mode


## Configuration Steps

### Step 1
Download Apache Jmeter from: https://jmeter.apache.org/download_jmeter.cgi

The version used while developing these features is: Apache JMeter 5.4.1. Please ensure you have an existing setup of Java 8+ installed in your system.

### Step 2
- Start Python based webserver available under Sample-Python-Files/python-web-server.py
- Start Image Uploader - Flask based webserver available under Sample-Python-Files/python-file-uploader-flask.py

Please ensure you have Python 2.7+ installed in your system

### Step 3
- Start Jmeter and import Jmeter-Feature-JMX/Jmeter-Features.jmx file
- Change paths/ports/variables in User Defined Variables section
- Run the Plan