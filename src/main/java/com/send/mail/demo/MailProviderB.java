package com.send.mail.demo;

import org.springframework.stereotype.Service;

@Service
public class MailProviderB extends MailProvider {

    private String subject;
    private StringBuilder body = new StringBuilder("Merhaba");

    public boolean weeklyNewsLetter(String firstName, String lastName, String newsletterDate, String newsletter) {
        subject = firstName + "," + newsletterDate + " tarihli bültenimizi kaçırma";
        body.append(firstName + lastName);
        body.append("; \n " + newsletter);

        return send("", "", subject, body.toString());

    }
}
