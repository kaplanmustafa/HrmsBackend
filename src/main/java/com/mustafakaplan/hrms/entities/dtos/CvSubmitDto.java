package com.mustafakaplan.hrms.entities.dtos;

import com.mustafakaplan.hrms.entities.concretes.ForeignLanguage;
import com.mustafakaplan.hrms.entities.concretes.JobExperience;
import com.mustafakaplan.hrms.entities.concretes.School;
import com.mustafakaplan.hrms.entities.concretes.Technology;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CvSubmitDto {

    private List<School> schools;

    private List<JobExperience> jobExperiences;

    private List<ForeignLanguage> foreignLanguages;

    private MultipartFile imageFile;

    private String github;

    private String linkedin;

    private List<Technology> technologies;

    private String coverLetter;
}
