package com.solution.appsolute.dao.mail.repository;

import com.solution.appsolute.entity.Mail;
import com.solution.appsolute.dto.mail.MailDto;
import com.solution.appsolute.dto.mail.MailListRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    @Query(value = "select * from Mail order by mailNum desc", nativeQuery = true)
    List<Mail> getListDesc();

    List<Mail> findByMailReceiverOrMailSender(Long receiver, Long sender);

    @Query(value = "select m.mailNum as mailNum, m.mailCheck as mailCheck, m.mailSender as mailSender, m.mailReceiver as mailReceiver, " +
            "e.empName as empName, m.mailTitle as mailTitle, m.mailContent as mailContent, m.mailDate as mailDate " +
            " from Mail m inner join Employee e on m.mailReceiver = e.empNum where m.mailSender = :mailSender order by m.mailNum desc")
    List<MailDto> findByMailSend(Long mailSender);

    @Query(value = "select m.mailNum as mailNum, m.mailCheck as mailCheck, m.mailSender as mailSender, m.mailReceiver as mailReceiver, " +
            "e.empName as empName, m.mailTitle as mailTitle, m.mailContent as mailContent, m.mailDate as mailDate " +
            " from Mail m inner join Employee e on m.mailSender = e.empNum where m.mailReceiver = :mailReceiver order by m.mailNum desc")
    List<MailDto> findByMailReceive(Long mailReceiver);


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

    int countBy(); // 메일 전체 개수 조회

    @Query("update Mail m set m.mailCheck = m.mailCheck + 1 where m.mailNum = :no")
    void increaseReadCount(Long no) ;

//    @Query(value = "select m.mailNum, m.mailCheck, m.mailSender, m.mailReceiver, e.empName, m.mailTitle, m.mailContent, m.mailDate" +
//            " from Mail m inner join Employee e on m.mailSender = e.empNum where m.mailSender = 1" +
//            " union" +
//            " select m.mailNum, m.mailCheck, m.mailSender, m.mailReceiver, e.empName, m.mailTitle, m.mailContent, m.mailDate" +
//            " from Mail m inner join Employee e on m.mailSender = e.empNum where m.mailReceiver = 1 ")
//    List<MailList> getList();



}
