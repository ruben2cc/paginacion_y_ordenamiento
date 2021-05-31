package com.benito.inheritance.pagingAndSorting;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.benito.inheritance.pagingAndSorting.entities.Product;
import com.benito.inheritance.pagingAndSorting.repos.ProductRepository;

@SpringBootTest
class PagingAndSortingApplicationTests {

	@Autowired
	private ProductRepository repo;
	
	@Test
	void testFindAllPaging() {
		
		PageRequest pageable=PageRequest.of(0, 2);
		Page<Product> page = repo.findAll(pageable);
		
		page.forEach(p -> System.out.println(p));
	}
	
	@Test
	void testFindAllSorting() {
		repo.findAll(Sort.by(Direction.ASC, "name")).forEach(p -> System.out.println(p));
	}
	
	@Test
	void testFindAllSortingByMultipleProperties() {
		repo.findAll(Sort.by(Direction.DESC, "name", "price")).forEach(p -> System.out.println(p));
	}
	
	@Test
	void testFindAllSortingByPairsPropertyDirection() {
		
		Iterable<Product> iterable = repo.findAll(Sort.by(new Sort.Order(Direction.DESC, "name"), new Sort.Order(Direction.DESC, "price")));
		iterable.forEach(p -> System.out.println(p));
		
	}
	
	@Test
	void testPagingAndSorting() {
		
		PageRequest pageable=PageRequest.of(0, 2, Direction.DESC, "price");
		Page<Product> page = repo.findAll(pageable);
		page.forEach(p -> System.out.println(p));
	}
	
	@Test
	void testfindbyIds() {
		PageRequest page = PageRequest.of(0, 2, Sort.by(Direction.DESC, "price"));
		List<Product> list = repo.findByIdIn(Arrays.asList(1,2,3), page);
		list.forEach(p -> System.out.println(p));
	}
	
}
