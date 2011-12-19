package de.beepublished.client.widget;
import org.eclipse.swt.widgets.Composite;

import de.beepublished.client.ftp.FTPLoginInformation;
import de.beepublished.client.ftp.FTPLoginInformationImplementation;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;


public class FTPLoginInformationWidget extends Composite {
	private Text inputHost;
	private Text inputPassword;
	private Text inputPort;
	private Text inputUserName;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FTPLoginInformationWidget(Composite parent, int style) {
		super(parent, style);
		
		Group grpFtpLoginInformation = new Group(this, SWT.NONE);
		grpFtpLoginInformation.setText("FTP Login Information");
		grpFtpLoginInformation.setBounds(10, 10, 172, 137);
		
		inputHost = new Text(grpFtpLoginInformation, SWT.BORDER);
		inputHost.setBounds(82, 22, 76, 21);
		
		inputPort = new Text(grpFtpLoginInformation, SWT.BORDER);
		inputPort.setBounds(82, 49, 76, 21);
		
		inputUserName = new Text(grpFtpLoginInformation, SWT.BORDER);
		inputUserName.setBounds(82, 76, 76, 21);
		
		inputPassword = new Text(grpFtpLoginInformation, SWT.BORDER);
		inputPassword.setBounds(82, 103, 76, 21);
		
		Label lblHost = new Label(grpFtpLoginInformation, SWT.RIGHT);
		lblHost.setBounds(21, 25, 55, 15);
		lblHost.setText("Host");
		
		Label lblPassword = new Label(grpFtpLoginInformation, SWT.RIGHT);
		lblPassword.setText("Password");
		lblPassword.setBounds(21, 106, 55, 15);
		
		Label lblPort = new Label(grpFtpLoginInformation, SWT.RIGHT);
		lblPort.setText("Port");
		lblPort.setBounds(21, 52, 55, 15);
		
		Label lblUsername = new Label(grpFtpLoginInformation, SWT.RIGHT);
		lblUsername.setText("UserName");
		lblUsername.setBounds(21, 79, 55, 15);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public FTPLoginInformation getLoginInformation(){
		// TODO create method description
		// TODO create test case
		return new FTPLoginInformationImplementation(inputHost.getText(),Integer.parseInt(inputPort.getText()),inputUserName.getText(),inputPassword.getText());
	}
}
