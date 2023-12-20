package com.solution.appsolute.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddPersonVO {
    private int empCheckNum;
    private int sequence;
    private long approvalNum;
}
