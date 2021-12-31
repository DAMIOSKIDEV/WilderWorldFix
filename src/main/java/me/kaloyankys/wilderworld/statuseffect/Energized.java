package me.kaloyankys.wilderworld.statuseffect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class Energized extends StatusEffect {

    public Energized(StatusEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
    }
}
