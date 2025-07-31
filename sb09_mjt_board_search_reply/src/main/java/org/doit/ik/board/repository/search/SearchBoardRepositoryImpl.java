package org.doit.ik.board.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.doit.ik.board.entry.Board;
import org.doit.ik.board.entry.QBoard;
import org.doit.ik.board.entry.QMember;
import org.doit.ik.board.entry.QReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

	public SearchBoardRepositoryImpl() {
		super(Board.class);
	}

	// QuerydslRepositorySupport 클래스 상속
	// Board, Reply, Member 엔티티를 
	// 1) 조인 2) 검색 3) 페이징 처리한 결과
	@Override
	public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
		log.info("😊 SearchBoardRepositoryImpl.searchPage()...");   

		// Querydsl에서 사용하는 [Q타입 객체]를 먼저 생성.
		// d/t [Q타입 객체]는 해당 클래스의 메타정보를 가지고 있기에 
		// 쿼리를 작성할 때 컬럼에 접근 가능.
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;

		// 2.JPQLQuery 객체 생성  - 복잡한 쿼리 만들때 만들어야 하는 객체.
		// 객체로 쿼리를 작성
		// 객체생성 <- from() 메서드
		// from(QBoard board) 쿼리 작성 시작. board 라는 Q타입 객체를 기준으로 
		// from board
		JPQLQuery<Board> jpqlQuery = from(board);
		
		// 3. Member 라는 Q타입 객체를 조인 + 조인조건
		// 4. Board 엔티티 + Member엔티티 => 연관관계
		// @ManyToOne (fetch = FetchType.LAZY) 연관관계 없으면 조인 못함
		// private Member writer;
		// left join member on b.writer = m.writer
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

		// Tuple  select()   select board, member, reply.count()
		// Tuple - 다양한 타입의 값을 한 행에 담기 위해서 사용되는 Querydsl의 결과 타입
		// 		   다양한 타입의 값을 저장하기 위한 컨테이너
		JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());
		// select ***
		// from board
		// left join member on
		// left join reply on
		// where bno > 0 and title like'%%' ~

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		// bno > 0
		BooleanExpression booleanExpression = board.bno.gt(0L);

		booleanBuilder.and(booleanExpression);

		if ( type != null) {
			String [] typeArr = type.split("");
			BooleanBuilder conditionBuilder = new BooleanBuilder();
			for (String t : typeArr) {
				switch (t) {
				case "t":  
					conditionBuilder.or(board.title.contains(keyword));
					break; 
				case "c":  
					conditionBuilder.or(board.content.contains(keyword));
					break; 
				case "w":  
					conditionBuilder.or(member.email.contains(keyword));
					break;
				} // switch
			} // for
			booleanBuilder.and(conditionBuilder);
		} // if

		tuple.where(booleanBuilder);
		tuple.groupBy(board);

		// 페이징 처리 추가
		// QuerydslRepositorySupport.getQuerydsl().applyPagination() 
		this.getQuerydsl().applyPagination(pageable, tuple);

		// fetch() 메소드 호출 - 쿼리 시행해서 결과를 반환하는 메소드
		// List<Tuple> 타입으로 리턴
		List<Tuple> result = tuple.fetch();         
		log.info(result);

		// fetchCount() - 쿼리 실행 후 총 레코드 수 반환 메서드
		long count = tuple.fetchCount();
		log.info("COUNT : " + count);

		// List<Tuple> - Page<object[]> 변환
		// [PageImpl] implements Page
		// public PageImpl(List<T> content, Pageable pageable, long total)
		return new PageImpl<Object[]>(
				result.stream().map(Tuple::toArray).collect(Collectors.toList())
				, pageable
				, count
				);
	}

}
