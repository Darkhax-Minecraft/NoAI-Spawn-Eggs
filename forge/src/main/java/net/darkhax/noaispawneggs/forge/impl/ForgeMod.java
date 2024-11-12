package net.darkhax.noaispawneggs.forge.impl;

import net.darkhax.noaispawneggs.common.impl.NoAISpawnEggs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Objects;

@Mod(NoAISpawnEggs.MOD_ID)
public class ForgeMod {

    public ForgeMod(FMLJavaModLoadingContext context) {
        context.getModEventBus().addListener(this::createItemTabs);
    }

    private void createItemTabs(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.CREATIVE_MODE_TAB) {
            event.register(Registries.CREATIVE_MODE_TAB, Objects.requireNonNull(ResourceLocation.tryBuild(NoAISpawnEggs.MOD_ID, "tab")), () -> {
                final CreativeModeTab.Builder builder = CreativeModeTab.builder();
                builder.title(Component.translatable("itemGroup.noaispawneggs.egg_tab"));
                builder.icon(Items.PIG_SPAWN_EGG::getDefaultInstance);
                builder.displayItems((params, output) -> NoAISpawnEggs.populateDisplayStacks(output::accept));
                return builder.build();
            });
        }
    }
}