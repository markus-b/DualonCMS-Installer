package de.beepublished.client.ftp;

public class FTPLoginInformationImplementation implements FTPLoginInformation {

	private String host;
	private int port;
	private String userName;
	private String password;
	
	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public FTPLoginInformationImplementation(String host, int port,
			String userName, String password) {
		super();
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}

}
