package de.beepublished.client.unittest;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.SocketException;

import org.junit.Test;

import de.beepublished.client.ftp.FTPLoginInformation;
import de.beepublished.client.ftp.FTPTarget;


public class FTPClient {

	private static FTPLoginInformation login = new FTPLoginInformation() {
		
		@Override
		public String getUserName() {
			return "f005e804";
		}
		
		@Override
		public int getPort() {
			return 21;
		}
		
		@Override
		public String getPassword() {
			return "XWNKyuBQqKgKrTmc";
		}
		
		@Override
		public String getHost() {
			return "brickit-mod.de";
		}
	};
	
	@Test
	public void loginTest() throws SocketException, IOException {
		FTPTarget target = new FTPTarget(login);
		
		target.connect();
		assertEquals(true, target.isConnected());
		
		target.disconnect();
		assertEquals(false, target.isConnected());
	}
	
	@Test
	public void uploadAndDeleteTest() throws SocketException, IOException {
		FTPTarget target = new FTPTarget(login);
		target.connect();
		
		assertFalse(target.containsFile("testfile.txt"));
		
		target.uploadFile("FilesUnittest/FileUpload/testfile.txt", "testfile.txt");
		assertTrue(target.containsFile("testfile.txt"));
		
		target.deleteFile("testfile.txt");
		assertFalse(target.containsFile("testfile.txt"));
		
		target.disconnect();
	}
	
	@Test
	public void uploadFolder() throws SocketException, IOException {
		FTPTarget target = new FTPTarget(login);
		target.connect();
		
		target.uploadFolder("FilesUnittest/FolderUpload/UploadFolderTest/", "/");
	}

}
