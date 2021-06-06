package com.mustafakaplan.hrms.business.abstracts;

import com.mustafakaplan.hrms.core.utilities.results.Result;
import com.mustafakaplan.hrms.entities.concretes.ForeignLanguage;

public interface ForeignLanguageService {

    Result add(ForeignLanguage foreignLanguage);
}
