package practicalities.gui.book;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.StatCollector;
import practicalities.gui.GuiScreenBase;
import practicalities.gui.book.page.PageEntryList;
import practicalities.gui.book.page.PageInEntry;

public class GuideStateManager {

	public GuidePage page;
	
	public static Map<String, PageEntryList> entryLists = new HashMap<String, PageEntryList>();
	
	public static void registerList(String name, String... entries) {
		PageEntryList list = new PageEntryList();
		for (int i = 0; i < entries.length; i++) {
			list.entries.add(entries[i]);
		}
		entryLists.put(name, list);
	}
	
	public static PageEntryList mainPage;
	public static GuidePersistance persistance = GuidePersistance.instance();
	
	GuiScreenBase gui;
	int xSize, ySize;
	String currentPage;
	String lastList;
	int currentPageNum;
	int maxPageNum;
	
	public GuideStateManager(GuiScreenBase gui, int xSize, int ySize) {
		this.gui = gui; this.xSize = xSize; this.ySize = ySize;
		if(mainPage == null || !entryLists.containsKey("list.root")) {
			mainPage = new PageEntryList();
			mainPage.entries.add("intro");
			mainPage.entries.add("list.intro");
			entryLists.put("list.root", mainPage);
		}
		
		
		if(persistance.didStopOnEntryList) {
			goToEntryList(persistance.name);
		} else {
			goToPage(persistance.name, persistance.page);
		}
	}
	
	public void goToEntryList() {
		goToEntryList(lastList);
	}
	
	public void goToEntryList(String name) {
		if(name != null) {
			page = entryLists.get(name);
		}
		if(page == null || name == null) {
			page = mainPage;
		}
		
		page.init(gui, this);
		this.lastList = name;
		persistance.didStopOnEntryList = true;
		persistance.name = name;
	}
	
	public void goToPage(String name, int number) {
		if(currentPage == null || !currentPage.equals(name)) {
			this.maxPageNum = getMaxPageNum(name);
		}
		if(number > maxPageNum)
			number = maxPageNum;
		page = GuidePage.getPage(gui, this, name, number);

		this.currentPage = name;
		this.currentPageNum = number;
		
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
