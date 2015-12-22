package practicalities.gui.book;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import practicalities.gui.GuiHelper;
import practicalities.gui.GuiScreenBase;
import practicalities.gui.ItemHelper;
import practicalities.gui.element.ElementScreenBase;

public class GuideEntry extends ElementScreenBase {

	public static final int HEIGHT = 11;
	
	boolean mouseIn = false;
	double animateStart = 0;
	int mouseEnterX = 0;
	
	String name;
	ItemStack icon;
	GuideStateManager state;
	GuiHelper h = GuiHelper.instance();
	
	public GuideEntry(GuiScreenBase gui, int x, int y, GuideStateManager state, String name) {
		super(gui, x, y, GuiGuide.MAIN_SIZE_X, HEIGHT);
		this.state = state;
		this.name = name;
		this.icon = ItemHelper.instance().parseItemStack(StatCollector.translateToLocal("guide.entry."+name+".list.iconStack"));
	}
	
	public String getTranslatedName() {
		return StatCollector.translateToLocal("guide.entry." + name + ".list.text");
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float partialTick) {
		
	}

	@Override
	public void drawForeground(int paramInt1, int paramInt2) {
		
		boolean intersect = intersectsWith(paramInt1, paramInt2) && paramInt2 != posY; // to prevent two of them being hovered over at the same time. intersectsWith uses <= and >=
		
		if(!mouseIn && intersect) {
			mouseIn = true;
			mouseEnterX = paramInt1-posX;
			animateStart = h.getTicks();
		} else if(mouseIn && !intersect) {
			mouseIn = false;
			animateStart = 0; // just for good measure.
		}
		
		int textLeft = 12;
		
		double s = 0.6;
		double S = 1.0/s;
		
		GL11.glScaled(s, s, s);

		h.itemRenderSetup();
		h.drawItemStack(icon, (int)( (posX+1)*S ), (int)( (posY+1)*S ));
		h.itemRenderCleanup();
		
		GL11.glScaled(S, S, S);
		
		s = 2.0/3.0;
		S = 1.0/s;
		
		GL11.glScaled(s, s, s);
		GuiHelper.fontRendererObj.drawString(getTranslatedName(), (int)( ( posX+textLeft )*S ), (int)( ( posY+3 )*S ), 0xFF0000);
		GL11.glScaled(S, S, S);
		
		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
		
		if(mouseIn) {
			double p = h.tickAnimate(animateStart, 5);
			h.loadInterpolationFraction(Math.min(p, 1));
			
			h.drawColoredRect(posX+h.intr(mouseEnterX, 0), posY, posX+ h.intr(mouseEnterX, sizeX), posY+sizeY, 0,0,0, h.intr(0, 0.1));
		}
	}
	
	@Override
	public boolean onMousePressed(int paramInt1, int paramInt2, int paramInt3) {
		state.currentPage = null;
		state.goToPage(name, 0);
		return true;
	}

}
