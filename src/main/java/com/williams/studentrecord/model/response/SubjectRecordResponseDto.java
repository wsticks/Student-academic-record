package com.williams.studentrecord.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.enums.Term;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectRecordResponseDto {

    private Long CourseId;
    private Term term;
    private NameOfClass nameOfClass;
    private String subjectName;
    private String studentName;
    private double score;
    private Long StudentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
