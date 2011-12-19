package de.beepublished.client.http.webservice.management;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;

import de.beepublished.client.http.webservice.dao.REST_CMS_Installation;
import de.beepublished.client.http.webservice.dao.REST_CMS_Installation_response;
import de.beepublished.client.http.webservice.interfaces.RestWebServiceListener;
import de.beepublished.client.http.webservice.services.ResponseListener;
import de.beepublished.client.http.webservice.services.ServiceException;
import de.beepublished.client.http.webservice.services.ServiceHandler;
import de.beepublished.client.http.webservice.services.ServiceResponse;




public class WebManager {
		
	private long userid;
	
	ServiceHandler handler;
	public WebManager(ServiceHandler handler) {
		this.handler = handler;
	}
	public void installCMS(String dBHost, String dBName, String dBPw, String dBLogin, final WebServiceListener listener){
		ResponseListener responseListener = new ResponseListener(){


			@Override
			public void onResponseFailed(String methodName,
					ServiceException exception) {
				listener.onRestInstallationFailed(exception);
			}

			@Override
			public void onResponseSuccessful(String methodName,
					ServiceResponse serviceResponse) {
				listener.onRestInstallationSuccess((REST_CMS_Installation_response)serviceResponse);
				
			}
			
		};
		
		try {
			handler.processRequestAsynch(new REST_CMS_Installation(dBHost, dBName, dBPw, dBLogin, "http://www.ms-mediagroup.de/Installation/") , responseListener);
		} catch (ServiceException e) {
			
		}
	}
	
	public static void main(String args[]){
	
		HttpClient hclient = createHttpClient();
		ServiceHandler shandler = new ServiceHandler(hclient);
		WebManager wmanager = new WebManager(shandler);
		wmanager.installCMS("test", "test", "test", "test", new RestWebServiceListener());
	}
	
	private static HttpClient createHttpClient() {
		// TODO Auto-generated method stub
		//set proxy if available
		/*if(proxyHostname != null)
		{
			proxyHttpHost = new HttpHost(proxyHostname, proxyPort);
		}
 */
		HttpClient httpClient;
		//create an HTTP scheme
		SchemeRegistry schemeReg = new SchemeRegistry();
		schemeReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		//create a thread-safe HTTP connection manager and client
		ThreadSafeClientConnManager connMgr = new ThreadSafeClientConnManager(new BasicHttpParams(), schemeReg);
		//create the HTTP client out of the thread-safe connection manager and set the proxy
		httpClient = new DefaultHttpClient(connMgr, new BasicHttpParams());
	/*	if(proxyHttpHost != null)
		{
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHttpHost);
		}*/
		
		return httpClient;
	}
	
}