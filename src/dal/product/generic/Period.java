package dal.product.generic;

import java.util.List;

public abstract class Period {

	public Period() 
	{
		
	}

	public abstract String load(String periodID);

	public abstract List<Object> loadPeriod(String chosenEvent);

}
