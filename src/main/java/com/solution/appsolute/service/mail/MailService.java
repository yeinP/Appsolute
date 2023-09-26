package com.solution.appsolute.service.mail;

import com.solution.appsolute.dao.mail.repository.MailDao;
import com.solution.appsolute.dao.mail.repository.MailRepository;
import com.solution.appsolute.dto.mail.MailList;
import com.solution.appsolute.dto.mail.MailPage;
import com.solution.appsolute.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    @Autowired
    private MailDao mailDao; // 마이바티스

    @Autowired
    private MailRepository mailRepository; // jpa

    private int size = 10; // 기본 페이지 사이즈

    // 페이지 끊어서 가져오는 메소드(list)
    public MailPage getMailPage(Long id, int pageNum){
        int total = mailRepository.countBy();
        List<MailList> content = mailDao.listDao(id, (pageNum - 1) * size, size);
        return new MailPage(total, pageNum, size, content);
    }
}
