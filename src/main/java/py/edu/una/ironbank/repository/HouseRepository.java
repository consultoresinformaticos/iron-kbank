package py.edu.una.ironbank.repository;

import org.springframework.data.repository.CrudRepository;

import py.edu.una.ironbank.entity.House;

public interface HouseRepository extends CrudRepository<House, Integer> {
	
}