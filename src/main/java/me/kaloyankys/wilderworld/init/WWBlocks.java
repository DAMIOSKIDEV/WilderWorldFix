package me.kaloyankys.wilderworld.init;

import me.andante.chord.block.helper.WoodBlocks;
import me.kaloyankys.wilderworld.Wilderworld;
import me.kaloyankys.wilderworld.block.*;
import me.kaloyankys.wilderworld.item.ScentedCandleItem;
import me.kaloyankys.wilderworld.util.enums.Flavours;
import me.kaloyankys.wilderworld.world.AspenSaplingGenerator;
import me.kaloyankys.wilderworld.world.WisteriaSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;

import java.util.function.Function;
import java.util.logging.Logger;

public class WWBlocks {
    public static final Block BIRD_OF_PARADISE = registerFF("bird_of_paradise", new TallPlantBlock(FabricBlockSettings.of(Material.PLANT, MapColor.ORANGE)
            .nonOpaque()
            .breakInstantly()
            .collidable(false)));

    public static final Block CHAMOMILE = registerFF("chamomile", new FlowerBlock(StatusEffects.LUCK, 400, FabricBlockSettings
            .of(Material.PLANT, MapColor.WHITE_GRAY)
            .nonOpaque()
            .breakInstantly()
            .collidable(false)));

    public static final Block RAGING_VIOLET = registerFF("raging_violet", new FlowerBlock(StatusEffects.STRENGTH, 400, FabricBlockSettings
            .of(Material.PLANT, MapColor.WHITE_GRAY)
            .nonOpaque()
            .breakInstantly()
            .collidable(false)));

    public static final Block GLOWBRUSH = registerFF("glowbrush", new GlowBrushBlock(FabricBlockSettings
            .of(Material.PLANT, MapColor.LIME)
            .nonOpaque()
            .breakInstantly()
            .luminance(state -> {
                if (state.get(SeedSpreaderBlock.STAGES) == 1) {
                    return 12;
                }
                return 0;
            })
            .collidable(false)));

    public static final Block SHELFSHROOM = registerFF("shelfshroom", new ShelfshroomBlock(FabricBlockSettings
            .of(Material.PLANT, MapColor.WHITE_GRAY)
            .nonOpaque()
            .breakInstantly()
            .collidable(false)));

    public static final Block ASPEN_LEAVES = registerFF("aspen_leaves", new LeavesBlock(FabricBlockSettings
            .copy(Blocks.OAK_LEAVES)
            .nonOpaque()));

    public static final WoodBlocks WISTERIA = new WoodBlocks.Builder()
            .saplingGenerator(new WisteriaSaplingGenerator())
            .boatType(BoatEntity.Type.BIRCH)
            .itemGroup(Wilderworld.FF_ADDITIONS)
            .build("wilderworld", "wisteria");

    public static final Block ASPEN_SAPLING = registerNoItem("aspen_sapling", new AspenBirchSaplingBlock(new AspenSaplingGenerator(), FabricBlockSettings
            .copy(Blocks.BIRCH_SAPLING)
            .nonOpaque()));

    public static final Block BUTTERFLY_SPAWN = registerFF("larvae", new SpawnBlock(FabricBlockSettings
            .of(Material.EGG)
            .collidable(false)
            .nonOpaque()
            .breakInstantly()));

    public static final Block BUTTERFLY_WING_MEMBRANE_BLOCK = registerFF("butterfly_wing_membrane_block", new MembraneBlock(FabricBlockSettings
            .of(Material.SOLID_ORGANIC)
            .collidable(false)));

    public static final Block WAX = registerFF("wax", new WaxBlock(FabricBlockSettings.copy(Blocks.HONEY_BLOCK)));

    public static final Block SCENTED_CANDLE = registerFF("scented_candle", new ScentedCandleBlock(FabricBlockSettings
            .of(Material.SOLID_ORGANIC)
            .luminance((state) -> {
                if (state.get(ScentedCandleBlock.LIT)) {
                    return 15;
                } else {
                    return 0;
                }
            })
            .ticksRandomly()));

    public static final Block EBONY_BUSH_TALL = registerIcy("ebony_bush_tall", new SnowyBushBlock(FabricBlockSettings.copy(Blocks.GRASS)));
    public static final Block EBONY_BUSH = registerIcy("ebony_bush", new SnowyPlantBlock(FabricBlockSettings.copy(Blocks.GRASS)));

    public static final Block COCOA_ICECREAM = registerNoItem("cocoa_icecream_block", new IceCreamBlock(
            FabricBlockSettings.copy(Blocks.SNOW).nonOpaque(), Flavours.CHOCOLATE));

    public static final Block SWEETBERRY_ICECREAM = registerNoItem("sweetberry_icecream_block", new IceCreamBlock(
            FabricBlockSettings.copy(Blocks.SNOW).nonOpaque(), Flavours.SWEET_BERRY));

    public static final Block MINT_ICECREAM = registerNoItem("mint_icecream_block", new IceCreamBlock(
            FabricBlockSettings.copy(Blocks.SNOW).nonOpaque(), Flavours.MINT));

    public static final Block SLUSH_CAULDRON = registerNoItem("slush_cauldron", new SlushCauldronBlock(
            FabricBlockSettings.copy(Blocks.CAULDRON), LeveledCauldronBlock.RAIN_PREDICATE, CauldronBehavior.WATER_CAULDRON_BEHAVIOR));

    public static final Block TRAVERTINE = registerIcy("travertine", new Block(FabricBlockSettings.copy(Blocks.QUARTZ_BLOCK)));
    public static final Block TRAVERTINE_PEACH = registerIcy("travertine_peach", new Block(FabricBlockSettings.copy(Blocks.TERRACOTTA)));
    public static final Block PEARL_STONE = registerIcy("pearl_stone", new Block(FabricBlockSettings.copy(Blocks.QUARTZ_BLOCK)));
    public static final Block PEARL_CHISELED = registerIcy("pearl_chiseled", new Block(FabricBlockSettings.copy(Blocks.QUARTZ_BLOCK)));
    public static final Block PEARL_TILES = registerIcy("pearl_tiles", new Block(FabricBlockSettings.copy(Blocks.QUARTZ_BLOCK)));

    public static final Block GLOWGI = registerIcy("glowgi", new LilyPadBlock(FabricBlockSettings.copy(Blocks.LILY_PAD).luminance((state) -> 15)));

    public static Block registerFF(String id, Block block) {
        Registry.register(Registry.ITEM, new Identifier("wilderworld", id), new BlockItem(block, new Item.Settings().group(Wilderworld.FF_ADDITIONS)));
        return Registry.register(Registry.BLOCK, new Identifier("wilderworld", id), block);
    }

    public static Block registerIcy(String id, Block block) {
        Registry.register(Registry.ITEM, new Identifier("wilderworld", id), new BlockItem(block, new Item.Settings().group(Wilderworld.ICY_ADDITIONS)));
        return Registry.register(Registry.BLOCK, new Identifier("wilderworld", id), block);
    }

    public static Block registerNoItem(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier("wilderworld", id), block);
    }
}
