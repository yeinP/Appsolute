package com.solution.appsolute.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExpenseReport {

    private String title;
    private String approvalDate;
    private String creationDate;
    private String department;
    private String author;
    private int totalAmount;
    private String reason;
}
