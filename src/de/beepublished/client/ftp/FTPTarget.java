package de.beepublished.client.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;


//TODO create class description
public class FTPTarget {
	
	private FTPLoginInformation loginInformation;
	private FTPClient ftpClient;
	
	public FTPTarget(FTPLoginInformation loginInformation){
		// TODO create method description
		// TODO create test case
		// TODO implement method
		
		this.loginInformation = loginInformation;
	}
	
	public void connect() throws SocketException, IOException{
		// TODO create method description
		// TODO create test case
		// TODO implement method
		
		ftpClient = new FTPClient();
		ftpClient.connect(loginInformation.getHost(), loginInformation.getPort());
		ftpClient.login(loginInformation.getUserName(), loginInformation.getPassword());
		
		//throw new RuntimeException("Not yet implemented!");
	}
	
	public void disconnect() throws IOException{
		// TODO create method description
		// TODO create test case
		// TODO implement method
		ftpClient.disconnect();
	}
	
	public void uploadFile(String localFilePath, String remoteFilePath) throws FileNotFoundException, IOException{
		assert(ftpClient.isConnected());
				
		ftpClient.storeFile(remoteFilePath, new FileInputStream(localFilePath));
		
		// TODO create method description
		// TODO create test case
		// TODO implement method
		//throw new RuntimeException("Not yet implemented!");
	}
	
	public void uploadFolder(String localFolderPath, String remoteFolderPath) throws FileNotFoundException, IOException{

		// TODO create method description
		// TODO create test case
		// TODO implement method
		
		File folder = new File(localFolderPath);
		assert(folder.isDirectory());
		
		ftpClient.makeDirectory(folder.getName());
		ftpClient.changeWorkingDirectory(folder.getName());
		
		for(File f : folder.listFiles()){
			if(f.isFile()){
				this.uploadFile(f.getAbsolutePath(), f.getName());
			}
			if(f.isDirectory()){
				this.uploadFolder(f.getAbsolutePath(), f.getAbsolutePath());
				ftpClient.changeToParentDirectory();
			}	
		}

	//	throw new RuntimeException("Not yet implemented!");
	}
	
	public boolean isConnected(){
		return ftpClient.isConnected();
	}
	
	public boolean containsFile(String filename) throws IOException{
		// TODO create method description
		// TODO create test case
		// TODO implement method
		
		// ACHTUNG, nur level 0!
		for(FTPFile file : ftpClient.listFiles()){
			if(file.getName().equals(filename))
				return true;
		}
		return false;
	}
	
	public void deleteFile(String filename) throws IOException{
		// TODO create method description
		// TODO create test case
		// TODO implement method
		ftpClient.deleteFile(filename);
	}

}
