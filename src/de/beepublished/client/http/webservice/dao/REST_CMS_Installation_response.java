package de.beepublished.client.http.webservice.dao;


import org.simpleframework.xml.Element;

import de.beepublished.client.http.webservice.services.ServiceResponse;





public class  REST_CMS_Installation_response extends ServiceResponse {

	public REST_CMS_Installation_response () {
	}
	//attributes
	@Element(name="RC", required =false)
	protected int responseCode;
	
	
	/**
	 * Checks for the response code of the web service response
	 * @return true, if response is valid otherwise false
	 */
/*	public boolean isResponseValid()
	{
		return responseCode == 0 ;
	}
	
	/**
	 * @return Returns the service response code
	 */
/*	public int getResponseCode()
	{
		return responseCode;
	}*/
	
}
