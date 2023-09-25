package com.solution.appsolute.spring.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MailList {
    private int mail_num;
    private String mail_title;
    private String mail_content;
    private LocalDateTime mail_date;
    private int mail_sender;
    private int mail_receiver;
    private int mail_check;
    private String emp_name;

}
