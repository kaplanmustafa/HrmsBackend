package com.mustafakaplan.hrms.business.concretes;

import com.mustafakaplan.hrms.business.abstracts.*;
import com.mustafakaplan.hrms.core.utilities.FileUtil;
import com.mustafakaplan.hrms.core.results.DataResult;
import com.mustafakaplan.hrms.core.results.Result;
import com.mustafakaplan.hrms.core.results.SuccessDataResult;
import com.mustafakaplan.hrms.core.results.SuccessResult;
import com.mustafakaplan.hrms.core.services.abstracts.ImageService;
import com.mustafakaplan.hrms.dataAccess.abstracts.CvDao;
import com.mustafakaplan.hrms.entities.concretes.*;
import com.mustafakaplan.hrms.entities.dtos.CvSubmitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvManager implements CvService {

    private final CvDao cvDao;
    private final ImageService imageService;
    private final SchoolService schoolService;
    private final EmployeeService employeeService;
    private final JobExperienceService jobExperienceService;
    private final ForeignLanguageService foreignLanguageService;
    private final TechnologyService technologyService;

    @Autowired
    public CvManager(CvDao cvDao, ImageService imageService, SchoolService schoolService, EmployeeService employeeService, JobExperienceService jobExperienceService, ForeignLanguageService foreignLanguageService, TechnologyService technologyService) {
        this.cvDao = cvDao;
        this.imageService = imageService;
        this.schoolService = schoolService;
        this.employeeService = employeeService;
        this.jobExperienceService = jobExperienceService;
        this.foreignLanguageService = foreignLanguageService;
        this.technologyService = technologyService;
    }

    @Override
    public Result add(CvSubmitDto cvSubmitDto, String identityNumber) {
        Cv cv = new Cv();
        Employee employee = employeeService.getEmployeeByIdentityNumber(identityNumber).getData();
        String imageUrl = null;

        if (cvSubmitDto.getImageFile() != null) {
            imageUrl = imageService.uploadImage(FileUtil.getFilePathFromMultipartFile(cvSubmitDto.getImageFile()), identityNumber);
        }

        cv.setImageUrl(imageUrl);
        cv.setGithub(cvSubmitDto.getGithub());
        cv.setLinkedin(cvSubmitDto.getLinkedin());
        cv.setCoverLetter(cvSubmitDto.getCoverLetter());
        cv.setEmployee(employee);
        cvDao.save(cv);

        for (School school : cvSubmitDto.getSchools()) {
            school.setCv(cv);
            schoolService.add(school);
        }

        for (JobExperience jobExperience : cvSubmitDto.getJobExperiences()) {
            jobExperience.setCv(cv);
            jobExperienceService.add(jobExperience);
        }

        for (ForeignLanguage foreignLanguage : cvSubmitDto.getForeignLanguages()) {
            foreignLanguage.setCv(cv);
            foreignLanguageService.add(foreignLanguage);
        }

        for (Technology technology : cvSubmitDto.getTechnologies()) {
            technology.setCv(cv);
            technologyService.add(technology);
        }

        return new SuccessResult("Kayıt Başarılı");
    }

    @Override
    public DataResult<Cv> getCvOfUser(String identityNumber) {
        Employee employeeInDB = employeeService.getEmployeeByIdentityNumber(identityNumber).getData();
        return new SuccessDataResult<>(cvDao.findByEmployee(employeeInDB), "Data Bulundu");
    }
}
