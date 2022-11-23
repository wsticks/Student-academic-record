package com.williams.studentrecord.model.entity;

import com.williams.studentrecord.enums.NameOfClass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Student {

    @Id
    @Column
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long studentId;
    private String studentName;
    private String email;
    private String address;
    private String phoneNumber;
    private NameOfClass nameOfClass;
    @Column(name = "createdAt", updatable = false, length = 30)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updatedAt", length = 30)
    @UpdateTimestamp
    private Timestamp updatedAt;


}
