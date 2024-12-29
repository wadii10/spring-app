package tn.iit.glid3.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.glid3.demo.dto.CreateStudentRequest;
import tn.iit.glid3.demo.response.StudentResponse;
import tn.iit.glid3.demo.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Message d'accueil
    // http://localhost:8080/api/sudent/index (GET)
    @RequestMapping(value ="/index" ,method = RequestMethod.GET)
    public String accueil() {
        return "BienVenue au service Web REST 'students' ";
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        StudentResponse studentResponse = studentService.createStudent(createStudentRequest);
        return ResponseEntity.ok(studentResponse);
    }
}
