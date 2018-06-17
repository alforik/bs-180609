package kr.ac.slipp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Qna {
	@Id
	@GeneratedValue
	private Long id;
	
	private String writer;
	private String title;
	private String contents;
	
	public Qna() {};
	
	public Qna(String writer, String title, String contents) {

		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
