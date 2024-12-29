package tn.iit.glid3.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String street;
}
