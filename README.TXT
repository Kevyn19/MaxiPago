---------- Project documentation -------------

Architecture:

Java version 8
Spring Boot
Tomcat
Mysql JDBC

Class main that gives start in application: ApiwebApplication

Root url: localhost:8080/Cities

Services Exposed

localhost:8080/Cities - POST 
localhost:8080/Cities - PUT
localhost:8080/Cities/xml/{metric} - GET
localhost:8080/Cities/json/{metric} - GET


Build - mvn clean install