# BookStore
 Simple Online Bookstore api project for demonstrating CRUD operations with capabilities provided by Spring Boot framework and embedded H2 database.

# For building and running the application we need:
- [OpenJDK 17](https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_windows-x64_bin.zip)
- [Maven 3](https://maven.apache.org/download.cgi)

# Configuring database and application start-up:
- For a quick solution I utilized an im-memory H2 database.
- H2 requires its own .db file in the system but I couldn't make it generate one if there aren't any. So I uploaded the [storeDb](https://github.com/edzorhan/BookStore/blob/master/storeDb.mv.db) file to this repository, after downloading it we need to place it under "D:/" path or anywhere we want unless we alter it's read location in `bookstore/src/main/resources/application.properties` "**spring.datasource.url=jdbc:h2:file:D:\\storeDb**"
- After we are done with .db file, we can access the H2 console and query the database from **http://localhost:8096/h2-console/** after we run the application. (Default username is "sa" and password is "password".)
- On start-up JPA generates tables in H2 database automatically, also I created a [data.sql](bookstore/src/main/resources/data.sql) file to populate main tables(Members and Books) on application start.
- For calling and documenting APIs I implemented Swagger UI interface. We can access it from http://localhost:8096/swagger-ui/index.html#/
  
# About services:
- Main services for my Members and Books tables includes capabilities to :
  - Get by ID
  - Get all rows
  - Get all with pagination and sorting utilities
  - Insert new row
  - Update existing row
  - Delete by ID
- Third table on the project is Orders table, which used by OrdersService providing capabilities of :
  - Inserting to table as demonstrating placing an order. According to request parameters service method checks for available stock on according book then calculates price by requested stock count. After then inserts a record to Orders table.
  - Get all service to return all orders.
- All of the services above documented on **SwaggerUI** we can either call them from Swagger or we can copy request bodies and send from **Postman**.

# AOP functionalities :
- To demonstrate aspect oriented capabilities of Spring Boot framework I implemented:
  - **LogFilter** and a **LogAspect** for logging incoming HTTP requests and responses to terminal console. Example:   
    ```
    Logging request : GET/api/members/get/1
    method name :memberInfo
    Parameter : 1
    Result : {"responseHeader":{"success":true,"message":"Success","code":0},"memberInfo":{"memberId":1,"name":"Ediz","surname":"Orhan","address":"kagithane"}}
    Logging response : GETapplication/json
    ```
 - **Controller Advice** for returning custom messages on @Valid anotation violations(@Notnull, @Notblank,@Positive...)
# Unit testing :
- To demonstrate unit testing needs I utilized Mockito and Junit frameworks to mock data and write unit tests.
 
 
  
