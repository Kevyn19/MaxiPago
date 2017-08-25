package br.com.maxiPago.ws.utils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Kevyn miranda dos Santos -
 * 
 * DTO que representa o retorno das buscas - City.
 *
 * DTO representing return on searches - City.
 */
@XmlRootElement(name = "Cities")
public class CitiesDistanceDTO {


	private String firstCity;
	

	private String SecundCity;
	

	private Double distance;
	

	private int approximateDistance;

	
	public String getFirstCity() {
		return firstCity;
	}

	public void setFirstCity(String firstCity) {
		this.firstCity = firstCity;
	}

	public String getSecundCity() {
		return SecundCity;
	}

	public void setSecundCity(String secundCity) {
		SecundCity = secundCity;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public int getApproximateDistance() {
		return approximateDistance;
	}

	public void setApproximateDistance(int approximateDistance) {
		this.approximateDistance = approximateDistance;
	}
	
	
}
