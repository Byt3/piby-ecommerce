package com.piby.boot_test.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.piby.boot_test.entities.Image;

public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
	
	public Image findByName(String name);
	
}
