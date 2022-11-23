package com.williams.studentrecord.repositories;

import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentByEmail(String email);

    Integer countAllByNameOfClass(NameOfClass nameOfClass);

//    List<StudentResult> findAllByNameOfClass(NameOfClass nameOfClass);

    @Query("SELECT s FROM Student s WHERE ((:email IS NULL) OR (:email IS NOT NULL AND s.email = :email))" +
            " AND ((:studentName IS NULL) OR (:studentName IS NOT NULL AND s.studentName = :studentName)) order by s.studentId desc")
    List<Student> findStudentResults(@Param("email") String email,
                                     @Param("studentName") String studentName);

}
