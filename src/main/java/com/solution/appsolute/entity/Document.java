package com.solution.appsolute.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Document {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentNum;

    @Column(nullable = false)
    private String documentName;
}

