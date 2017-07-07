# jCMS

A Content Management System developed with React, Java Spring Boot, MVC, Data and Hibernate.
The frontend side uses Babel, React and Webpack.
The build tool and web server include Maven and Tomcat. The data and contents are stored using mySQL.

## Setting up
Make sure that you have mySQL 5.x.x installed.

Create a mySQL database called 'jcms' (you can change the name but make sure to update it in
application.properities under datasource url).

Then enter your credentials into the config.properties file, which jCMS will use to write into the DB.
Maven will build and deploy all the files, including installing npm and running webpack to bundle all the modules.

## Building and deploying
```bash
mvn clean spring-boot:run

# For more logging purposes
mvn clean spring-boot:run -X
```
