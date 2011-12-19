package de.beepublished.client.http.webservice.services;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.simpleframework.xml.core.Persister;

import de.beepublished.client.http.webservice.services.ServiceException.ServiceErrorType;





//import org.simpleframework.xml.core.Persister;


public class ServiceHandler {
    
    //simple persister
   private static Persister simplePersister = null;
    
	//attributes
   	private HttpClient httpclient;
    private LWResponseHandler lwResponseHandler = null;
    private ArrayList<LightweightConnectionTask> lwTaskList = new ArrayList<LightweightConnectionTask>();
	
	public ServiceHandler(HttpClient httpclient)
	{
		//create the response handlers
		lwResponseHandler = new LWResponseHandler();
		this.httpclient = httpclient;
	}
	
	/**
	 * Starts an asynchronous webservice call.
	 * 
	 * @param service The service objecy which represents the request information data
	 * @param responseListener A response listener which is invoked when the reponse is available
	 * @throws ServiceException Thrown when a service error occurs
	 */
	public void processRequestAsynch(Service service, ResponseListener responseListener) throws ServiceException
	{
		//lightweight HTTP POST web services	
		if(service instanceof LightweightService)
		{
			//cast to lightweight service and handler
			LightweightService lwService = (LightweightService)service;
		
			//create an HTTP POST method and set the service information
		
			HttpPost postMethod = new HttpPost(lwService.getServiceURL());
			
			postMethod.setHeader("Content-Type","application/x-www-form-urlencoded");
	
			try 
			{	
				postMethod.setEntity(new UrlEncodedFormEntity(lwService.getServiceKeyValueList()));
			} 
			catch (UnsupportedEncodingException e)
			{
				throw(new ServiceException(ServiceErrorType.INVALID_URL_ENCODING, e));
			}
			//start request as an asynchronous task
			Thread thread = new Thread(new LightweightConnectionTask(this, lwService, lwResponseHandler, responseListener,postMethod, httpclient));
			thread.start();

		} 
		
	}
	
	/**
	 * @return The currently running connection tasks

	
	/**
	 * A lightweight connection task executes an HTTP POST method in a separate thred and returns 
	 * the response or exception object to the UI thread by the supplied listener.
	 *  
	 * 
	 *
	 */
	private class LightweightConnectionTask implements Runnable 
	{
		//attributes
		private ServiceHandler serviceHandler = null;
		private LightweightService lwService = null;
		private ResponseHandler responseHandler = null;
		private ResponseListener responseListener = null;
		private HttpPost post = null;
		private HttpClient httpclient;
		public LightweightConnectionTask(ServiceHandler serviceHandler, LightweightService lwService, LWResponseHandler responseHandler, ResponseListener responseListener,HttpPost post, HttpClient httpclient)
		{
			super();
			this.serviceHandler = serviceHandler;
			this.lwService = lwService;
			this.responseHandler = responseHandler;
			this.responseListener = responseListener;
			this.post = post;
			this.httpclient = httpclient;
			//add to the task list

		}

		/**
		 * Starts the network connection, get the data according to the POST method information 
		 * and parse them with the response handler.
		 */
		@Override
		public void run() {
			ServiceException se =  new ServiceException(ServiceErrorType.INVALID_HTTP_RESPONSE, new Exception("test"));
			
			try
			{
				//check the post method
				if(post == null || post.isAborted())
				{
					
				}
				//execute the request
				HttpResponse httpResponse = null;
				try
				{	
					httpResponse = httpclient.execute(post);
					
				}
				catch(NullPointerException e)
				{
					/*
					//retry. because of unknown problem provoking a NullPointerException lets recreate the httpClient 
					try
					{
						HttpClient httpClient = ((WebApplication)context.getApplicationContext()).recreateHttpClient();
						httpResponse = httpClient.execute(post[0]);
					}catch(NullPointerException e2){
						return new ServiceException(ServiceErrorType.ENTITY_IS_NULL, new Exception("NullPointerException within  doInBackground 2nd time"));
					}
					*/
				}
				if(httpResponse == null)
				{
					responseListener.onResponseFailed(lwService.getServiceMethodName(), se);
				
				}
				//handle the response content
				HttpEntity entity = httpResponse.getEntity();
				if(entity != null) {
					ServiceResponse serviceResponse = responseHandler.handleResponse(lwService.getResponseClass(), entity.getContent());				
					entity.consumeContent();
					
					if(responseListener != null)
					{
						//check the response type
						if(serviceResponse instanceof ServiceResponse)
						{
							responseListener.onResponseSuccessful(lwService.getServiceMethodName(), serviceResponse);
						}
							
					} else {
						responseListener.onResponseFailed(lwService.getServiceMethodName(), se);
					}
					//provide the response object for the UI thread
				}else{
					
				}
			}
			catch(ClientProtocolException e) 
			{
				//protocol error occurred

				responseListener.onResponseFailed(lwService.getServiceMethodName(), se);
			}
			catch(IOException e) 
			{
				if(e instanceof UnknownHostException)
				{
					//connection to host is not resolved

					responseListener.onResponseFailed(lwService.getServiceMethodName(), se);
				}
				else
				{
					//some other content errors

					responseListener.onResponseFailed(lwService.getServiceMethodName(), se);
				
			}}
			catch(ServiceException e)
			{
				//here redirect parsing errors

				responseListener.onResponseFailed(lwService.getServiceMethodName(), se);
			}
			
		}	

		public LightweightService getLwService() {
			return lwService;
		}

		public ResponseHandler getResponseHandler() {
			return responseHandler;
		}

		public ResponseListener getResponseListener() {
			return responseListener;
		}

	}//LW Connection Thread
	
	/**
	 * @return Returns the persister from the SIMPLE API
	 */
	public static Persister getSimplePersister()
	{
		if(simplePersister == null)
		{
			simplePersister = new Persister();
		}
		return simplePersister;
	}
	
}
