package py.edu.una.ironbank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.una.ironbank.entity.House;
import py.edu.una.ironbank.repository.HouseRepository;

@RestController
public class HouseController {

	public static final String GET_HOUSES = "/house";
	public static final String ADD_HOUSE = "/house/add";
	public static final String DELETE_HOUSE = "/house/delete/{id}";
	public static final String UPDATE_HOUSE = "/house/update";

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
	
	
	@RequestMapping(value = ADD_HOUSE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ResponseEntity<House> addHouse(@Valid @RequestBody(required = true) House house) {
		house = houseRepository.save(house);
		return new ResponseEntity<House>(house, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = UPDATE_HOUSE, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ResponseEntity<House> updateHouse(@RequestBody(required = true) House house) {
		System.out.println(""+ house);

		if(houseRepository.exists(house.getId())){
			house = houseRepository.save(house);
			return new ResponseEntity<House>(house, HttpStatus.OK);
		} else{
			return new ResponseEntity<House>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	@RequestMapping(value = DELETE_HOUSE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ResponseEntity<House> deleteHouse(@PathVariable("id") int id) {
		try {
			House house = houseRepository.findOne(id);
			if(house == null){
				return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
			} else{
				houseRepository.delete(house);
				return new ResponseEntity<House>(HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<House>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	

}
