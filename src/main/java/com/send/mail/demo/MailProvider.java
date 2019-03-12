package com.send.mail.demo;

import org.springframework.stereotype.Service;

@Service
public abstract class MailProvider {

    private String userName;
    private String password;
    private String endPointURL;

    public MailProvider(String userName, String password, String endPointURL) {
        this.userName = userName;
        this.password = password;
        this.endPointURL = endPointURL;
    }

    public MailProvider() {

    }

    public boolean send(String to, String cc, String subject, String content) {
        int count = 0;
        while (count <= 3) {
            try {
                //smth
                return true;
            } catch (Exception e) {
                count++;
            }
        }

        return false;
    }

}
