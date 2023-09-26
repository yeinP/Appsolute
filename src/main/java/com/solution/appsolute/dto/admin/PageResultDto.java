package com.solution.appsolute.dto.admin;

import lombok.Data;

import java.util.List;

@Data
public class PageResultDto <DTO, EN>{
    private List<DTO> employeeList;

    private int totalPage;

    private int page;

    private int size;

    private int start, end;
    private boolean prev, next;

//    private List<Integer> pageList;
//    public PageResultDto(Page<EN> result, Function<EN, DTO> fn) {
//        employeeList = result.stream().map(fn).collect(Collectors.toList());
//        totalPage = result.getTotalPages();
//        makePageList(result.getPageable());
//
//    }
//
////    private void makePageList(Pageable pageable) {
////        this.page = pageable.getPageNumber() + 1;
////        this.size = pageable.getPageSize();
////
////        int temped
////    }
////


}
