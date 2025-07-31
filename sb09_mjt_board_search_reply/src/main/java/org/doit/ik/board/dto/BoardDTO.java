package org.doit.ik.board.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDTO {
	private Long bno;
	private String title;
	private String content;
	private String writerEmail; // 작성자 이메일 (ID)
	private String writerName;  // 작성자 이름
	
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private int replyCount; // 댓글 수
}
