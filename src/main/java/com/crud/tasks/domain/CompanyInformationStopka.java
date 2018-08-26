package com.crud.tasks.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyInformationStopka {

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.email}")
    private String emailName;

    @Value("${info.company.phone}")
    private String phoneName;

}
