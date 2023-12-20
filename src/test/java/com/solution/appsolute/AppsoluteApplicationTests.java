package com.solution.appsolute;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.solution.appsolute.entity.QMail;
import com.solution.appsolute.mail.dao.repository.MailRepository;
import com.solution.appsolute.mail.dto.MailDto;
import com.solution.appsolute.mail.dto.MailPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class AppsoluteApplicationTests {

	@Autowired
	MailRepository mailRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void test(){
		Pageable pageable = PageRequest.of(0, 10);
		QMail qMail = QMail.mail;
		String keyword = "제목 1";
		BooleanBuilder builder = new BooleanBuilder();
		BooleanExpression expression = qMail.mailTitle.contains(keyword);
		builder.and(expression);
		Page<MailDto> result = mailRepository.findByMailSend(1L, pageable);
		result.stream().forEach(mailDto -> {
			System.out.println("==========================" + mailDto);
		});
	}
}
