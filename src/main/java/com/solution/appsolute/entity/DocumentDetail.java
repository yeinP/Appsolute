package com.solution.appsolute.entity;

import javax.persistence.*;

@Entity
public class DocumentDetail {

    @Id
    @Column(nullable = false)
    private Long documentNum;

    @Column(nullable = false)
    private String fieldName;

    @Column(nullable = false)
    private int sequence;

    @Column
    private int format;
}
