package de.beepublished.client.http.webservice.services;

/**
 * Represents an web service
 * 
 * 
 */
public abstract class Service {
	
	/**
	 * @return Returns the response class according to the service
	 */
	public abstract Class<? extends ServiceResponse> getResponseClass();
	protected String serviceURL;
	public String getServiceURL(){
		return serviceURL;
	}
}
