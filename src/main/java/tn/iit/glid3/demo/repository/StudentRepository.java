package tn.iit.glid3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.glid3.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
