Spring Boot JPA Bulk Excel Import
This is a Spring Boot project that demonstrates an efficient way to import a large amount of data (over 1 million records) from an XLSX file into a PostgreSQL database using JPA/Hibernate.

The project is configured for high performance by using JDBC batching and a streaming Excel reader to ensure low memory consumption.

Features
JPA/Hibernate for database interaction.

High-Performance Batch Processing for bulk data inserts.

Streaming Excel Reader (xlsx-streamer) to handle large .xlsx files without OutOfMemoryError.

PostgreSQL database integration.

Configuration
Before running the application, you need to configure your database connection and file location.

1. Database Connection
Open the src/main/resources/application.properties file and update the following properties to match your PostgreSQL setup:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_postgres_user
spring.datasource.password=your_postgres_password

# Recommended setting to prevent data loss on every restart
spring.jpa.hibernate.ddl-auto=validate
2. Excel File Location
By default, the application looks for a file named customers.xlsx in the root directory of the project.

Place your .xlsx file in the project's root folder (the same level as pom.xml).

Ensure the filename matches the one specified in the run method inside the main application class.

How to Run
Once the configuration is complete, you can run the application. It's set up as a CommandLineRunner, so the import process will start automatically.

Clone the repository.

Open the project in your IDE (like IntelliJ IDEA or VS Code).

Run the main application class (SpringJpaApplication.java).

Alternatively, you can run it from the terminal using the Maven wrapper:

Bash

./mvnw spring-boot:run
