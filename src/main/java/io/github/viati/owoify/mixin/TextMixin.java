package io.github.viati.owoify.mixin;

import io.github.viati.owoify.OwOify;
import net.minecraft.text.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Text.class, priority = 1000000000)
public interface TextMixin {
    @Redirect(method = "literal", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;of(Lnet/minecraft/text/TextContent;)Lnet/minecraft/text/MutableText;"))
    private static MutableText onLiteral(TextContent content) {
        return MutableText.of(new LiteralTextContent(OwOify.owoify(((LiteralTextContent) content).string())));
    }

    @Redirect(method = "of", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;literal(Ljava/lang/String;)Lnet/minecraft/text/MutableText;"))
    private static MutableText onOf(String string) {
        return MutableText.of(new LiteralTextContent(OwOify.owoify(string)));
    }

    @Inject(method = "translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;", at = @At("RETURN"), cancellable = true)
    private static void onTranslate(String key, CallbackInfoReturnable<MutableText> cir) {
        cir.setReturnValue(MutableText.of(new TranslatableTextContent(OwOify.owoify(MutableText.of(new TranslatableTextContent(key)).getString()))));
    }

    @Inject(method = "translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;", at = @At("RETURN"), cancellable = true)
    private static void onTranslate(String key, Object[] args, CallbackInfoReturnable<MutableText> cir) {
        cir.setReturnValue(MutableText.of(new TranslatableTextContent(OwOify.owoify(MutableText.of(new TranslatableTextContent(key, args)).getString()))));
    }
}
