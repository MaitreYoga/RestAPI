package bl.manager;

import dal.factory.Factory;
import dal.product.generic.Speaker;
import dal.product.generic.SpeakerList;

public class SpeakerManager {

	private Factory factory;
	
	public SpeakerManager(){
    	factory = Factory.getInstance();
	}
	
	public String handleSubscribe(String login, String job) {
		Speaker speaker = factory.makeSpeaker();
		speaker.setJob(job);
		return speaker.save(login);
	}

	public Speaker getSpeaker(int speakerId) {
		Speaker speaker = factory.makeSpeaker();
		speaker.load(speakerId);
		return speaker;
	}
	
	public int getIdFromSpeaker(int userId) {
		return factory.makeSpeaker().getIdFromSpeaker(userId);
	}

	public void handleEdit(int speakerId, String job, String sDescritpion, String lDescription) {
		Speaker speaker = factory.makeSpeaker();
		speaker.setJob(job);
		speaker.setShortDescription(sDescritpion);
		speaker.setLongDescription(lDescription);
		speaker.update(speakerId);
	}

	public SpeakerList getAllSpeakers() {
		SpeakerList speakers = factory.makeSpeakerList();
		speakers.load();
		return speakers;
	}
}