package org.doit.ik;

import java.util.Iterator;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.service.GuestbookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuestbookServiceTests {
	
	@Autowired
	private GuestbookService guestbookService;
	
	/*
	@Test
	public void registerTest() {
		GuestbookDTO guestbookDTO = GuestbookDTO.builder()
									.title("새글")
									.content("새 내용")
									.writer("홍길동")
									.build();
		Long gno = this.guestbookService.register(guestbookDTO);
		System.out.println("👍👍"+gno + "글 등록!");
	}
	*/
	
	@Test
	public void listTest() {
		PageRequestDTO requestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO = this.guestbookService.getList(requestDTO);
		
		//List<GuestbookDTO>
		for (GuestbookDTO dto : resultDTO.getDtoList()) {
			System.out.println("👍👍"+ dto.getTitle());
		}
	}
	
	
}

















