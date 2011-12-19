package de.beepublished.client.http.webservice.services;

/**
 * Represents a response listener for service calls
 * 
 * 
 *
 */
public interface ResponseListener {
	
	/**
	 * Gets called when the response handler of the service has parsed the service content into a response object.
	 * 
	 * @param methodName The name of the  service called
	 * @param serviceResponse The response object representing the response content
	 */
	public void onResponseSuccessful(String methodName, ServiceResponse serviceResponse);
	
	/**
	 * Gets called when the retrieving of parsing of the service response content failed.
	 * 
	 * @param methodName The name of the  service called
	 * @param exception The  service exception containing the service error type
	 */
	public void onResponseFailed(String methodName, ServiceException exception);
}
