package me.kaloyankys.wilderworld.init;

import me.kaloyankys.wilderworld.Wilderworld;
import me.kaloyankys.wilderworld.item.CoffeeMugItem;
import me.kaloyankys.wilderworld.item.MugItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WWItems {

    public static final Item EMPTY_MUG = register("empty_mug", new MugItem(new Item.Settings().group(Wilderworld.ICY_ADDITIONS)));

    public static final Item COFFEE_MUG = register("coffee_mug", new CoffeeMugItem(new Item.Settings().group(Wilderworld.ICY_ADDITIONS)
            .food(new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).alwaysEdible().build()).maxCount(16)));

    public static final Item SPAWN_BUTTERFLY = register("butterfly_spawn_egg", new SpawnEggItem(WWEntities.BUTTERFLY,
            0x381a20, 0xedaa00, new Item.Settings()
            .group(ItemGroup.MISC)));

    public static final Item BUTTEFLY_WING = register("butterfly_wing", new Item(new Item.Settings().group(Wilderworld.FF_ADDITIONS)));

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("wilderworld", id), item);
    }
}
