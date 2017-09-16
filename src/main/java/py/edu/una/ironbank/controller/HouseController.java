package py.edu.una.ironbank.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.una.ironbank.entity.House;
import py.edu.una.ironbank.repository.HouseRepository;

@RestController
public class HouseController {

	public static final String GET_HOUSES = "/house";

	@Autowired
	private HouseRepository houseRepository;

	@RequestMapping(value = GET_HOUSES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<House>> getHouses() {
		List<House> houseList = (List<House>) houseRepository.findAll();
		
		if (houseList.isEmpty()) {
			System.out.println("No se encontraron datos");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<House>>(houseList, HttpStatus.OK);
	}

}
