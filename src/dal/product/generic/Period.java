package dal.product.generic;

import java.util.List;

import javax.swing.JComboBox;

public abstract class Period {

	public Period() 
	{
		
	}

	public abstract String load(String periodID);

	public abstract List<Object> loadPeriod(String chosenEvent);

}
