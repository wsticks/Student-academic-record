package com.williams.studentrecord.controller;

import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.enums.Term;
import com.williams.studentrecord.model.entity.SubjectRecord;
import com.williams.studentrecord.model.request.SubjectRecordRequestDto;
import com.williams.studentrecord.model.response.Response;
import com.williams.studentrecord.model.response.SubjectRecordResponseDto;
import com.williams.studentrecord.service.SubjectRecordService;
import com.williams.studentrecord.util.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("records")
public class SubjectRecordController {

    private final SubjectRecordService subjectRecordService;

    public SubjectRecordController(SubjectRecordService subjectRecordService) {
        this.subjectRecordService = subjectRecordService;
    }

    @PostMapping("")
    public ResponseEntity<Response> createSubjectRecord(@Validated @RequestBody SubjectRecordRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        SubjectRecordResponseDto response = subjectRecordService.createSubjectRecord(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateSubjectRecord(@Validated @RequestBody SubjectRecordRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        SubjectRecordResponseDto response = subjectRecordService.updateSubjectRecord(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("")
    public ResponseEntity<Response> getStudents(@RequestParam(value = "studentName",required = false)String studentName,
                                                @RequestParam(value = "subjectName",required = false)String subjectName,
                                                @RequestParam(value = "clasName",required = false)NameOfClass clasName,
                                                @RequestParam(value = "term",required = false)Term term){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<SubjectRecord> response = subjectRecordService.findSubjectRecords(studentName,subjectName,clasName, term);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
