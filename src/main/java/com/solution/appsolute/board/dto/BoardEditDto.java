package com.solution.appsolute.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardEditDto {

    private String title;
    private String createdBy;
    private String content;

    @Builder
    public BoardEditDto(String title, String createdBy, String content) {
        this.title = title;
        this.createdBy = createdBy;
        this.content = content;
    }
}
