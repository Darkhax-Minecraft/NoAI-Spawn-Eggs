package net.darkhax.noaispawneggs;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforgespi.Environment;

@Mod(Constants.MOD_ID)
public class NoAiSpawnEggsNeoForge {

    public NoAiSpawnEggsNeoForge() {

        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));

        if (Environment.get().getDist() == Dist.CLIENT) {

            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::createItemTabs);
            NeoForge.EVENT_BUS.addListener(this::displayTooltips);
        }
    }

    private void displayTooltips(ItemTooltipEvent event) {
        NoAISpawnEggsCommon.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

    private void createItemTabs(RegisterEvent event) {

        if (event.getRegistryKey() == Registries.CREATIVE_MODE_TAB) {

            event.register(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, "tab"), () -> {

                final CreativeModeTab.Builder builder = CreativeModeTab.builder();
                builder.title(Component.translatable("itemGroup.noaispawneggs.egg_tab"));
                builder.icon(() -> Items.PIG_SPAWN_EGG.getDefaultInstance());
                builder.displayItems((params, output) -> NoAISpawnEggsCommon.populateDisplayStacks(ForgeRegistries.ITEMS, output::accept));
                return builder.build();
            });
        }
    }
}