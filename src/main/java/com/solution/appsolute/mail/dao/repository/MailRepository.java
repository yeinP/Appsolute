package com.solution.appsolute.mail.dao.repository;

import com.solution.appsolute.entity.Mail;
import com.solution.appsolute.mail.dto.MailDto;
import com.solution.appsolute.mail.dto.MailListRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    @Query(value = "select * from Mail order by mailNum desc", nativeQuery = true)
    List<Mail> getListDesc();

    List<Mail> findByMailReceiverOrMailSender(Long receiver, Long sender);

    @Query(value = "select m.mailNum as mailNum, m.mailCheck as mailCheck, m.mailSender as mailSender, m.mailReceiver as mailReceiver, " +
            "e.empName as empName, m.mailTitle as mailTitle, m.mailContent as mailContent, m.mailDate as mailDate " +
            " from Mail m inner join Employee e on m.mailReceiver = e.empNum where m.mailSender = :mailSender order by m.mailNum desc")
    Page<MailDto> findByMailSend(Long mailSender, Pageable pageable); // 보낸 메일 리스트

    @Query(value = "select m.mailNum as mailNum, m.mailCheck as mailCheck, m.mailSender as mailSender, m.mailReceiver as mailReceiver, " +
            "e.empName as empName, m.mailTitle as mailTitle, m.mailContent as mailContent, m.mailDate as mailDate " +
            " from Mail m inner join Employee e on m.mailSender = e.empNum where m.mailReceiver = :mailReceiver order by m.mailNum desc")
    Page<MailDto> findByMailReceive(Long mailReceiver, Pageable pageable); // 받은 메일 리스트


    @Query(value = "SELECT m.mailNum as mailNum, m.mailTitle as mailTitle, m.mailContent as mailContent, m.mailDate as mailDate, m.mailSender AS senderId, e.empName AS senderName, e.empEmail as senderEmail, " +
            "m.mailReceiver AS receiverId, f.empName AS receiverName, f.empEmail as receiverEmail, m.mailCheck as mailCheck " +
            " FROM Mail m " +
            "inner JOIN Employee e ON m.mailSender = e.empNum  " +
            " INNER JOIN Employee AS f ON m.mailReceiver = f.empNum" +
            " where( e.empNum = :id " +
            "or f.empNum = :id) " +
            "and m.mailNum = :num " +
            "order by m.mailNum desc")
    MailListRequest list(Long id, Long num); // 필요한 모든 데이터가 출력됨


    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query(value = "Update Mail m set m.mailCheck=0 where m.mailNum = :id")
    void updateById(Long id);

    @Transactional
    @Modifying
    @Query("update Mail m set m.mailCheck = m.mailCheck + 1 where m.mailNum = :no")
    void increaseReadCount(Long no) ;

    public Page<Mail> findAllByOrderByMailNumDesc(Pageable pageable);


}
