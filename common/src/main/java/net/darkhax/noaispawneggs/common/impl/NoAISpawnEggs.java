package net.darkhax.noaispawneggs.common.impl;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.TypedEntityData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class NoAISpawnEggs {

    public static final String MOD_ID = "noaispawneggs";
    public static final String MOD_NAME = "NoAI Spawn Eggs";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    public static final MutableComponent TITLE = Component.translatable("itemGroup.noaispawneggs.egg_tab");
    public static final Component TOOLTIP = TITLE.copy().withStyle(ChatFormatting.GOLD);

    public static void populateDisplayStacks(Consumer<ItemStack> adder) {
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof SpawnEggItem spawnEgg) {
                final ItemStack eggStack = spawnEgg.getDefaultInstance().copy();
                final TypedEntityData<EntityType<?>> rawSpawnData = eggStack.get(DataComponents.ENTITY_DATA);
                if (rawSpawnData != null) {
                    final CompoundTag noAiData = rawSpawnData.copyTagWithoutId();
                    noAiData.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(rawSpawnData.type()).toString());
                    noAiData.putBoolean("NoAI", true);
                    eggStack.set(DataComponents.ITEM_NAME, Component.translatable("item.name.noaispawneggs", eggStack.getHoverName(), TOOLTIP));
                    eggStack.set(DataComponents.ENTITY_DATA, TypedEntityData.of(rawSpawnData.type(), noAiData));
                    adder.accept(eggStack);
                }
            }
        }
    }
}