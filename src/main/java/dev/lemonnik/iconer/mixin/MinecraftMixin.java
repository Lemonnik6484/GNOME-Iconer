package dev.lemonnik.iconer.mixin;

import dev.lemonnik.iconer.Iconer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    //? if >1.21.1 {
    @Inject(method = "<init>", at = @At("HEAD"))
    private static void iconer$init(CallbackInfo ci) {
        Iconer.genFiles();
    }
    //?}

    //? if >1.21.1 {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;<init>(Lcom/mojang/blaze3d/platform/WindowEventHandler;Lcom/mojang/blaze3d/platform/DisplayData;Ljava/lang/String;Ljava/lang/String;Lcom/mojang/blaze3d/systems/GpuBackend;)V"), index = 3)
    private static String iconer$swapWindowName(String original) {
        return "Minecraft*";
    }
    //?} else {
    /*@ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/VirtualScreen;newWindow(Lcom/mojang/blaze3d/platform/DisplayData;Ljava/lang/String;Ljava/lang/String;)Lcom/mojang/blaze3d/platform/Window;"), index = 2)
    private String iconer$swapWindowName(String original) {
        return "Minecraft*";
    }
    *///?}
}
