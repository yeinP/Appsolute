package com.solution.appsolute.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empNum;

    @Column(length=30, nullable = false)
    private String empName;

    @Column(nullable = false)
    private Long deptNo;

    @Column(length = 50, nullable = false)
    private String empPhone;

    @Column(length = 100, nullable = false)
    private String empEmail;

    @Column(length = 50, nullable = false)
    private String empPassword;

    @Column(nullable = true)
    private LocalDateTime empHireDate;

    @Column(length = 20, nullable = false)
    private String empPosition;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int empLeader;

    @Column
    private int empMgr;
}
