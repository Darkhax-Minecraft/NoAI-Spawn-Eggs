package net.darkhax.colouredtooltips;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabNoAI extends CreativeTabs {
    
    public CreativeTabNoAI() {
        
        super("noai");
    }
    
    @Override
    public ItemStack createIcon () {
        
        return new ItemStack(Items.SPAWN_EGG);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems (NonNullList<ItemStack> itemList) {
        
        super.displayAllRelevantItems(itemList);
        
        for (final ResourceLocation id : EntityList.ENTITY_EGGS.keySet()) {
            
            final ItemStack spawner = new ItemStack(Items.SPAWN_EGG);
            ItemMonsterPlacer.applyEntityIdToItemStack(spawner, id);
            NBTTagCompound entityTag = spawner.getTagCompound().getCompoundTag("EntityTag");
            entityTag.setBoolean("NoAI", true);
            itemList.add(spawner);
        }
    }
}