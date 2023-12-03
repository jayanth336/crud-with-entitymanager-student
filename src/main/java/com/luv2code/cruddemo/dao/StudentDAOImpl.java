package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //define field for entity manager
    private final EntityManager entityManager;

    //inject entity manager using constructor based dependency injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method - Create
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    //implement findById, findAll and findByLastName methods - Read
    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);

        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> finByLastName(String lastName) {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where lastName=:theData", Student.class);

        //set parameter
        theQuery.setParameter("theData", lastName);

        //return query results
        return theQuery.getResultList();
    }

    //implement update method - Update
    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    //implement delete and deleteAll methods - Delete
    @Override
    @Transactional
    public void delete(Integer id) {
        //retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        //delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int noOfRowsDeleted = entityManager.createQuery("delete from Student").executeUpdate();

        return noOfRowsDeleted;
    }
}
