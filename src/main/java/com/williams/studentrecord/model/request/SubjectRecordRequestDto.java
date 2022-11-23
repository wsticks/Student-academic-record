package com.williams.studentrecord.model.request;

import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.enums.Term;
import lombok.Data;

@Data
public class SubjectRecordRequestDto {

    private Term term;
    //private NameOfClass nameOfClass;
    private String subjectName;
    private double score;
    private Long StudentId;
    private String studentName;
}
