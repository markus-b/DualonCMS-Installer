package de.beepublished.client.widget;
import org.eclipse.swt.widgets.Composite;

import de.beepublished.client.db.DBLoginInformation;


//TODO create class description
public class DBLoginInformationWidget extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DBLoginInformationWidget(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public DBLoginInformation getLoginInformation(){
		// TODO create method description
		// TODO create test case
		// TODO implement method
		throw new RuntimeException("Not yet implemented!");
	}

}
