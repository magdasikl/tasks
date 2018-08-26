package com.crud.tasks.com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class EmailScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
      long size = taskRepository.count();
        String massage = "Currently in database you got: " + size;
        if (size == 1) {
             massage = massage + " task";
        } else {
            massage = massage +" tasks";
        }
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,massage, ""));
    }

    @Scheduled(cron = "0 0 8  * * *")
    public void sendMailScheduled() {
        LOGGER.info("Starting Scheduled email preparation...");
        try {
            javaMailSender.send(simpleEmailService.createMimeMessageSheduler(new Mail("msiklucka@gmail.com", "Informacja o ilości dostępnych zadań", "","" )));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }

    }

}
