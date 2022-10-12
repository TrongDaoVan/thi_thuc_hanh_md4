package com.example.thi.repository;

import com.example.thi.model.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends PagingAndSortingRepository<City, Long> {
//    City findCityById (Long id);
}
