package tn.iit.glid3.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.iit.glid3.demo.dto.CreateStudentRequest;
import tn.iit.glid3.demo.entity.Address;
import tn.iit.glid3.demo.entity.Student;
import tn.iit.glid3.demo.repository.AddressRepository;
import tn.iit.glid3.demo.repository.StudentRepository;
import tn.iit.glid3.demo.response.StudentResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Address newAddress = new Address(createStudentRequest.getCity(), createStudentRequest.getStreet());
        Student student = new Student(createStudentRequest.getFirstName(), createStudentRequest.getLastName(), createStudentRequest.getEmail(), newAddress);
        addressRepository.save(newAddress);
        Student newStudent = studentRepository.save(student);
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(newStudent.getId());
        studentResponse.setFirstName(newStudent.getFirstName());
        studentResponse.setLastName(newStudent.getLastName());
        studentResponse.setEmail(newStudent.getEmail());
        studentResponse.setCity(newStudent.getAddress().getCity());
        studentResponse.setStreet(newStudent.getAddress().getStreet());
        return studentResponse;
    }

    @Override
    public StudentResponse updateStudent(CreateStudentRequest createStudentRequest) {
        return null;
    }

    @Override
    public StudentResponse getById(Integer id) {
        Optional<Student> studentFound = studentRepository.findById(id);
        StudentResponse studentResponse = new StudentResponse();
        if ( studentFound.isPresent()) {
            studentResponse.setId(studentFound.get().getId());
            studentResponse.setFirstName(studentFound.get().getFirstName());
            studentResponse.setLastName(studentFound.get().getLastName());
            studentResponse.setEmail(studentFound.get().getEmail());
            studentResponse.setCity(studentFound.get().getAddress().getCity());
            studentResponse.setStreet(studentFound.get().getAddress().getStreet());
            return studentResponse;
        }

        return null;
    }

    @Override
    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(this::mapToStudentResponse)
                .collect(Collectors.toList());
    }

    private StudentResponse mapToStudentResponse(Student student) {
        StudentResponse response = new StudentResponse();

        if (student.getId() != null) {
            response.setId(student.getId().intValue()); // Conversion Long -> Integer (avec vérification)
        }

        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());

        if (student.getAddress() != null) {
            response.setCity(student.getAddress().getCity());
            response.setStreet(student.getAddress().getStreet());
        }

        return response;
    }

    @Override
    public void deleteById(Integer id) {
        // Check if student exists
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with ID " + id + " not found"));

        studentRepository.delete(student);
    }

}
