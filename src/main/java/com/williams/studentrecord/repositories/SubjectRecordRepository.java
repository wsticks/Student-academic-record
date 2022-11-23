package com.williams.studentrecord.repositories;

import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.enums.Term;
import com.williams.studentrecord.model.entity.SubjectRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRecordRepository extends JpaRepository<SubjectRecord, Long> {

    SubjectRecord findBySubjectNameAndStudentIdAndTerm(String subjectName, Long studentId, Term term);

    @Query("SELECT s FROM SubjectRecord s inner join Student  r on s.studentId = r.studentId WHERE ((:studentName IS NULL) OR (:studentName IS NOT NULL AND r.studentName = :studentName))" +
            " AND ((:subjectName IS NULL) OR (:subjectName IS NOT NULL AND s.subjectName = :subjectName))" +
            " AND ((:className IS NULL) OR (:className IS NOT NULL AND s.nameOfClass = :className))" +
            " AND ((:term IS NULL) OR (:term IS NOT NULL AND s.term = :term)) order by s.subjectId desc")
    List<SubjectRecord> findSubjectRecords(@Param("studentName") String studentName,
                                     @Param("subjectName") String subjectName,
                                     @Param("className") NameOfClass className,
                                     @Param("term") Term term);



}
