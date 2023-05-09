package com.tenco.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 100)
	private String title;
	@Lob // 대용량 데이터
	private String content;

	@ColumnDefault("0") // 숫자형은 - "숫자"
	private int count; // 조회수
	
	// FK는 포함관계로
	// Board : User = N : 1
	@ManyToOne   // 기준 정확하게
	@JoinColumn(name = "user_id") // User의 PK를 참조함
	private User user;
	
	@CreationTimestamp // 자동으로 now()
	private Timestamp createdDate;
	
	// Board에는 reply에 대한 정보가 없음
	// Board 정보를 가져올 때 댓글 정보도 가져와야 한다면
	// Board와 Reply의 관계는 1:N
	
	// @OneToMany // 설정하니까 board_reply이라는 테이블이 생성됨 -> 1:N관계이므로 중간 테이블이 필요 없으니, 테이블을 생성하지 못하도록 해야 함
	// 주의 : board 테이블에는 reply_id 컬럼이 필요 없음 => mappedBy를 설정해주면 reply_id 컬럼이 생성되지 않음
	// 오브젝트가 생성될 때 (데이터를 가져올 떄) 알아서 join 처리해서 데이터만 가지고 와라.	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // join 전략 : FetchType.EAGER : 데이터를 다 가져옴 (기본값), FetchType.LAGE : 이름만 가져옴
	private List<Reply> reply;
	
	
	
}
