# jCMS

A Content Management System developed with Java, Spring (Boot, Data, Hibernate, MVC), React and Redis.
The frontend side uses Babel, React and Webpack.
The build tool and web server include Maven and Tomcat. The data and contents are stored using mySQL.

## Setting up
Make sure that you have Java 8, mySQL 5.x.x and Redis installed.

Create a mySQL database called 'jcms' (you can change the name but make sure to update it in
application.properties under datasource url).

For redis, enter the hostname and port number in application.properties as well.

Then enter your credentials (for both mySQL and Redis) into the config.properties 
file, which jCMS will use to write into the DB. Maven will build and deploy all 
the files, including installing npm and running webpack to bundle all the modules.

Make sure **mySQL and Redis are running.**

## Building and deploying
```bash
./start.sh

# For more logging purposes
./start.sh -X
```

## Development mode
To test mySQL queries, you can source the file directly:
```
mysql> source ~/Documents/jCMS/src/main/resources/data/mysqldata.sql
```

For hot reloading Spring boot, the spring boot dev tools plugin can be used.
Run the main method directly in the IDE without using maven.

For the frontend side, run ```npm start```
