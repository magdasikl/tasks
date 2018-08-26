package com.crud.tasks.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
//        Mail mail = new Mail("test@test.com", "Test", "Test Message", "");
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMassage());
//        if (mail.getToCc()!=null && ! mail.getToCc().equals ("")) {
//            mailMessage.setCc(mail.getToCc());
//        }
//
//        //When
//        simpleEmailService.send(mail);
//
//        //Then
//        verify(javaMailSender,times(1)).send(mailMessage);
////        System.out.println(mail.getMailTo()+" "+ mail.getSubject()+" "+ mail.getMassage()+" "+ mail.getToCc());
    }


}