package de.beepublished.client.http.webservice.management;

import de.beepublished.client.http.webservice.dao.REST_CMS_Installation_response;
import de.beepublished.client.http.webservice.services.ServiceException;



public interface WebServiceListener {
	public void onRestInstallationSuccess(REST_CMS_Installation_response response);
	public void onRestInstallationFailed(ServiceException e);

}
