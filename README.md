This project use JDK 11, Spring Boot 2.7.0 version, for Database used postgresql.
I have created docker-compose file which is run postgresql Database. Also there are sql direction in where I put sql cript for creating Tables. So when we run postgresql Database the tables also will be created.

After running docker which will be run postgresql DB with this parameters
postgresql_USER: postgresql, 
postgresql_PASSWORD: postgresql, 
postgresql_DB: testdb

Then needed tu build and run the project.

For build you can run following command
#mvn package 

and after successfully build you can run the project from target direction by following command

#java -jar ThirdPartyApp.jar

it will run application on 8080 port

then you can test it by following URL with parameters

POST
localhost:8080/register/client/
localhost:8080/register/conversion
