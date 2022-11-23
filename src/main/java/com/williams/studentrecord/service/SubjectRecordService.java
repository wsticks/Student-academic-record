package com.williams.studentrecord.service;

import com.williams.studentrecord.enums.NameOfClass;
import com.williams.studentrecord.enums.Term;
import com.williams.studentrecord.exception.ConflictException;
import com.williams.studentrecord.exception.NotFoundException;
import com.williams.studentrecord.helper.Validations;
import com.williams.studentrecord.model.entity.Student;
import com.williams.studentrecord.model.entity.SubjectRecord;
import com.williams.studentrecord.model.request.SubjectRecordRequestDto;
import com.williams.studentrecord.model.response.SubjectRecordResponseDto;
import com.williams.studentrecord.repositories.StudentRepository;
import com.williams.studentrecord.repositories.SubjectRecordRepository;
import com.williams.studentrecord.util.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SubjectRecordService {

    private final ModelMapper mapper;
    private final Validations validations;
    private final SubjectRecordRepository subjectRecordRepository;
    private final StudentRepository studentRepository;

    public SubjectRecordService(ModelMapper mapper, Validations validations, SubjectRecordRepository subjectRecordRepository, StudentRepository studentRepository) {
        this.mapper = mapper;
        this.validations = validations;
        this.subjectRecordRepository = subjectRecordRepository;
        this.studentRepository = studentRepository;
    }

    public SubjectRecordResponseDto createSubjectRecord(SubjectRecordRequestDto requestDto){
//         validations.classValidation(requestDto);
        SubjectRecord subjectRecord = mapper.map(requestDto, SubjectRecord.class);
        SubjectRecord studentExist = subjectRecordRepository.findBySubjectNameAndStudentIdAndTerm(requestDto.getSubjectName(),requestDto.getStudentId(),requestDto.getTerm());
        if(studentExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,
                    " Subject record already exist");
        }
        Student SavedStudent = studentRepository.findById(requestDto.getStudentId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Student record does not exist!"));
        subjectRecord.setNameOfClass(SavedStudent.getNameOfClass());
        subjectRecord.setStudentName(SavedStudent.getStudentName());
        validations.classValidation(subjectRecord);
        subjectRecord = subjectRecordRepository.save(subjectRecord);
        log.debug("Create new Subject record - {}"+ subjectRecord);
        return mapper.map(subjectRecord, SubjectRecordResponseDto.class);
    }

    public SubjectRecordResponseDto updateSubjectRecord(SubjectRecordRequestDto request) {
        //validations.validateLGA(request);
        SubjectRecord savedRecorde = subjectRecordRepository.findBySubjectNameAndStudentIdAndTerm (request.getSubjectName(),request.getStudentId(),request.getTerm());
        if(savedRecorde == null) {
            new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                    "Subject record does not exist!");
        }
        mapper.map(request, savedRecorde);
        subjectRecordRepository.save(savedRecorde);
        log.debug("Subject record updated - {}" + savedRecorde);
        return mapper.map(savedRecorde, SubjectRecordResponseDto.class);
    }

    public List<SubjectRecord> findSubjectRecords(String studentName, String subjectName, NameOfClass clasName, Term term){
        List<SubjectRecord> subjectRecords = subjectRecordRepository.findSubjectRecords(studentName,subjectName,clasName,term);
        if(subjectRecords == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        subjectRecords.forEach(student -> {

        });
        return subjectRecords;
    }
}
