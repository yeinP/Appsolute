package com.solution.appsolute.board.service;


import com.solution.appsolute.board.dao.BoardRepository;
import com.solution.appsolute.board.dto.BoardDto;
import com.solution.appsolute.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    private ThreadLocal<Long> countVisitStore = new ThreadLocal<>();

    @Transactional
    public Long saveBoard(BoardDto boardDto) {
        boardRepository.save(boardDto.toEntity());
        return boardDto.getId();
    }

    @Transactional
    public BoardDto getBoard(Long id){

        Optional<Board> boardWrapper = boardRepository.findById(id);
        if(boardWrapper.isPresent())
        {
            Board board = boardWrapper.get();

            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdBy(board.getCreatedBy())
                    .createdDate(board.getCreatedDate())
                    .modifiedDate(board.getModifiedDate())
                    .countVisit(board.getCountVisit())
                    .build();

            return boardDto;
        }

        return null;
    }



    @Transactional
    public void update(BoardDto dto) {
        Board board = boardRepository.findById(dto.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 게시물이 없습니다" + dto.getId()));

        board.update(dto.getTitle(), dto.getContent());
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }



    @Transactional
    public Page<Board> getBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);

    }

    public Page<Board> paging(int page) {
        return boardRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id")));

    }

//    @Transactional
//    public void updateVisit(Long id, BoardDto boardDto) {
//        Board board = boardRepository.findById(id).orElseThrow((() ->
//                new IllegalStateException("해당 게시글이 존재하지 않습니다.")));
//
//        board.updateVisit(boardDto.getCountVisit());
//    }

    public Board findById(Long id){
        Board board = boardRepository.findById(id).get();
        return board;
    }


    @Transactional
    public Long countVisitLogic(Long id) {

        Board board = boardRepository.findById(id).orElseThrow((() ->
                new IllegalStateException("해당 게시글이 존재하지 않습니다.")));

        log.info("저장 : ID={} board.getCountVisit={} ",id, countVisitStore.get());
        countVisitStore.set(board.getCountVisit() + 1L);
        board.updateVisit(countVisitStore.get());
        sleep(100);
        log.info("조회 : countVisitStore={}",countVisitStore.get());
        log.info("카운트 횟수={}", board.getCountVisit());

        countVisitStore.remove();
        return countVisitStore.get();
    }

    @Transactional
    public void deleteBoard(Long id) {
        Optional<Board> optBoard = boardRepository.findById(id);
        if(optBoard.isPresent()){
            Board board = optBoard.get();
            boardRepository.deleteById(id);
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
