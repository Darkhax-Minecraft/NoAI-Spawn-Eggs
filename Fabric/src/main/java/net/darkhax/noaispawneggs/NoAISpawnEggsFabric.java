package net.darkhax.noaispawneggs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NoAISpawnEggsFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        final FabricItemGroupBuilder tabBuilder = FabricItemGroupBuilder.create(new ResourceLocation(Constants.MOD_ID, "egg_tab"));
        tabBuilder.icon(() -> new ItemStack(Items.COW_SPAWN_EGG));
        tabBuilder.appendItems(NoAISpawnEggsCommon.populateDisplayStacks(Registry.ITEM));
        tabBuilder.build();

        ItemTooltipCallback.EVENT.register(NoAISpawnEggsCommon::onItemTooltip);
    }
}