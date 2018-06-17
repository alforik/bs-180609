package kr.ac.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Qna {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne  // 와우   질문-유저 관계
	@JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer") )
	private User writer;
	
	//private String writer;
	private String title;
	private String contents;
	
	private LocalDateTime createDate;
	
	public Qna() {};
	
	public Qna(User writer, String title, String contents) {

		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}
	public String getFormattedCreateDate() { // 출력하기 위해 함수를 하나 만들어둠.. 와우..
		
		if( createDate == null ) {
			return "";
		}
		
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
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