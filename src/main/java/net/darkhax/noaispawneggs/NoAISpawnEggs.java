package net.darkhax.noaispawneggs;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod("noaispawneggs")
public class NoAISpawnEggs {
    
    public NoAISpawnEggs() {
        
    	if (FMLEnvironment.dist == Dist.CLIENT) {
    		
            MinecraftForge.EVENT_BUS.addListener(this::handleTooltips);
            new ItemGroupNoAI();
    	}
    }
    
    private void handleTooltips (ItemTooltipEvent event) {
        
        final ItemStack stack = event.getItemStack();
        
        if (stack.getItem() instanceof SpawnEggItem) {
            
            final CompoundNBT stackTag = stack.getChildTag("EntityTag");
            
            if (stackTag != null && stackTag.getBoolean("NoAI")) {
                
                event.getToolTip().add(new TranslationTextComponent("itemGroup.noai").func_240699_a_(TextFormatting.GOLD));
            }
        }
    }
}