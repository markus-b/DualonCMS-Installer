package de.beepublished.client.http.webservice.services;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import de.beepublished.client.http.webservice.services.ServiceException.ServiceErrorType;



/**
 * Response handler for lightweight services using the SIMPLE API.
 * 
 *
 */
public class LWResponseHandler extends ResponseHandler {

	public ServiceResponse handleResponse(Class<?> responseClass, InputStream stream) throws ServiceException
	{
		//read the xml content stream into a buffer
		Reader inputReader = new InputStreamReader(stream);
		StringBuilder inputBuffer = new StringBuilder();
		char[] readBuffer = new char[1024];
		int readCounter = 0;
		try
		{
			while((readCounter = inputReader.read(readBuffer)) >= 0)
			{
				inputBuffer.append(readBuffer, 0, readCounter);
			}
		}
		catch(IOException e)
		{
			throw(new ServiceException(ServiceErrorType.PARSING_ERROR, e));
		}
		//create a byte array input stream
	
		
		ByteArrayInputStream contentBIS = null;

		contentBIS = new ByteArrayInputStream(inputBuffer.toString().getBytes());

		//invoke the SIMPLE API to create an object mapping from XML to DAO
		Object responseObject = null;
		try 
		{
			responseObject = ServiceHandler.getSimplePersister().read(responseClass, contentBIS, false);
		} 
		catch(Exception e) 
		{
			
			try
			{
				System.out.println( e.getMessage() + "\n"+ inputBuffer.toString());
				ByteArrayInputStream errorBIS = new ByteArrayInputStream(inputBuffer.toString().substring(inputBuffer.toString().indexOf(">") + 1).getBytes());
				
			}
			catch(Exception ee)
			{
				//throw a parsing exception
				if(ee instanceof ServiceException)
				{
					throw((ServiceException)ee);
				}
				else
				{
					throw(new ServiceException(ServiceErrorType.PARSING_ERROR, ee));
				}
			}
		}
		
		return (ServiceResponse)responseObject;
	}

}
