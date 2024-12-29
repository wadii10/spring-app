package tn.iit.glid3.demo.service;

import tn.iit.glid3.demo.dto.CreateStudentRequest;
import tn.iit.glid3.demo.response.StudentResponse;

import java.util.List;

public interface StudentService {

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest);
    public StudentResponse updateStudent(CreateStudentRequest createStudentRequest);
    public StudentResponse getById (long id);
    public List<StudentResponse> getAll ();
    public void deleteById (long id);

}
