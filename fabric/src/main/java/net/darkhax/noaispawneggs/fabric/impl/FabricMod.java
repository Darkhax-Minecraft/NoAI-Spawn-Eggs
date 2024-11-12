package net.darkhax.noaispawneggs.fabric.impl;

import net.darkhax.noaispawneggs.common.impl.NoAISpawnEggs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FabricMod implements ModInitializer {

    @Override
    public void onInitialize() {
        final CreativeModeTab group = FabricItemGroup.builder().icon(() -> new ItemStack(Items.CREEPER_SPAWN_EGG)).title(NoAISpawnEggs.TITLE).displayItems((context, entries) -> NoAISpawnEggs.populateDisplayStacks(entries::accept)).build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(NoAISpawnEggs.MOD_ID, "tab"), group);
    }
}