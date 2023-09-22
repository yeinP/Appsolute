package com.solution.appsolute.entity;

import javax.persistence.*;

@Entity
public class ApprovalContentDetail {

    @Id
    @Column(nullable = false)
    private Long approvalNum;

    @Column(nullable = false)
    private long documentNum;

    @Column(length = 50)
    private String key1;
    @Column(length = 50)
    private String value1;
    @Column(length = 50)
    private String key2;
    @Column(length = 50)
    private String value2;
    @Column(length = 50)
    private String key3;
    @Column(length = 50)
    private String value3;
    @Column(length = 50)
    private String key4;
    @Column(length = 50)
    private String value4;
    @Column(length = 50)
    private String key5;
    @Column(length = 50)
    private String value5;
    @Column(length = 50)
    private String key6;
    @Column(length = 50)
    private String value6;
    @Column(length = 50)
    private String key7;
    @Column(length = 50)
    private String value7;
    @Column(length = 50)
    private String key8;
    @Column(length = 50)
    private String value8;
    @Column(length = 50)
    private String key9;
    @Column(length = 50)
    private String value9;
    @Column(length = 50)
    private String key10;
    @Column(length = 50)
    private String value10;
}
