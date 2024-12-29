package tn.iit.glid3.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tn.iit.glid3.demo.entity.Address;
import tn.iit.glid3.demo.entity.Student;
import tn.iit.glid3.demo.repository.AddressRepository;
import tn.iit.glid3.demo.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		final StudentRepository studentRepository;
		final AddressRepository addressRepository;

		ConfigurableApplicationContext contexte = SpringApplication.run(DemoApplication.class, args);

		// Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance
		studentRepository =contexte.getBean(StudentRepository.class);
		addressRepository = contexte.getBean(AddressRepository.class);

		System.out.println("---------Insertion de l'étudiant Jemal Ahmed et de son adresse ----------");
		Address address1 = new Address("Rte Mehdia Km 05", "Sfax");
		addressRepository.save(address1);
		Student student1 = new Student("Jemal", "Ahmed", "jmlhmd@gmail.com", address1);
		studentRepository.save(student1);

		System.out.println("---------Insertion de l'étudiant Ben Halima Riadh et de son adresse ----------");
		Address address2 = new Address("Rte Menzel Chaker Km 04", "Sfax");
		addressRepository.save(address2);
		Student student2 = new Student("Ben Halima", "Riadh", "riadh.benhalima@enis.tn", address2);
		studentRepository.save(student2);

		System.out.println("---------Changer l’adresse du premier étudiant ----------");
		Optional<Student> studentOptional = studentRepository.findById(student1.getId());
		if (studentOptional.isPresent()) {
			Student studentToUpdate = studentOptional.get();
			Address newAddress = new Address("Avenue Habib Bourguiba", "Tunis");
			addressRepository.save(newAddress);
			studentToUpdate.setAddress(newAddress);
			studentRepository.save(studentToUpdate);
		}

		System.out.println("---------Afficher les deux étudiants ----------");
		List<Student> students = studentRepository.findAll();
		students.forEach(System.out::println);

		System.out.println("---------Supprimer l’étudiant non modifié ----------");
		studentRepository.delete(student2);
	}

}
