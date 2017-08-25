package br.com.maxiPago.ws.utils;

import br.com.maxiPago.ws.model.CitiesEntity;

/**
 * 
 * @author Kevyn miranda dos Santos -
 *
 * Classe responsavel por validar o objeto city
 *
 * Class responsible for validating the city object
 */
public class Validator {

	/**
	 * Verifica se o objeto é valido.
	 * 
	 * Checks whether the object is valid.
	 * @param city
	 * @return
	 */
	public static boolean isCityValid(CitiesEntity city){
		boolean result = false;
		
		if(city != null && (city.getName() != null && !city.getName().equals("")) && city.getLatitude() != 0.0 && city.getLongitude() != 0.0){
			result = true;
		}
		
		return result;
	}
}
