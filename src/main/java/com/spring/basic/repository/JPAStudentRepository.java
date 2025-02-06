package com.spring.basic.repository;

import com.spring.basic.domain.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class JPAStudentRepository {

    // EntityManager를 주입 받아 사용한다.
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 학생 저장
     */
    @Transactional
    public Student save(Student student) {
        //영속성 컨텍스트
        entityManager.persist(student);
        return student;
    }

}
