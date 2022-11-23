package com.williams.studentrecord.model.request;

import com.williams.studentrecord.enums.NameOfClass;
import lombok.Data;

@Data
public class StudentRequestDto {

    private String studentName;
    private String email;
    private String address;
    private String phoneNumber;
    private Long studentId;
    private NameOfClass nameOfClass;
}