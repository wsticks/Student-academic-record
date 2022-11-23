package com.williams.studentrecord.service;

import com.williams.studentrecord.exception.ConflictException;
import com.williams.studentrecord.exception.NotFoundException;
import com.williams.studentrecord.helper.Validations;
import com.williams.studentrecord.model.entity.Student;
import com.williams.studentrecord.model.request.StudentRequestDto;
import com.williams.studentrecord.model.request.SubjectRecordRequestDto;
import com.williams.studentrecord.model.response.StudentResponseDto;
import com.williams.studentrecord.repositories.StudentRepository;
import com.williams.studentrecord.util.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper mapper;
    private final Validations validations;

    public StudentService(StudentRepository studentRepository, ModelMapper mapper, Validations validations) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.validations = validations;
    }

    public StudentResponseDto createStudent(StudentRequestDto requestDto){
        validations.studentValidation(requestDto);
        Student student = mapper.map(requestDto, Student.class);
        Student studentExist = studentRepository.findStudentByEmail(requestDto.getEmail());
        if(studentExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,
                    " Student record already exist");
        }
        int countOfStudentSize = studentRepository.countAllByNameOfClass(requestDto.getNameOfClass());
        if(countOfStudentSize > 10){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,
                    " Class has reached its maximum capacity");
        }
        validations.studentValidation(requestDto);
        student = studentRepository.save(student);
        log.debug("Create new Student record - {}"+ student);
        return mapper.map(student, StudentResponseDto.class);
    }

    public StudentResponseDto updateStudent(StudentRequestDto request) {
//        validations.classValidation(request);
        Student savedStudent = studentRepository.findStudentByEmail (request.getEmail());
        if(savedStudent == null) {
                new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                    "Student record does not exist!");
        }
        mapper.map(request, savedStudent);
        studentRepository.save(savedStudent);
        log.debug("Student record updated - {}" + savedStudent);
        return mapper.map(savedStudent, StudentResponseDto.class);
    }

    public StudentResponseDto findStudent(String email){
        Student savedStudentResult = studentRepository.findStudentByEmail(email);
        if(savedStudentResult == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                    "Student record does not exist!");
        }
        return mapper.map(savedStudentResult,StudentResponseDto.class);
    }

    public List<Student> findAllStudents(String email, String studentName){
        List<Student> students = studentRepository.findStudentResults(email,studentName);
        if(students == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        students.forEach(student -> {

        });
        return students;
    }
}
