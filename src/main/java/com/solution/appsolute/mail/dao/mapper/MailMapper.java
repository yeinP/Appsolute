package com.solution.appsolute.mail.dao.mapper;

import com.solution.appsolute.mail.dto.MailList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailMapper {
    public List<MailList> listDao(Long id, int startRow, int size);

    public Integer countDao(Long id, int startRow, int size);

    public List<MailList> unreadDao(Long id, int startRow, int size);

    public Integer countUnreadDao(Long id);

    public int deleteById(int id);

    public List<MailList> findByMailReceiveLikeEmp(Long id, int startRow, int size, Long mailSender);

    public Integer countLikeEmp(Long id, Long mailSender);

    String findByMailLikeName(Long sender, Long receiver);
}
