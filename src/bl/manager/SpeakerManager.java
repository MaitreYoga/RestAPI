package bl.manager;

import persistance.data.generic.Speaker;
import persistance.data.generic.SpeakerList;
import persistance.factory.Factory;

public class SpeakerManager {

	private Factory factory;
	private Speaker speaker;
	
	public SpeakerManager(){
    	factory = Factory.getInstance();
	}
	
	public String handleSubscribe(String login, String job) {
		speaker = factory.makeSpeaker();
		
		speaker.setJob(job);

		return speaker.save(login);
	}

	public Speaker getSpeaker(int speakerId) {
		speaker = factory.makeSpeaker();
		speaker.load(speakerId);
		return speaker;
	}
	
	public int getIdFromSpeaker(String user) {
		speaker = factory.makeSpeaker();
		return speaker.getIdFromSpeaker(user);
	}

	public void handleEdit(int speakerId, String job, String sDescritpion, String lDescription) {
		speaker = factory.makeSpeaker();
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