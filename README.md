# Technical Assessment 
 
## Task 

We would like you to help us build a service around exposing and correcting power data.This data is then further used on algorithms to provide value to our customers.The data we receive can potentially contain errors (e.g. malfunctioning sensors, errors in monitoring systems), 
hence we’d like the ability to manually overwrite certain datapoints in the process.  

Please take the `powerProduced.json` file as your “in-memory database” to build:

1. A REST endpoint to allow manual correction of the power generated of a given datapoint
2. A REST endpoint exposing all datapoints (with their potentially corrected values)

The challenge should be tackled with either Java or Kotlin, with the help of Spring Boot.  

While this technical challenge is an opportunity for you to showcase your skills, we also want to be respectful of your time and suggest spending no more than 3 hours on this.
When implementing, make sure to follow known best practices around architecture, testability, and documentation.  

The result should be sent as a Git bundle (`git bundle create challenge.bundle --all`).

For any further questions please feel free to reach out at any time.


Technologies Used :
-----------------------

Java11

Spring Boot Core

Spring Boot Test 

Spring Boot Actuator

Docker

OpenApi Swagger

In memory H2 database

Spring DATA JPA

Junit5

Mockito and Spring Boot Test.


Build and Run the Application :

Steps:
-------------------------------

git clone <The http clone url>

git checkout -b <project-name> 

mvn clean install

java Application.java

Open http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/

Execute all the endPoints to see the data.

Unit and Integrations Tests:
----------------------------------------

Test class are written for controller,service and repository classes.

The controller test is the integration test.

If you import your project into any IDE like Eclipse or Intelij just run the respective test classes.

You can access the H2 database by using the below URL to see the entities.
--------------------------------------------------------------------------

http://localhost:8080/h2/

The application is fully containerized with docker.
--------------------------------------------------

For create image ::

docker build -t manoj/challenge-java-baywa-docker .

Run the image ::

docker run -p 8080:8080 manoj/challenge-java-baywa-docker