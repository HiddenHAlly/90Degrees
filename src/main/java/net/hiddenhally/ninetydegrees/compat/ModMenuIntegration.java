package net.hiddenhally.ninetydegrees.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.hiddenhally.ninetydegrees.config.ModConfig;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.literal("90 Degrees Configuration"));

            ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // Adds a slider/input field for the rotation angle
            general.addEntry(entryBuilder.startDoubleField(Text.literal("Rotation Degree"), ModConfig.INSTANCE.rotationDegrees)
                    .setDefaultValue(90.0)
                    .setMin(-360.0)
                    .setMax(360.0)
                    .setTooltip(Text.literal("The angle (in degrees) to rotate mouse movements clockwise."))
                    .setSaveConsumer(newValue -> ModConfig.INSTANCE.rotationDegrees = newValue)
                    .build());

            builder.setSavingRunnable(ModConfig::save);

            return builder.build();
        };
    }
}