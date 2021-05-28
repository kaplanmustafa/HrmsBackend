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
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;

    private String website;

    private String email;

    private String phone;

    private String password;

    private boolean isVerifiedEmail;

    private boolean isVerifiedAccount;

    @OneToMany(mappedBy = "employer")
    private List<JobPosting> jobPostings;
}
