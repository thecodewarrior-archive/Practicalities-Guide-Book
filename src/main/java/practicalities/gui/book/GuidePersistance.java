package practicalities.gui.book;

public class GuidePersistance {
	private static GuidePersistance instance;
	public static GuidePersistance instance() {
		if(instance == null) {
			instance = new GuidePersistance();
		}
		return instance;
	}
	
	public boolean didStopOnEntryList = true;
	public String name;
	public int page;
}
