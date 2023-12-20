package com.solution.appsolute.admin.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDto {
    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageRequestDto() {
        this.page = 1;
        this.size = 5;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page -1, size, sort);
    }
//    public Pageable getPageable(Sort sort) {
//        int correctedPage = Math.max(page -1, 0); // Ensure page is at least 0
//        return PageRequest.of(correctedPage, size, sort);
//
//    }

}
