package com.solution.appsolute.mail.service;

import com.solution.appsolute.entity.Mail;
import com.solution.appsolute.mail.dao.mapper.MailMapper;
import com.solution.appsolute.mail.dto.*;
import com.solution.appsolute.mail.dao.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private MailMapper mailMapper; // 마이바티스

    @Autowired
    private MailRepository mailRepository; // jpa

    private int size = 10; // 기본 페이지 사이즈

    // 페이지 끊어서 가져오는 메소드(전체 메일)
    public MailPage getMailPage(Long id, int pageNum) {
        int total = mailMapper.countDao(id, (pageNum - 1) * size, size);
        List<MailList> content = mailMapper.listDao(id, (pageNum - 1) * size, size);
        return new MailPage(total, pageNum, size, content, id);
    }

    // 페이지 끊어서 가져오는 메소드(안 읽은 메일)
    public MailPage getUnreadMailPage(Long id, int pageNum, Pageable pageable) {
        int total = mailMapper.countUnreadDao(id);
        List<MailList> content = mailMapper.unreadDao(id, (pageNum - 1) * size, size);
        return new MailPage(total, pageNum, size, content, id);
    }

    // 페이지 끊어서 가져오는 메소드(특정 인물이 보낸 메일)
    public MailPage getUnreadMailPageLikeEmp(Long id, int pageNum, Long mailSender) {
        int total = mailMapper.countLikeEmp(id, mailSender);
        List<MailList> content = mailMapper.findByMailReceiveLikeEmp(id, (pageNum - 1) * size, size, mailSender);
        return new MailPage(total, pageNum, size, content, id);
    }

    @Transactional
    public MailListRequest getMail(Long id, Long mailNum) { // 조회수 증가 관련

        MailListRequest mail = mailRepository.list(id, mailNum);
        if (mail == null) {
            throw new MailNotFoundException();
        }
        if (!id.equals(mail.getSenderId())) {
            mailRepository.increaseReadCount(mailNum);
        }

        return mail;
    }

    @Transactional
    public Long mailSend(Long id, writeMailDto writeMailDto) {
        Mail entity = mailDtoToentity(id, writeMailDto);
        mailRepository.save(entity);
        return entity.getMailNum();
    }

    Mail mailDtoToentity(Long id, writeMailDto writeMailDto) {
        Mail entity = Mail.builder().mailNum(writeMailDto.getMailNum()).mailTitle(writeMailDto.getMailTitle()).mailContent(writeMailDto.getMailContent()).mailDate(LocalDateTime.now()).mailSender(id).mailReceiver(writeMailDto.getMailReceiver()).mailCheck(writeMailDto.getMailCheck()).build();
        return entity;
    }

//    public void mailCheckDel(List<String> mailArray) {
//        for(int i = 0; i < mailArray.size(); i++) {
//            mailMapper.deleteById(mailArray.get(i));
//        }
//    }
}
