package com.JPA.Spring.JPA.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_secuence_generator")
    @SequenceGenerator(name = "my_secuence_generator", sequenceName="my_sequence", allocationSize = 1)
    private Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private String address;
    private String email;

}
