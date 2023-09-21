package com.solution.appsolute.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Long noNum;

    @Column(nullable = false)
    private String noTitle;

    @Column(nullable = false)
    private String noContent;

    @Column(nullable = false)
    private Long empNum;

    @Column(nullable = false)
    private Timestamp noRegdate;

    @Column(columnDefinition = "int default 0", nullable = false)
    private Integer noHits;

    @Column(columnDefinition = "int default 0", nullable = false)
    private Integer noImp;
}
