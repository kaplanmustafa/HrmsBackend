package com.mustafakaplan.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;

    private String website;

    private String phone;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Employer> employers;
}
