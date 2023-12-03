package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//Create
			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			//Read
			readStudent(studentDAO);
			readAllStudents(studentDAO);
			queryForStudentsByLastName(studentDAO);
			//Update
			updateStudent(studentDAO);
			//Delete
			deleteStudent(studentDAO);
			deleteAllStudents(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {

		//create student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		//save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		//display the ID of the saved student
		System.out.println("Saved the student. Generated ID : " + tempStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create 3 student objects
		System.out.println("Creating 3 student objects ...");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		//save the 3 student objects
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student
		System.out.println("creating a student ...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2vode.com");

		//save the student
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved the student. Generated ID : " + tempStudent.getId());

		//retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + tempStudent.getId());
		Student student = studentDAO.findById(tempStudent.getId());

		//display student
		System.out.println("Found the student : " + student);

	}

	private void readAllStudents(StudentDAO studentDAO) {
		//create a list of students
		List<Student> studentList = studentDAO.findAll();

		//sort them by using java 8 approach -> sorted-comparator
//		studentList.stream()
//				.sorted(Comparator.comparing(Student::getLastName).reversed())
//				.forEach(System.out::println);

		//sort them directly by querying
		studentList.stream()
				.forEach(System.out::println);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//create a list of students
		List<Student> studentList = studentDAO.finByLastName("Doe");

		//filter the last name
//		List<Student> list = studentDAO.findAll();
//		list.stream()
//				.filter(i -> i.getLastName().equals("Doe"))
//				.forEach(System.out::println);

		//display the students
		studentList.stream()
				.forEach(System.out::println);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with ID: " + studentId);
		Student theStudent = studentDAO.findById(studentId);

		//change the first name to "Scooby"
		theStudent.setLastName("Scooby");

		//update the student
		studentDAO.update(theStudent);

		//display the updated student
		System.out.println("Updated student: " + theStudent);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Delete student Id: " + studentId);
		System.out.println("Student is: " + studentDAO.findById(studentId));
		studentDAO.delete(studentId);
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numOfRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted rows count: " + numOfRowsDeleted);
	}

}
