package com.send.mail.demo;

import org.springframework.stereotype.Service;

@Service
public class MailProviderA extends MailProvider {

    private String subject;
    private StringBuilder body = new StringBuilder();

    public boolean welcome(String firstName, String lastName) {
        subject="Hoşgeldiniz!";
        body.append("Sayın ");
        body.append(firstName + lastName);
        body.append(", aramıza hoşgeldiniz.");

       return send("","",subject, body.toString());
    }

    public boolean forgotPassword(String passwordResetURL) {
        subject="Şifre Yenileme!";
        body.append("Şifrenizi yenilemek için aşağıdaki linke tıklayınız \n");
        body.append(passwordResetURL);

        return send("","",subject, body.toString());

    }

}
