# Apache Jmeter Features
Apache JMeter is an Apache project that can be used as a unit-test tool for JDBC database connections, FTP, LDAP, web services, JMS, HTTP, generic TCP connections and OS-native processes. One can also configure JMeter as a monitor, although this is typically used as a basic monitoring solution rather than advanced monitoring. It can be used for some functional testing as well.

Full Description: https://medium.com/@akashmehta10/using-apache-jmeter-to-be-awesome-at-work-underrated-fun-feature-rich-tool-acb048c02507

In this exercise, I have tried exploring many great features offered by Jmeter and provided code for the same.

Setup:
- Setup a Python based HTTP web server to accept GET/POST requests (code sample attached)

- Setup a Python based Flask server to receive images uploaded by end user (code sample attached)

- Setup Mysql server on local/remote machine, instructions: https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/

Jmeter Features Explored:
- Submitting GET based HTTP requests to web server and then asserting response by utilizing: ResponseAssertion and JSONAssertion

- Submitting POST based HTTP requests to web server by utilizing BeanShell PreProcessor to send random IDs in payload and then asserting response by utilizing: ResponseAssertion and JSONAssertion

- Initiating Database connections to fire SQL Queries to Mysql server and then asserting response using Bean Shell post-processor and ResponseAssertion.

- Writing a custom JAVA code to generate random images, utilizing that code to upload images uploading to Python based Flask server and then asserting response by utilizing: ResponseAssertion and JSONAssertion

- Exploring ViewResults, SummaryReport and GraphResults Listeners to monitor run results

- Running Jmeter in non-GUI mode and validating results
Load testing your application


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