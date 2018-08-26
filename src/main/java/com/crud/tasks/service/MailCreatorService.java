package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CompanyInformationFooter;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyInformationFooter informationStopka;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){

        List<String> functionality;
        functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url","http://localhost:8888/crud" );
        context.setVariable("button", "Visit website in Magda");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("goodbye","Regards" );
        context.setVariable("nameCompany", informationStopka.getCompanyName());
        context.setVariable("mailCompany",informationStopka.getEmailName());
        context.setVariable("phoneComapny", informationStopka.getPhoneName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildMail() {
        List<String> functionality;
        functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", taskRepository.count());
        context.setVariable("admin_name", "Przyjacielu");
        context.setVariable("goodbye","Pozdrawiam, Zespół realizujący" );
        context.setVariable("is_friend", true);
        context.setVariable("nameCompany", informationStopka.getCompanyName());
        context.setVariable("mailCompany",informationStopka.getEmailName());
        context.setVariable("phoneComapny", informationStopka.getPhoneName());
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/indexMail", context);

    }
}
