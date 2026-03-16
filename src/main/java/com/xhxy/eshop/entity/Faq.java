package com.xhxy.eshop.entity;
/**
 * 实体类：常见问题
 *
 */
public class Faq {
	private Integer id;
	private String question;	// 问题
	private String answer;		// 回答
	
	// ----- getter/setter ------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
