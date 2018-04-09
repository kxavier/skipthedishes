# SkipTheDishes Java Challenge
Implementation of the Java Challenge for the SkipTheDishes Recruiting Fair 2018

## Description
The goal of the project was to create the backend implementation for a remote API created by the VanHack team and described [here](http://api-vanhack-event-sp.azurewebsites.net/swagger/)

The following technologies were used:
- Java 8
- JAX-RS
- JPA
- JSON-P
- Maven

This code is not yet a complete implementation! The structure of the project is described next.

## Structure

We have the following packages in the project:
- *com.skipthedishes.challenge* : contains general purpose classes like for example the JAX-RS Configuration class, custom exceptions and JAX-RS exception mappers.
- *com.skipthedishes.challenge.boundary* : Each REST Resource has a corresponding JAX-RS endpoint named after the Resource contained in this package. For example, for the Product resource we have the *ProductResource* class.
- *com.skipthedishes.challenge.entity* : Contains the model and DAO classes.

### API Integration Tests

For each Resource there is a corresponding Integration Test (IT) JUnit class. For example, tests for the Product API are written in the *ProductResourceIT* class.

The test classes were created in the src/test/java package as usual in Maven, and use the same package structure as their corresponding Resource class.

### Running with Docker

There is a docker-compose.yml file to allow the application to be run with docker containers. To run the application type *docker-compose up*.

 ### Runtime Dependencies

To run the application without Docker it is necessary to install and configure an application server and a database server.

The code runs in the Payara 4.1 Application Server and uses a custom deployment descriptor to create a database connection pool in the server. The proprietary deployment descriptor is the glassfish.resources.xml file located in the src/main/webapp/WEB-INF/ folder. To use other Java EE servers it will be necessary to configure their connections pools. 

The database used was a MySQL Server and the JDBC driver was installed in the application server. The creation of the tables is done automatically by the configuration of the JPA *persistence.xml* file, but it is assumed that there is a schema named skip in the database, and that there is a user named skip with password skip created with full access to the schema.


