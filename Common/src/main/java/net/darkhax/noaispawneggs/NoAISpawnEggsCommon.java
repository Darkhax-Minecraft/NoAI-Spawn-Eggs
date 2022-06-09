package net.darkhax.noaispawneggs;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
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

    public static Consumer<List<ItemStack>> populateDisplayStacks(Iterable<Item> items) {

        final NonNullList<ItemStack> eggs = NonNullList.create();

        return list -> {

            // Populate ItemStack list lazily.
            if (eggs.isEmpty()) {

                final NonNullList<ItemStack> tempEggs = NonNullList.create();

                // Grab ItemStack variants from creative tab.
                for (Item item : items) {

                    if (item instanceof SpawnEggItem spawnEgg) {

                        spawnEgg.fillItemCategory(CreativeModeTab.TAB_SEARCH, tempEggs);
                    }
                }

                // Clone and modify stacks to have the no AI tag
                for (ItemStack eggStack : tempEggs) {

                    final ItemStack eggCopy = eggStack.copy();
                    final CompoundTag entityTag = eggCopy.getOrCreateTagElement("EntityTag");
                    entityTag.putBoolean("NoAI", true);
                    eggs.add(eggCopy);
                }
            }

            list.addAll(eggs);
        };
    }
}