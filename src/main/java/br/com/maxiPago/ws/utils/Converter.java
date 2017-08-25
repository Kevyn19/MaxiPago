package br.com.maxiPago.ws.utils;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * 
 * @author Kevyn miranda dos Santos -
 *
 * Classe responsavel por converter string em xml 
 *
 *Class responsible for converting string to xml
 */
public class Converter {
	
	/**
	 * Parse da uma lista de CitiesDistanceDTO para XML
	 * 
	 * Parse of a List of CitiesDistanceDTO for XML
	 * @param list
	 * @return
	 * @throws JAXBException
	 */
	public static String parseXml(List<CitiesDistanceDTO> list) throws JAXBException{
		
		String xmlString = "";
		
		JAXBContext context = JAXBContext.newInstance(CitiesDistanceDTO.class);
        Marshaller m = context.createMarshaller();

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

        StringWriter sw = new StringWriter();
        for (CitiesDistanceDTO citiesDistanceDTO : list) {
        	  m.marshal(citiesDistanceDTO, sw);
              xmlString  = sw.toString();
		}
        
        return xmlString;
		
	}

}
