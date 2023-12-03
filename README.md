# crud-with-entitymanager-student

# Java class details 👈
* This is a regular CRUD project which uses a Student.java object having fields - a unique ID, firstname, lastname, emailid.
* It has an interface StudentDAO.java and it is implemented by StudentDAOImpl.java.
* StudentDAOImpl.java uses EntityManager object to perform CRUD operations.
* To modify the way of implementation of a few operations, we use JPQL via EntityManager to query desired data directly from our Spring Boot app.
* In the application class, we use @Bean to access CommandLineRunner interface. This is invoked once after the application class is loaded.

# Database details 👈
* We use the MySql database for this project.
* We configured JPA/Hibernate to 'update' so that the table gets updated for every application run.
* Database name is student_tracker and table name is student.
* The name of the primary key field is id.
