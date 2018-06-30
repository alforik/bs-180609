package kr.ac.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Qna {
	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;
	
	@ManyToOne  // 와우   질문-유저 관계
	@JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer") )
	@JsonProperty
	private User writer;
	
	//private String writer;
	@JsonProperty
	private String title;
	
	@Lob
	@JsonProperty
	private String contents;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="qna")
	@OrderBy("id DESC")
	private List<Answer> answers;
	
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
	
	public void update(Qna newQna) {
		this.title = newQna.title;
		this.contents = newQna.contents;
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
		
	}

	public boolean isSameWriter(User loginUser) {
		
		return this.writer.equals(loginUser);
	}

		
	
}
