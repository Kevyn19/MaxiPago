package br.com.maxiPago.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maxiPago.ws.model.CitiesEntity;
import br.com.maxiPago.ws.repository.CitiesRepository;
import br.com.maxiPago.ws.utils.CitiesDistanceDTO;

/**
 * 
 * @author Kevyn miranda dos Santos -
 *
 *
 */
@Service
public class CitiesService {
	
	private static final int METRIC_KM = 6371;
	private static final int METRIC_MI = 3959;
	
	
	@Autowired
	CitiesRepository citiesRepository;
	
		
		/**
		 * Salva objeto CitiesEntity na base de dados.
		 * 
		 * Saves CitiesEntity object to the database.
		 * @param citiesEntity
		 * @return
		 */
		public CitiesEntity save(CitiesEntity citiesEntity) {
			return citiesRepository.insertCities(citiesEntity.getLatitude(), citiesEntity.getLongitude(), citiesEntity.getName());
		}
		
		/**
		 * Atualiza objeto CitiesEntity na base de dados.
		 * 
		 * Updates the CitiesEntity object in the database.
		 * @param citiesEntity
		 * @return
		 */
		public CitiesEntity update(CitiesEntity citiesEntity){
			return citiesRepository.updateCities(citiesEntity.getLatitude(), citiesEntity.getLongitude(), citiesEntity.getName(),citiesEntity.getIdCities());
		}
		
	
		
		/**
		 * Chama o método findByCoordinates para cada objeto CitiesEntity e retorna a lista de CitiesDistanceDTO.
		 * 
		 * Invokes the findByCoordinates method for each CitiesEntity object and returns the list of CitiesDistanceDTO.
		 * @param metric
		 * @return
		 */
		public List<CitiesDistanceDTO> findCities(String metric){
			List<CitiesEntity> cities = citiesRepository.findAllCities();
			
			List<CitiesDistanceDTO> result = new ArrayList<>();
			
			for (CitiesEntity citiesEntity : cities) {
				result.addAll(findByCoordinates(metric,citiesEntity.getLatitude(),citiesEntity.getLongitude(),citiesEntity.getName()));
			}
			
			return result;
		}
		
		/**
		 * Faz a busca de cidades por latitude e longitude.
		 * 
		 * Search for cities by latitude and longitude.
		 * @param metric
		 * @param latitude
		 * @param logitude
		 * @return
		 */
		public List<CitiesDistanceDTO> findByCoordinates(String metric, double latitude, double logitude, String firstCity) {
			
			List<Object> list = new ArrayList<>();
			
			
			if(metric.equalsIgnoreCase("KM")){
				list = citiesRepository.findByCoordinates(METRIC_KM,latitude,logitude);
			}else if(metric.equalsIgnoreCase("MI")){
				list = citiesRepository.findByCoordinates(METRIC_MI,latitude,logitude);
			}

			List<CitiesDistanceDTO> listaCoordenadas = new ArrayList<>();
			
			listaCoordenadas = populate(list,firstCity);
			
			return listaCoordenadas;
		}
		
		/**
		 * Popula a lita de CitiesDistanceDTO com o resultado da busca.
		 * 
		 * Add to CitiesDistanceDTO List with search result.
		 * @param list
		 * @return
		 */
		private List<CitiesDistanceDTO> populate(List<Object> list, String firstCity){
			List<CitiesDistanceDTO> aux = new ArrayList<>();
			
			Iterator itr = list.iterator();
			while(itr.hasNext()){
				
			   CitiesDistanceDTO temp = new CitiesDistanceDTO();
			   Object[] obj = (Object[]) itr.next();
			  
			   temp.setSecundCity(String.valueOf(obj[0]));
			   temp.setFirstCity(firstCity);
			   Double d = Double.parseDouble(String.valueOf(obj[1])); 
			   temp.setDistance(d);
			   temp.setApproximateDistance(d.intValue());
			   
			   aux.add(temp);
			
			}
			
			return aux;
		}
		
		/**
		 * Verifica se o registro existe na base de dados.
		 * 
		 * Checks whether the record exists in the database.
		 * @param id
		 * @return
		 */
		public boolean existInBase(Integer id){
			boolean result = false;
			CitiesEntity city = citiesRepository.findOne(id);
			
			if(city != null){
				result = true;
			}
			
			return result;
		}

}
