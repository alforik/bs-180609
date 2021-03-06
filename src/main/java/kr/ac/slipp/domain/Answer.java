package kr.ac.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;
	
	@ManyToOne  // 와우   질문-유저 관계
	@JoinColumn(foreignKey = @ForeignKey(name="fk_answer_writer") )
	@JsonProperty
	private User writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_answer_qna") )
	@JsonProperty
	private Qna qna;
	
	@Lob
	@JsonProperty
	private String contents;
	
	private LocalDateTime createDate;
	
	public Answer() {
		
	}
	
	public Answer(User writer, Qna qna, String contents) {
		this.writer = writer;
		this.qna = qna;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}
	public String getFormattedCreateDate() { // 출력하기 위해 함수를 하나 만들어둠.. 와우..
		
		if( createDate == null ) {
			return "";
		}
		
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + ", createDate=" + createDate
				+ "]";
	}


	public boolean isSameWriter(User user) {
		
		if( !this.writer.equals(user) ) {
			return false;
		}
		
		return true;
	}
	
}
