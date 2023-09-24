package com.solution.appsolute.repository;

import com.solution.appsolute.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    @Query(value = "select * from Mail order by mailNum desc", nativeQuery = true)
    List<Mail> getListDesc();

    List<Mail> findByMailReceiverOrMailSender(Long Receiver, Long Sender);
}
