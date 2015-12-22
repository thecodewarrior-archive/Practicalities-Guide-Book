package practicalities;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import practicalities.gui.GuiHandler;
import practicalities.gui.GuiHelper;

@Mod(modid = PracticalitiesMod.MODID, version = PracticalitiesMod.VERSION)
public class PracticalitiesMod {
	public static final String MODID = "practicalities_guides";
	public static final String VERSION = "2.0.0-a5";
	public static final String TEXTURE_BASE = "practicalities:";
	private boolean initializedMachines;

	Item book;
	
	@Instance(PracticalitiesMod.MODID)
	public static PracticalitiesMod instance;
	public static final GuiHandler guiHandler = new GuiHandler();

//	@SidedProxy(clientSide = "practicalities.network.ProxyClient", serverSide = "practicalities.network.Proxy")
//	public static Proxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		book = new ItemGuide();
//		ConfigMan.init(new Configuration(event.getSuggestedConfigurationFile()));

//		proxy.preInit();
//		initMachines();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
	}

//	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
//		new CraftingRecipes().init();
//		proxy.registerKeyBinds();
//		proxy.registerRenderInformation();
//		proxy.registerTickHandlers();
//		proxy.registerPacketInformation();
	}

//	private void initMachines() {
//		if (!initializedMachines) {
//			TileShippingCrate.initialize();
//			TilePlayerInterface.initialize();
//			TileVampiricGenerator.initialize();
//			TileInventoryFilter.initialize();
//			TileTeslaCoil.initialize();
//			TileInductionCoil.initialize();
//			TileFieldRepeater.initialize();
//			initializedMachines = true;
//		}
//	}

	public static CreativeTabs tab = new CreativeTabs("tabPracticalities") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Items.acacia_door;
		}
	};

}