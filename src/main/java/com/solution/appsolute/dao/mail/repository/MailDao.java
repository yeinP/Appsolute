package com.solution.appsolute.dao.mail.repository;

import com.solution.appsolute.dto.mail.MailList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailDao {
    public List<MailList> listDao(Long id, int startRow, int size);



}
