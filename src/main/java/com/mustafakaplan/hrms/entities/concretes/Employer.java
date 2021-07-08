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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobPostings", "company"})
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    private boolean isVerifiedEmail;

    private boolean isVerifiedAccount;

    @OneToMany(mappedBy = "employer")
    private List<JobPosting> jobPostings;

    @ManyToOne
    private Company company;
}
