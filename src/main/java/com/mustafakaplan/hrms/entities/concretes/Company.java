package com.mustafakaplan.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobPostings"})
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;

    private String website;

    private String phone;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Employer> employers;

    @OneToMany(mappedBy = "company")
    private List<JobPosting> jobPostings;
}
