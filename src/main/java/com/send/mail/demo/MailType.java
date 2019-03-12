package com.send.mail.demo;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MailType {
    WELCOME("Hoşgeldiniz"),FORGOTPASSWORD("Şifre Yenileme"), NEWSLETTER("Haftlık Haber Bülteni");

    private String value;

    MailType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
