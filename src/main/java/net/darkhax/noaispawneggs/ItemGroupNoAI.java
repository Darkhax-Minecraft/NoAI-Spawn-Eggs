package net.darkhax.noaispawneggs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
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
        
        for (final Item item : SpawnEggItem.getEggs()) {
            
            final ItemStack stack = new ItemStack(item);
            final CompoundNBT entityTag = stack.getOrCreateChildTag("EntityTag");
            entityTag.putBoolean("NoAI", true);
            items.add(stack);
        }
    }
    
    @Override
    public boolean hasSearchBar () {
        
        return true;
    }
}