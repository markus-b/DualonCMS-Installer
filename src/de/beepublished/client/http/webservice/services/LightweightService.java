package de.beepublished.client.http.webservice.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.http.message.BasicNameValuePair;






/**
 * Lightweight Service aka Rest
 * 
 *
 */
public abstract class LightweightService extends Service {
	
	//attributes
	protected List<BasicNameValuePair> methodKeyValuePairs = new ArrayList<BasicNameValuePair>();
	
	public LightweightService()
	{
		
	}
	
	/**
	 * @return Returns the service method name which is supplied in the HTTP POST header
	 */
	public abstract String getServiceMethodName();
	
	/**
	 * @return The method's key-value-pairs in form of a list
	 */
	public List<BasicNameValuePair> getServiceKeyValueList()
	{
		return methodKeyValuePairs;
	}
	
	public String getServiceValueByKey(String key) throws NoSuchElementException
	{
		Iterator<BasicNameValuePair> it = methodKeyValuePairs.iterator();
		while(it.hasNext())
		{
			BasicNameValuePair pair = it.next();
			if(pair.getName().equals(key))
			{
				return pair.getValue(); 
			}
		}
		throw(new NoSuchElementException("No such element for key " + key));
	}
	
}
