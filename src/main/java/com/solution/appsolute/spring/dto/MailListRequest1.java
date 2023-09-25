package com.solution.appsolute.spring.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailListRequest1 {
    Integer mailNum;
    String mailTitle;
    String mailContent;
    LocalDateTime mailDate;
    Integer senderId;
    String senderName;
    String senderEmail;
    Integer receiverId;
    String receiverName;
    String receiverEmail;
    Integer mailCheck;
}
