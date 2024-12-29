package tn.iit.glid3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.glid3.demo.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
