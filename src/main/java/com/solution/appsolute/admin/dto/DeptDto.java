package com.solution.appsolute.admin.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeptDto {

    private Long deptNo;

    private String deptName;

    private String deptLoc;

    private int deptBranch;

}
