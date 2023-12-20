package com.solution.appsolute.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddDetailVO {
    private long approvalNum;
    private int documentNum;
    private String categoryKey;
    private String category;
    private String costKey;
    private int cost;
    private String detailKey;
    private String detail;



}
