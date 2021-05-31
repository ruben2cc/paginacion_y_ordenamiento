package com.benito.inheritance.pagingAndSorting.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.benito.inheritance.pagingAndSorting.entities.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	public List<Product> findByIdIn(List<Integer> ids, Pageable pageable);
}
