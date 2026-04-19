package net.darkhax.noaispawneggs.fabric;

import net.darkhax.noaispawneggs.common.impl.NoAISpawnEggs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NoAISpawnEggsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        final CreativeModeTab tab = FabricCreativeModeTab.builder().icon(() -> new ItemStack(Items.CREEPER_SPAWN_EGG)).title(NoAISpawnEggs.TITLE).displayItems((params, output) -> NoAISpawnEggs.populateDisplayStacks(output::accept)).build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(NoAISpawnEggs.MOD_ID, "tab"), tab);
    }
}