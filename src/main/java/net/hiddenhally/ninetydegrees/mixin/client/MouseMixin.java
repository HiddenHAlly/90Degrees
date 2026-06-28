package net.hiddenhally.ninetydegrees.mixin.client;

import net.minecraft.client.Mouse;
import net.hiddenhally.ninetydegrees.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Shadow private double cursorDeltaX;
    @Shadow private double cursorDeltaY;

    @Inject(method = "updateMouse", at = @At("HEAD"))
    private void onUpdateMouse(CallbackInfo ci) {
        Mouse mouse = (Mouse) (Object) this;

        if (mouse.isCursorLocked()) {
            double originalX = this.cursorDeltaX;
            double originalY = this.cursorDeltaY;

            double degrees = ModConfig.INSTANCE.rotationDegrees;

            // If rotation is 0, do nothing
            if (degrees == 0.0) {
                return;
            }

            // Convert degrees to radians for trigonometric functions
            double radians = Math.toRadians(degrees);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);

            // Apply 2D Rotation Matrix:
            // X' = X * cos(θ) - Y * sin(θ)
            // Y' = X * sin(θ) + Y * cos(θ)
            this.cursorDeltaX = originalX * cos - originalY * sin;
            this.cursorDeltaY = originalX * sin + originalY * cos;
        }
    }
}