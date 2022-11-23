package com.williams.studentrecord.helper;

import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.exception.BadRequestException;
import com.williams.studentrecord.exception.ConflictException;
import com.williams.studentrecord.exception.NotFoundException;
import com.williams.studentrecord.model.entity.Student;
import com.williams.studentrecord.model.entity.SubjectRecord;
import com.williams.studentrecord.model.request.StudentRequestDto;
import com.williams.studentrecord.model.request.SubjectRecordRequestDto;
import com.williams.studentrecord.repositories.StudentRepository;
import com.williams.studentrecord.util.CustomResponseCode;
import com.williams.studentrecord.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Validations {

    private final StudentRepository studentRepository;

    public Validations(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void classValidation (SubjectRecord requestDto){
        if (requestDto.getNameOfClass().equals(NameOfClass.A1)){
            if (!("Maths".equalsIgnoreCase(requestDto.getSubjectName()) || "English".equalsIgnoreCase(requestDto.getSubjectName()) || "Writing".equalsIgnoreCase(requestDto.getSubjectName()) ||"General Science".equalsIgnoreCase(requestDto.getSubjectName()))) {
                throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Wrong subject combination for class A1");
            }
        }
        if (requestDto.getNameOfClass().equals(NameOfClass.A2)){
            if (!("Maths".equalsIgnoreCase(requestDto.getSubjectName()) || "Arts".equalsIgnoreCase(requestDto.getSubjectName()) || "Economics".equalsIgnoreCase(requestDto.getSubjectName()) ||"English".equalsIgnoreCase(requestDto.getSubjectName()))) {
                throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Wrong subject combination for class A2");
            }
        }

        if (requestDto.getNameOfClass().equals(NameOfClass.B1)){
            if (!("Maths".equalsIgnoreCase(requestDto.getSubjectName()) || "English".equalsIgnoreCase(requestDto.getSubjectName()) || "Biology".equalsIgnoreCase(requestDto.getSubjectName()) ||"Physics".equalsIgnoreCase(requestDto.getSubjectName()) || "Chemistry".equalsIgnoreCase(requestDto.getSubjectName()) || "Geography".equalsIgnoreCase(requestDto.getSubjectName()))) {
                throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Wrong subject combination for class B1");
            }
        }

        if (requestDto.getNameOfClass().equals(NameOfClass.B2)){
            if (!("Maths".equalsIgnoreCase(requestDto.getSubjectName()) || "Painting".equalsIgnoreCase(requestDto.getSubjectName()) || "Drawing".equalsIgnoreCase(requestDto.getSubjectName()) ||"Music".equalsIgnoreCase(requestDto.getSubjectName()) || "Accounting".equalsIgnoreCase(requestDto.getSubjectName()) || "English".equalsIgnoreCase(requestDto.getSubjectName()))) {
                throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Wrong subject combination for class B1");
            }
        }

        if (requestDto.getScore() > 100){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,"Maximum score should not be over 100%");
        }
        if (requestDto.getScore() < 0){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,"Please provide a valid score");
        }
        if (requestDto.getStudentId() == null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,"StudentId can not be empty");
        }
        Student savedStudent = studentRepository.findById(requestDto.getStudentId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Student record does not exist!"));
    }

    public void studentValidation (StudentRequestDto requestDto) {
        if (requestDto.getStudentName() == null) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Student name can not be empty");
        }

        if (requestDto.getPhoneNumber() == null) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "phone number can not be empty");
        }

        if (requestDto.getAddress() == null) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "address can not be empty");
        }

        if (requestDto.getEmail() == null) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid data type for Email ");
        }
        if (!Utility.validEmail(requestDto.getEmail().toString()))
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid data type for Email ");
    }

}
