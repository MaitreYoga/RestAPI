package dal.product.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class SpeakerList {

	private List<Speaker> speakers;
	
	public SpeakerList(){
		speakers = new ArrayList<Speaker>();
	}
	
	public int size(){
		return speakers.size();
	}
	public void add(Speaker speaker){
		speakers.add(speaker);
	}
	public Speaker get(int index){
		return speakers.get(index);
	}

	public abstract void load();
}
