package br.com.maxiPago.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.maxiPago.ws.model.CitiesEntity;
import br.com.maxiPago.ws.service.CitiesService;
import br.com.maxiPago.ws.utils.CitiesDistanceDTO;
import br.com.maxiPago.ws.utils.Converter;
import br.com.maxiPago.ws.utils.Validator;

/**
 * 
 * @author Kevyn miranda dos Santos -
 * 
 * Classe responsavel por expor os serviços. 
 * 
 * Class responsible for exposing services.
 */
@RestController
@RequestMapping("/Cities")
public class CitiesController {

	@Autowired
	CitiesService citiesService;

	
	/**
	 * Método responsavel por cadastrar novas cidades.
	 * 
	 * Method responsible for registering new cities.
	 * @param city
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<CitiesEntity> saveCitites(@RequestBody CitiesEntity city){

		CitiesEntity result = new CitiesEntity();
		if(Validator.isCityValid(city)){
			
			result = citiesService.save(city);
			
			return new ResponseEntity<>(result,HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Metodo responsavel por buscar a lista de cidades e a distancia entre elas - XML.
	 * 
	 * Method responsible for searching the list of cities and the distance between them - XML.
	 * @param metric
	 * @param lat
	 * @param lng
	 * @return
	 * @throws JAXBException 
	 */
	@RequestMapping(method = RequestMethod.GET, value="/xml/{metric}", produces = { "application/json", "application/xml" }, consumes = MediaType.ALL_VALUE )
	private ResponseEntity<String> findCitiesXml(@PathVariable String metric) throws JAXBException {
		
		List<CitiesDistanceDTO> resultSearch = new ArrayList<>();
		String xmlString = "";
		
		if(metric != null && !metric.equals("")){
			resultSearch = citiesService.findCities(metric);
			
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		xmlString = Converter.parseXml(resultSearch);
		return new ResponseEntity<>(xmlString, HttpStatus.OK);
	}
	
	/**
	 * Metodo responsavel por buscar a lista de cidades e a distancia entre elas - Json.
	 * 
	 * Method responsible for searching the list of cities and the distance between them - Json.
	 * @param metric
	 * @param lat
	 * @param lng
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/json/{metric}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<CitiesDistanceDTO>> findCitiesJson(@PathVariable String metric){
		
		List<CitiesDistanceDTO> result = new ArrayList<>();
		
		if(metric != null && !metric.equals("")){
			result = citiesService.findCities(metric);
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	/**
	 * Método responsavel por alterar  cidades.
	 * 
	 * Method responsible for update  cities.
	 * @param city
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<CitiesEntity> updateCitites(@RequestBody CitiesEntity city) {

		CitiesEntity result = new CitiesEntity();
		if(Validator.isCityValid(city)){
			if(citiesService.existInBase(city.getIdCities())){
				result = citiesService.save(city);
				return new ResponseEntity<>(result,HttpStatus.CREATED);
			}else{
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
