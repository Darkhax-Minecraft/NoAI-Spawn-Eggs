package net.darkhax.noaispawneggs;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.function.Consumer;

public class NoAISpawnEggsCommon {

    public static void onItemTooltip(ItemStack stack, TooltipFlag context, List<Component> tooltip) {

        if (stack.getItem() instanceof SpawnEggItem) {

            final CompoundTag stackTag = stack.getTagElement("EntityTag");

            if (stackTag != null && stackTag.getBoolean("NoAI")) {

                tooltip.add(Component.translatable("itemGroup.noaispawneggs.egg_tab").withStyle(ChatFormatting.GOLD));
            }
        }
    }

    public static void populateDisplayStacks(Iterable<Item> items, Consumer<ItemStack> adder) {

        // Grab ItemStack variants from creative tab.
        for (Item item : items) {

            if (item instanceof SpawnEggItem spawnEgg) {

                final ItemStack eggStack = spawnEgg.getDefaultInstance().copy();
                final CompoundTag entityTag = eggStack.getOrCreateTagElement("EntityTag");
                entityTag.putBoolean("NoAI", true);
                adder.accept(eggStack);
            }
        }
    }
}