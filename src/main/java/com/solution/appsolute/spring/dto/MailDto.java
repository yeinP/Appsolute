package com.solution.appsolute.spring.dto;

import lombok.*;

import java.time.LocalDateTime;

public interface MailDto {
    int getMailNum();
    String getMailTitle();
    String getMailContent();
    LocalDateTime getMailDate();
    int getMailSender();
    int getMailReceiver();
    int getMailCheck();
    String getEmpName();
}
