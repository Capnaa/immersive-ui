package it.hurts.shatterbyte.immersiveui.mixin;

import it.hurts.shatterbyte.immersiveui.ImmersiveUI;
import it.hurts.shatterbyte.immersiveui.compat.ExtraScreenData;
import it.hurts.shatterbyte.immersiveui.util.CommonCode;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "extractRenderStateWithTooltipAndSubtitles", at = @At("HEAD"))
    private void computeMouseDelta(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if ((Object) this instanceof ExtraScreenData screenData) {
            CommonCode.computeMouseDelta(screenData.getMouseInfo(), mouseX, mouseY);
        }
    }

    /**
     * Shakes the screen. This is done right after the dimmed world background has been drawn, so the background itself
     * stays put while the container texture, the slots and everything drawn on top of them move.
     */
    @Inject(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;extractTransparentBackground(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V", shift = At.Shift.AFTER))
    private void shake(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (!((Object) this instanceof ExtraScreenData screenData)) {
            return;
        }

        if (ImmersiveUI.SOPHISTICATED_COMPAT.isStorageScreenBase((Screen) (Object) this)) {
            return;
        }

        CommonCode.shakeScreen(guiGraphics, (Screen) (Object) this, screenData.getShakeTimer(), 1f);
    }
}
