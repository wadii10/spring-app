package tn.iit.glid3.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address implements Serializable /* obligatoire selon la spec JEE */ {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Integer id;

    private String city;

    private String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
