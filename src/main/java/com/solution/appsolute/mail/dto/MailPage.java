package com.solution.appsolute.mail.dto;

import java.util.List;

// 문의 게시판 페이지 별 조회 클래스
public class MailPage {

	private int total;
	private int currentPage;
	private List<MailList> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	private Long id;
	
	public MailPage(int total, int currentPage, int size, List<MailList> content, Long id) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		this.id = id;

		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0) startPage -= 5;
			
			endPage = startPage + 4;
			if (endPage > totalPages) endPage = totalPages;
		}
		
		
	}
	
	public int getTotal() {
		return total;
	}

	public boolean hasNoArticles() {
		return total == 0;
	}

	public boolean hasArticles() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<MailList> getContent() {
		return content;
	}

	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}

	public Long getId() {return id;}
}
