package net.darkhax.noaispawneggs;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class CreativeTabNoAI extends CreativeModeTab {

    public CreativeTabNoAI() {

        super(Constants.MOD_ID + "." + "egg_tab");
    }

    private ItemStack icon = ItemStack.EMPTY;
    private final Consumer<List<ItemStack>> items = NoAISpawnEggsCommon.populateDisplayStacks(ForgeRegistries.ITEMS);

    @Override
    public ItemStack makeIcon() {

        if (icon.isEmpty()) {

            icon = new ItemStack(Items.COW_SPAWN_EGG);
        }

        return this.icon;
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> displayStacks) {

        items.accept(displayStacks);
    }
}

