package me.kaloyankys.wilderworld.init;

import me.kaloyankys.wilderworld.mixin.BrewingRecipeRegistryInvoker;
import me.kaloyankys.wilderworld.statuseffect.RageEffect;
import me.kaloyankys.wilderworld.statuseffect.ShelfSenseEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WWPotions {

    public static final StatusEffect SHELF_SENSE_EFFECT = new ShelfSenseEffect(StatusEffectCategory.BENEFICIAL, 0xf5d442);

    public static final StatusEffect RAGE_EFFECT = new RageEffect(StatusEffectCategory.BENEFICIAL, 0x15031c)
            .addAttributeModifier(
                    EntityAttributes.GENERIC_ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.4D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(
                    EntityAttributes.GENERIC_MOVEMENT_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.4D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public static final Potion SHELF_SENSE = new Potion("shelf_sense", new StatusEffectInstance(SHELF_SENSE_EFFECT, 2400, 1));
    public static final Potion RAGE = new Potion("rage", new StatusEffectInstance(RAGE_EFFECT, 2400, 1));
    public static final Potion GLOWING = new Potion("glowing", new StatusEffectInstance(StatusEffects.GLOWING, 2400, 1));

    public static void init() {
        BrewingRecipeRegistryInvoker.registerPotionRecipe(Potions.AWKWARD, WWBlocks.SHELFSHROOM.asItem(), SHELF_SENSE);
        registerPotion("shelf_sense", SHELF_SENSE_EFFECT, SHELF_SENSE);
        BrewingRecipeRegistryInvoker.registerPotionRecipe(Potions.AWKWARD, WWBlocks.RAGING_VIOLET.asItem(), RAGE);
        registerPotion("rage", RAGE_EFFECT, RAGE);
        BrewingRecipeRegistryInvoker.registerPotionRecipe(Potions.AWKWARD, WWBlocks.PHOSPHOSHOOTS.asItem(), GLOWING);
        registerPotionWithExistingEffect("glowing", GLOWING);
    }

    private static void registerPotion(String id, StatusEffect statusEffect, Potion potion) {
        Registry.register(Registry.STATUS_EFFECT, new Identifier("wilderworld", id), statusEffect);
        Registry.register(Registry.POTION, new Identifier("wilderworld", id), potion);
    }

    private static void registerPotionWithExistingEffect(String id, Potion potion) {
        Registry.register(Registry.POTION, new Identifier("wilderworld", id), potion);
    }
}