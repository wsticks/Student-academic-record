package com.williams.studentrecord.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.enums.Term;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponseDto {

    private long studentId;
    private String studentName;
    private String email;
    private String address;
    private String phoneNumber;
    private NameOfClass nameOfClass;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
