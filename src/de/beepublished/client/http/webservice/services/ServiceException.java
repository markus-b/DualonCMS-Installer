package de.beepublished.client.http.webservice.services;



/**
 * Service exceptions for web service communication
 * 
 * 
 *
 */
public class ServiceException extends Exception {
	
	/**
	 * Type of error enumeration
	 */
	public enum ServiceErrorType
	{
		INVALID_URL_ENCODING,//invalid encoded HTTP POST form information
		HOST_CONNECTION_UNRESOLVED,//unknown service hostname or unavailable host connection
		INVALID_HTTP_RESPONSE,//the server response is invalid
		INVALID_PROTOCOL,//an protocol exception occurred
		INVALID_RETURNCODE,//the service return code is not 0
		PARSING_ERROR,//error in parsing the content
		WEBSERVICE_ERROR,//a web service error occurred, the inner text of the error message is available
		ENTITY_IS_NULL,//HTTP response entity is null
	}
	
	//attributes
	protected ServiceErrorType errorType;
	protected int returnCode = -1;
	
	public ServiceException(ServiceErrorType errorType, Throwable e)
	{
		super(e);
		this.errorType = errorType;
	}
	
	public ServiceException(int serviceReturnCode)
	{
		this(ServiceErrorType.INVALID_RETURNCODE, new Exception("Invalid web service return code."));
		this.returnCode = serviceReturnCode;
	}
	
	/**
	 * @return Returns the error type of the Apollo service
	 */
	public ServiceErrorType getErrorType()
	{
		return errorType;
	}
	
	/**
	 * return Returns the return code of the service call. Use only if error type equals INVALID_SERVICE_RETURNCODE
	 */
	public int getReturnCode()
	{
		return returnCode;
	}
	
}
