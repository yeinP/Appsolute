package com.solution.appsolute.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseVO {
    private long approvalNum;
    private String documentNum;
    private String fieldValue;
    private String fieldValue2;
    private String fieldValue3;
    private String total;
    private String reason;
}
