package practicalities.gui.book.page;

import java.util.ArrayList;
import java.util.List;

import practicalities.gui.GuiScreenBase;
import practicalities.gui.book.GuideEntry;
import practicalities.gui.book.GuidePage;
import practicalities.gui.book.GuideStateManager;

public class PageEntryList extends GuidePage {

	public List<String> entries = new ArrayList<String>();
	
	@Override
	public void init(GuiScreenBase gui, GuideStateManager state) {
		int top = 0;
		for (String entry : entries) {
			mainElements.add(new GuideEntry(gui, 0, top, state, entry));
			top += GuideEntry.HEIGHT;
		}
	}

}
