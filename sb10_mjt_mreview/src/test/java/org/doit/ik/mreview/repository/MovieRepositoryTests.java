package org.doit.ik.mreview.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MovieRepositoryTests {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieImageRepository movieImageRepository;

	/*
	@Test
	void insertMovies() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			log.info("👌 MovieRepositoryTests.insertMovies()...");
			Movie movie = Movie.builder().title("movie..."+i).build();
			this.movieRepository.save(movie);

			int count = (int)(Math.random()*5)+1;

			for (int j = 0; j < count; j++) {
				MovieImage movieImage = MovieImage.builder()
						.uuid(UUID.randomUUID().toString())
						.movie(movie)
						.imgName("test"+j+".jpg")
						.build();
				this.movieImageRepository.save(movieImage);
			}   // for
		});
	}
	*/
	
	/*
	@Test
	void testGetListPage() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
		Page<Object[]> result = this.movieRepository.getListPage(pageable);
		
		for (Object[] objects : result.getContent()) {
	         System.out.println("😝 " + Arrays.toString(objects));
	      }
		
		result.getContent().forEach(record->{
	         System.out.println("🎬 결과: " + Arrays.toString(record));
	      });
	}
	*/
	
	@Test
	void testGetMovieWithAll() {
		
		List<Object[]> result = this.movieRepository.getMovieWithAll(93L);
		System.out.println("💕💕💕" + result);
		
		result.forEach(movie ->{
			System.out.println("💕💕💕" + movie);
		});
		
	}
	

} // class
