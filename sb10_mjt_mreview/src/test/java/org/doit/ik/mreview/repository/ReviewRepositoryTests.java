package org.doit.ik.mreview.repository;

import java.util.List;

import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class ReviewRepositoryTests {

	@Autowired
	private ReviewRepository reviewRepository;

	/*
	@Test
	void insertMovieReviews() {
		IntStream.rangeClosed(1, 200).forEach(i->{
			log.info("👌 ReviewRepositoryTests.insertMovieReviews()...");
			Long mno = (long)(Math.random()*100)+1;   // 영화 번호
			Long mid = (long)(Math.random()*100)+1;   // 리뷰어 번호
			Member member = Member.builder()
					.mid(mid)
					.build();
			Review review = Review.builder()
					.member(member)
					.movie(Movie.builder().mno(mno).build())
					.grade((int)(Math.random()*5)+1)
					.text("이 영화에 대한 느낌..."+i)
					.build();

			this.reviewRepository.save(review);
		});
	}
	*/
	
	// List<Review> findByMovie(Movie movie); 단위 테스트
	@Test
	@Transactional
	void testFindByMovie() {
		Movie movie = Movie.builder()
					.mno(93L)
					.build();
		List<Review> result = this.reviewRepository.findByMovie(movie);
		
		result.forEach(movieReviw->{
	         System.out.println("🎶" +movieReviw.getReviewnum());
	         System.out.println("\t"+movieReviw.getGrade());
	         System.out.println("\t"+movieReviw.getText());
	         // 아래 코딩이 문제 - EM 닫힘. 해결 :     @EntityGraph(attributePaths = {"member"}) 추가
	         System.out.println("\t"+movieReviw.getMember().getEmail());
	         System.out.println("-".repeat(70));
	      });
	}

}
