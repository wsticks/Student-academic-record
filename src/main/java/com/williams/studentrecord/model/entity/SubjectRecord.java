package com.williams.studentrecord.model.entity;

import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.enums.Term;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class SubjectRecord {

    @Id
    @Column
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long subjectId;
    private Term term;
    private NameOfClass nameOfClass;
    private String subjectName;
    private String studentName;
    private double score;
    private Long studentId;
    @Column(name = "createdAt", updatable = false, length = 30)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updatedAt", length = 30)
    @UpdateTimestamp
    private Timestamp updatedAt;



}
