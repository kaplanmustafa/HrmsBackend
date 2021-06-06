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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "foreignLanguages"})
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<School> schools;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<JobExperience> jobExperiences;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<ForeignLanguage> foreignLanguages;

    private String imageUrl;

    private String github;

    private String linkedin;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Technology> technologies;

    @Column(length = 2000)
    private String coverLetter;

    @OneToOne
    private Employee employee;
}
