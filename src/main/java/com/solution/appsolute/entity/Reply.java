package com.solution.appsolute.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long ReNum;

    @Column(nullable = false)
    private Long noNum;

    @Column(nullable = false)
    private String reContent;

    @Column(nullable = false)
    private String reRegdate;

    @Column(nullable = false)
    private Long empNum;
}
