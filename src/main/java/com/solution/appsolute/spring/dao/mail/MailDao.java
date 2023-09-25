package com.solution.appsolute.spring.dao.mail;

import com.solution.appsolute.spring.dto.MailDto;
import com.solution.appsolute.spring.dto.MailDto;
import com.solution.appsolute.spring.dto.MailList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailDao {
    public List<MailList> listDao(Long id);

    public List<MailDto> listAll();

    public int count();
}
