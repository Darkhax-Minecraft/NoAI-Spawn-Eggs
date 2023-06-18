package net.darkhax.noaispawneggs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

public class NoAISpawnEggsFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        CreativeModeTab.Builder builder = FabricItemGroup.builder();
        builder.title(Component.translatable("itemGroup.noaispawneggs.egg_tab"));
        builder.icon(() -> Items.PIG_SPAWN_EGG.getDefaultInstance());
        builder.displayItems((params, output) -> NoAISpawnEggsCommon.populateDisplayStacks(BuiltInRegistries.ITEM, output::accept));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, "tab"), builder.build());
        ItemTooltipCallback.EVENT.register(NoAISpawnEggsCommon::onItemTooltip);
    }
}