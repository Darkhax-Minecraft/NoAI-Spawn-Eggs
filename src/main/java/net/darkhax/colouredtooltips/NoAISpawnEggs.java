package net.darkhax.colouredtooltips;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "noaispawneggs", name = "No AI Spawn Eggs", version = "@VERSION@", clientSideOnly = true, certificateFingerprint = "@FINGERPRINT@")
public class NoAISpawnEggs {

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void onPreInit (FMLPreInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(this);
        new CreativeTabNoAI();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTooltipInfo (ItemTooltipEvent event) {

    	if (event.getItemStack() != null && event.getItemStack().getItem() instanceof ItemMonsterPlacer) {
    		
    		if (event.getItemStack().hasTagCompound()) {
    			
    			final NBTTagCompound stackTag = event.getItemStack().getTagCompound();
    			
    			if (stackTag.hasKey("EntityTag")) {
    				
    				final NBTTagCompound entityTag = stackTag.getCompoundTag("EntityTag");
    				
    				if (entityTag.getBoolean("NoAI")) {
    					
    					event.getToolTip().add(TextFormatting.RED + I18n.format("itemGroup.noai"));
    				}
    			}
    			
    		}
    	}
    }
}