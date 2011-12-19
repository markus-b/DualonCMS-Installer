package de.beepublished.client.widget;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UploadSourceWidget extends Composite {
	private Text inputFolderPath;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public UploadSourceWidget(Composite parent, int style) {
		super(parent, style);
		
		Group grpQuelle = new Group(this, SWT.NONE);
		grpQuelle.setText("Quelle");
		grpQuelle.setBounds(10, 10, 263, 54);
		
		inputFolderPath = new Text(grpQuelle, SWT.BORDER);
		inputFolderPath.setBounds(10, 20, 219, 21);
		
		Button buttonBrowseFolder = new Button(grpQuelle, SWT.NONE);
		buttonBrowseFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog fileDialog = new DirectoryDialog(getShell());
				fileDialog.setFilterPath(inputFolderPath.getText());
				fileDialog.setText("BeePublished Source");
				fileDialog.setMessage("Select the directory of the BeePublished Source");
				String selectedDirectory = fileDialog.open();
				if(selectedDirectory != null){
					inputFolderPath.setText(selectedDirectory);
				}
			}
		});
		buttonBrowseFolder.setBounds(232, 18, 21, 25);
		buttonBrowseFolder.setText("...");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public String getFolder(){
		return inputFolderPath.getText();
	}
}
