package com.williams.studentrecord.controller;

import com.williams.studentrecord.model.entity.Student;
import com.williams.studentrecord.model.request.StudentRequestDto;
import com.williams.studentrecord.model.response.Response;
import com.williams.studentrecord.model.response.StudentResponseDto;
import com.williams.studentrecord.service.StudentService;
import com.williams.studentrecord.util.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("studentinfo")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("")
    public ResponseEntity<Response> createStudentRecord(@Validated @RequestBody StudentRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        StudentResponseDto response = studentService.createStudent(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateStudent(@Validated @RequestBody  StudentRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        StudentResponseDto response = studentService.updateStudent(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Response> getStudent(@PathVariable String email){
        HttpStatus httpCode ;
        Response resp = new Response();
        StudentResponseDto response = studentService.findStudent(email);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("")
    public ResponseEntity<Response> getStudents(@RequestParam(value = "email",required = false)String email,
                                             @RequestParam(value = "studentName",required = false)String studentName){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Student> response = studentService.findAllStudents( email,studentName);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
