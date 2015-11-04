package dal.product.mysql;

import java.util.List;

import dal.MySQLDatabase;
import dal.product.generic.Notification;

public class MSNotification extends Notification {

	private static final String table = "notification";

	private static final String index = "id";
	private static final String label = "label";
	private static final String isread = "isread";
	private static final String fkcreator = "idcreatedby";
	private static final String fkreceiver = "idreceivedby";

	@Override
	public void save() {
		List<String> receivers = super.getReceivers();
		for(int i = 0 ; i < receivers.size() ; i++) {
			String request = "insert into "+table+" ("+isread+","+label+","+fkcreator+","+fkreceiver+") values ("
					+""+(getRead() ? 1 : 0)+","
					+"'"+getMessage()+"',"
					+"'"+getSender()+"',"
					+"'"+receivers.get(i)+"'"
					+ ")";
	
			MySQLDatabase.getInstance().insertRequest(request);
		}
	}

	@Override
	public void read(int idNotif) {
		String request = "UPDATE "+table+" SET "+isread+"=1 WHERE "+index+"="+idNotif;
		MySQLDatabase.getInstance().updateRequest(request);
	}
}