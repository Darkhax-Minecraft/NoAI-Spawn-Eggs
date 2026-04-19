package net.darkhax.noaispawneggs.neoforge;

import net.darkhax.noaispawneggs.common.impl.NoAISpawnEggs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Objects;

@Mod(NoAISpawnEggs.MOD_ID)
public class NoAISpawnEggsNeoforge {

    public NoAISpawnEggsNeoforge(IEventBus modBus) {
        modBus.addListener(this::createItemTabs);
    }

    private void createItemTabs(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.CREATIVE_MODE_TAB) {
            event.register(Registries.CREATIVE_MODE_TAB, Objects.requireNonNull(Identifier.tryBuild(NoAISpawnEggs.MOD_ID, "tab")), () -> {
                final CreativeModeTab.Builder builder = CreativeModeTab.builder();
                builder.title(Component.translatable("itemGroup.noaispawneggs.egg_tab"));
                builder.icon(Items.PIG_SPAWN_EGG::getDefaultInstance);
                builder.displayItems((params, output) -> NoAISpawnEggs.populateDisplayStacks(output::accept));
                return builder.build();
            });
        }
    }
}