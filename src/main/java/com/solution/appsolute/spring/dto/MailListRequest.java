package com.solution.appsolute.spring.dto;

import lombok.*;

import java.time.LocalDateTime;

public interface MailListRequest {
    Integer getMailNum();
    String getMailTitle();
    String getMailContent();
    LocalDateTime getMailDate();
    Integer getSenderId();
    String getSenderName();
    String getSenderEmail();
    Integer getReceiverId();
    String getReceiverName();
    String getReceiverEmail();
    Integer getMailCheck();
}
