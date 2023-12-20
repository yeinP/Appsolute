package com.solution.appsolute.board.dto;


import com.solution.appsolute.entity.BaseTimeEntity;
import com.solution.appsolute.entity.Board;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDto extends BaseTimeEntity {

    private Long id;
    private String title;
    private String content;
    private String createdBy;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private Long countVisit;
    //private MultipartFile attachment;

    @Builder
    public BoardDto(Long id, String title, String content, String createdBy,
                    LocalDate createdDate, LocalDate modifiedDate, Long countVisit, MultipartFile attachment) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.countVisit = countVisit;
        //this.attachment = attachment;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .countVisit(countVisit)
                .build();
    }

    public BoardDto(Board board) {
        id = board.getId();
        title = board.getTitle();
        content = board.getContent();
        createdBy = board.getCreatedBy();
        createdDate = board.getCreatedDate();
        modifiedDate = board.getModifiedDate();
        countVisit = board.getCountVisit();

    }

    public void updateVisit(Long countVisit){
        this.countVisit = countVisit;
    }
}
