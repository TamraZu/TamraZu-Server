package goormton.tamrazu.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import goormton.tamrazu.server.domain.Alcohol;
import goormton.tamrazu.server.domain.Category;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {
	List<Alcohol> findTop3ByOrderByAteCountDesc();
	List<Alcohol> findByCategory(Category category);
}
