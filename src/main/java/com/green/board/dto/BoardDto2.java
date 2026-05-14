package com.green.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data // Getter,Setter,toString,HashCode,equals,constructor
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든인자생성자
public class BoardDto2 {
	 private int idx;
	 private String menu_id;
	 private String title;
	 private String content;
	 private String writer;
	 private String regdate;
	 private int hit;
}

