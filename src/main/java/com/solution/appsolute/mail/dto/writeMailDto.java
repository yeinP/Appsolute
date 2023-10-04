package com.solution.appsolute.mail.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class writeMailDto {

    private Long mailNum;
    private String mailTitle;
    private String mailContent;
    private LocalDateTime mailDate;
    private Long mailSender;
    private Long mailReceiver;
    private int mailCheck;
}
