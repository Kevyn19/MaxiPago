package br.com.maxiPago.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.maxiPago.ws.model.CitiesEntity;

/**
 * 
 * @author Kevyn miranda dos Santos -
 *
 * Classe responsavel por manipular o acesso ao banco de dados.
 *
 * Class responsible for manipulating database access.
 */
@Repository
public interface CitiesRepository extends JpaRepository<CitiesEntity, Integer> {
	
	@Query(value="SELECT name, "+
				 " (?1 * acos( "+
				 " cos( radians(?2) ) "+
				 " * cos( radians( latitude ) ) "+
				 " * cos( radians( longitude ) - radians(?3) ) "+
				 " + sin( radians(?2) ) "+
				 " * sin( radians(latitude) ) "+ 
				 " ) "+
				" ) AS distance "+
				 " FROM cities_entity  "+
				 " HAVING distance > 0.0 "+
				 " ORDER BY distance ASC ",nativeQuery = true)
	public List<Object> findByCoordinates(@Param("met") int metric,@Param("lat") double latitude,@Param("lng") double logitude);
	
	@Query(value="SELECT * FROM cities_entity ",nativeQuery = true)
	public List<CitiesEntity> findAllCities();
	
	
	@Query(value="insert into cities_entity(latitude,longitude,name) values(?1,?2,?3); ",nativeQuery = true)
	public CitiesEntity insertCities(@Param("lat") double latitude,@Param("lng") double logitude, @Param("name") String name);
	
	@Query(value="update cities_entity set latitude = ?1, longitude = ?2, name = ?3 where id_cities = ?4 ",nativeQuery = true)
	public CitiesEntity updateCities(@Param("lat") double latitude,@Param("lng") double logitude, @Param("name") String name,@Param("id") int id);
	

}
