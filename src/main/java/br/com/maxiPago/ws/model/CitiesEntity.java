package br.com.maxiPago.ws.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author Kevyn miranda dos Santos -
 *
 *Entidade que representa a tabela cities_entity.
 *
 *Entity representing the cities_entity table.
 */
@Entity
public class CitiesEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer IdCities;
	
	private String name;

	private double longitude;
	private double latitude;
	
	public Integer getIdCities() {
		return IdCities;
	}
	public void setIdCities(Integer idCities) {
		IdCities = idCities;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
