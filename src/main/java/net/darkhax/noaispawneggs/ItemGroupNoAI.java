package net.darkhax.noaispawneggs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemGroupNoAI extends ItemGroup {
    
    public ItemGroupNoAI() {
        
        super("noai");
        this.setBackgroundImageName("item_search.png");
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack createIcon () {
        
        return new ItemStack(Items.CREEPER_SPAWN_EGG);
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void fill (NonNullList<ItemStack> items) {
        
        for (final Item item : ItemSpawnEgg.getEggs()) {
            
            if (item instanceof ItemSpawnEgg) {
                
                final ItemStack stack = new ItemStack(item);
                final NBTTagCompound entityTag = stack.getOrCreateChildTag("EntityTag");
                entityTag.putBoolean("NoAI", true);
                items.add(stack);
            }
        }
    }
    
    @Override
    public boolean hasSearchBar () {
        
        return true;
    }
}