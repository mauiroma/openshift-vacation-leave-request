**Spring Boot and RHOAR - Leave Vacation Sample App**

A Leave Vacation from employees sample app developed with Spring Boot and Openshift RHOAR.

It exposes some REST services and persists data on a postgres database.

**Test the application locally from a web browser**

```bash
mvn spring-boot:run
```

These eendpoints are available:
 - list employees with leave list

 ```
 [GET] http://localhost:8080/api/employees?page=<page_number>&pageSize=<page_size>
 ```

**Execute the test locally**

```bash
mvn verify
```

**Create project and app on OpenShift**

```bash
oc login -u developer -p developer

oc new-project leave_vacation --display-name="Leave Vacation App"

oc new-app -e POSTGRESQL_USER=luke -e POSTGRESQL_PASSWORD=secret -e POSTGRESQL_DATABASE=my_data openshift/postgresql-92-centos7 --name=my-database
```

**OpenShift resources**

OCP resources are inside the folder src/main/fabric8:
 - credentials-secret.yml --> create a secret to be used for postgres username and password
 - deployment.yml --> define the deployment configuration
 - route.yml --> create a route for external communication


**Deploy on OpenShift**

Deploy on OCP will use the fabric8 maven plugin and maven profile openshift.

OCP resources inside the folder src/main/fabric8 will be created in OCP project leave_vacation.

```bash
mvn package fabric8:deploy -Popenshift -DskipTests
```

**Health check**

An health check can detect when our application is responding correctly.
Spring Boot provides a feature for this called Actuator, which exposes health data under the path /health.
Just add it to your pom.xml

```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
