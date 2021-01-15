# Service Dev/Prod toggle

A minimal demo project to demonstrate how to switch between Prod/Dev service configurations, using maven profiles.

## About

In real word systems, service configurations depending on the deployment environment.
The most common cases are, a *Development (Dev)* and a *Production (Prod)* environment.
Maven profiles can be used to toggle specific build configurations and change service behaviour based on associated ```.properties``` files.

 > Note: This project is based on configurations described at [medium.com](https://medium.com/@derrya/maven-profile-spring-boot-properties-a34f2b2bb386). This project showcases no additional features and only puts things into a build-and-testable context.

## Context

The context for this demo project is a slightly modified version of the TimeService, a minimal RESTful service with just only a single resource: ```/time```.  
A ```GET``` request on that resource is answered with a time-string.

We assume that the service must run on different ports - depending on its current environment:

 * **Dev**: The service runs on port 8081 and returns a dummy string: "Teatime".
 * **Prod**: The service runs on port 8082 and returns the actual time.

## Configurations

This sections lists all relevant configurations added to the original TimeService, that allow for a changeable **Dev**/**Prod** behaviour.

### Spring

There are two extra spring boot configurations:

 * ```src/main/resources/application-dev.properties```
 * ```src/main/resources/application-prod.properties```
 
These files only specify the server port and time-string characteristics.
Common configurations such as the response preamble remain in the common:  
```src/main/resources/application.properties```

### Maven

An extra *profile* block in the ```pom.xml``` declares two build profiles and assigns a specific ```.properties``` each:

```xml
    <profiles>
	<profile>
	    <id>dev</id>
	    <activation>
		<activeByDefault>true</activeByDefault>
	    </activation>
	    <properties>
		<activatedProperties>dev</activatedProperties>
	    </properties>
	</profile>
	<profile>
	    <id>prod</id>
	    <properties>
		<activatedProperties>prod</activatedProperties>
	    </properties>
	</profile>
    </profiles>
```

Above configuration sets **Dev** as default. If no further profile information is provided at startup, maven will apply the **Dev** configuration.

## Usage

Compile the resources. There is no need to recompile for later profile alternations.

```bash
mvn clean package
```

Use the ```-P``` switch to specify the profile to apply:

 * **Dev** profile:  
 ("```-P dev```" is redundant for the default configuration)  
```bash
mvn spring-boot:run
curl -X GET http://127.0.0.1:8081/time
```

 * **Prod** profile:  
```bash
mvn spring-boot:run -P prod
curl -X GET http://127.0.0.1:8082/time
```

## Contact / Pull Requests

Contact information for bug reports and pull requests:

 * Author: Maximilian Schiedermeier ![email](markdown/email.png)
 * Github: [Kartoffelquadrat](https://github.com/kartoffelquadrat)
 * Webpage: [McGill University, School of Computer Science](https://www.cs.mcgill.ca/~mschie3)
 * License: [CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/)

