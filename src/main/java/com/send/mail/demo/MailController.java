package com.send.mail.demo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@Controller
public class MailController {

    @Autowired
    MailProviderA mailProviderA;

    @Autowired
    MailProviderB mailProviderB;

    Queue<MailDTO> queue = new LinkedList();

    @RequestMapping({"/", "/home"})
    public String welcome(Map<String, Object> model) {
        return "mail";
    }

    @PostMapping(value = "/send-mail")
    @ResponseBody
    public String postMail(@RequestBody MailDTO mailDTO) {

        queue.add(mailDTO);

        sendMail();
        return "Mail işleminiz kuyruğa alınmıştır.";

    }


    @Async("sendMailExecutor")
    //@Timeable(limit = 60, unit = TimeUnit.SECONDS)
    public boolean sendMail() {

        MailDTO mail = queue.remove();
        boolean result;
        if (mail.getMailType().equals(MailType.WELCOME)) {
            result = mailProviderA.welcome(mail.getName(), mail.getLastName());
        } else if (mail.getMailType().equals(MailType.FORGOTPASSWORD)) {
            result = mailProviderA.forgotPassword(mail.getResetPasswordUrl());
        } else {
            result = mailProviderB.weeklyNewsLetter(mail.getName(), mail.getLastName(), mail.getLetterDate(), mail.getNewsletter());
        }

        return result;
    }
}
