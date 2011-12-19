package de.beepublished.client.http.webservice.interfaces;

import de.beepublished.client.http.webservice.dao.REST_CMS_Installation_response;
import de.beepublished.client.http.webservice.management.WebServiceListener;
import de.beepublished.client.http.webservice.services.ServiceException;


public class RestWebServiceListener implements WebServiceListener {

	public RestWebServiceListener() {
		super();

	}
	@Override
	public void onRestInstallationSuccess(
			REST_CMS_Installation_response response) {
		System.out.println("Erfolg");
		
	}

	@Override
	public void onRestInstallationFailed(ServiceException e) {
		System.out.println("Fail2");
		
	}

}
