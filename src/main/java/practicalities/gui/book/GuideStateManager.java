package practicalities.gui.book;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.StatCollector;
import practicalities.gui.GuiHelper;
import practicalities.gui.GuiScreenBase;
import practicalities.gui.book.page.PageEntryList;

public class GuideStateManager {

	public GuidePage page;
	
	public static Map<String, PageEntryList> entryLists = new HashMap<String, PageEntryList>();
	
	public static PageEntryList mainPage;
	public static GuidePersistance persistance = GuidePersistance.instance();
	
	GuiScreenBase gui;
	int xSize, ySize;
	String currentPage;
	int currentPageNum;
	int maxPageNum;
	
	public GuideStateManager(GuiScreenBase gui, int xSize, int ySize) {
		this.gui = gui; this.xSize = xSize; this.ySize = ySize;
		mainPage = new PageEntryList();
		mainPage.entries.add("intro");
		mainPage.entries.add("intro");
		mainPage.entries.add("intro");
		mainPage.entries.add("intro");
		if(persistance.didStopOnEntryList) {
			goToEntryList(persistance.name);
		} else {
			goToPage(persistance.name, persistance.page);
		}
	}
	
	public void goToEntryList(String name) {
		if(name == null) {
			mainPage.init(gui, this);
			page = mainPage;
		}
		persistance.didStopOnEntryList = true;
		persistance.name = name;
	}
	
	public void goToPage(String name, int number) {
		if(currentPage == null || !currentPage.equals(name)) {
			this.maxPageNum = getMaxPageNum(name);
		}
		if(number > maxPageNum)
			number = maxPageNum;
		this.currentPage = name;
		this.currentPageNum = number;
		page = GuidePage.getPage(gui, this, name, number);
		persistance.didStopOnEntryList = false;
		persistance.name = name;
		persistance.page = number;
	}
	
	int getMaxPageNum(String name) {
		int i = 0;
		while(StatCollector.canTranslate("guide.entry."+name+".page."+i)) {
			i++;
		}
		i--;
		return i;
	}
	
	public void goToNext() {
		goToPage(currentPage, currentPageNum+1);
	}
	
	public void goToPrev() {
		goToPage(currentPage, currentPageNum-1);
	}
	
	public int getMaxPageNum() {
		return maxPageNum;
	}
}
