package de.beepublished.client.http.webservice.services;

import java.io.InputStream;

/**
 * Abstract class for handling content returned by web service calls
 * 
 * 
 */
public abstract class ResponseHandler{
	
	/**
	 * Implements the content parsing and mapping of a web service content response.
	 * 
	 * @param responseClass The class definition of the corresponding service
	 * @param stream The content input stream containing the service response data
	 * @return Returns a service response object representing the parsed content
	 * @throws ServiceException Exception with error type if content parsing fails
	 */
	public abstract ServiceResponse handleResponse(Class<?> responseClass, InputStream stream) throws ServiceException;
	
}
