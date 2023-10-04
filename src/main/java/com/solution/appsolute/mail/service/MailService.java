package com.solution.appsolute.mail.service;

import com.solution.appsolute.entity.Mail;
import com.solution.appsolute.mail.dao.mapper.MailMapper;
import com.solution.appsolute.mail.dto.*;
import com.solution.appsolute.mail.dao.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private MailMapper mailDao; // 마이바티스

    @Autowired
    private MailRepository mailRepository; // jpa

    private int size = 10; // 기본 페이지 사이즈

    // 페이지 끊어서 가져오는 메소드(전체 메일)
    public MailPage getMailPage(Long id, int pageNum) {
        int total = mailDao.countDao(id, (pageNum - 1) * size, size);
        List<MailList> content = mailDao.listDao(id, (pageNum - 1) * size, size);
        return new MailPage(total, pageNum, size, content, id);
    }

    // 페이지 끊어서 가져오는 메소드(안 읽은 메일)
    public MailPage getUnreadMailPage(Long id, int pageNum) {
        int total = mailDao.countUnreadDao(id, (pageNum - 1) * size, size);
        List<MailList> content = mailDao.unreadDao(id, (pageNum - 1) * size, size);
        return new MailPage(total, pageNum, size, content, id);
    }

    @Transactional
    public MailListRequest getMail(Long id, Long mailNum, boolean increaseReadCount) { // 조회수 증가 관련
        MailListRequest mail = mailRepository.list(id, mailNum);
        if (mail == null) {
            throw new MailNotFoundException();
        }
        if (increaseReadCount) {
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
}
