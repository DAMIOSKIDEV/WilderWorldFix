package me.kaloyankys.wilderworld.init;

import com.google.common.collect.ImmutableList;
import me.kaloyankys.wilderworld.world.ShelfshroomTreeDecorator;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.MegaJungleTrunkPlacer;

import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public class WWBiomeModifications {
    private static final Random RANDOM = new Random();
    public static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);

    /* public static final ConfiguredFeature<?, ?> BIRD_OF_PARADISE_PATCH = registerFF("birds_of_paradise", Feature.RANDOM_PATCH.configure(
            ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
                    BlockStateProvider.of(WWBlocks.BIRD_OF_PARADISE))), List.of(Blocks.GRASS_BLOCK))));

    public static final ConfiguredFeature<?, ?> CHAMOMILE_PATCH = registerFF("chamomiles", Feature.RANDOM_PATCH.configure(
            ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
                    BlockStateProvider.of(WWBlocks.CHAMOMILE))), List.of(Blocks.GRASS_BLOCK))));

    public static final ConfiguredFeature<?, ?> RAGING_VIOLET_PATCH = registerFF("raging_violets", Feature.RANDOM_PATCH.configure(
            ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
                    BlockStateProvider.of(WWBlocks.RAGING_VIOLET))), List.of(Blocks.GRASS_BLOCK))));

    public static final ConfiguredFeature<?, ?> PHOSPHOSHOOT_PATCH = registerFF("phosphoshoots", Feature.RANDOM_PATCH.configure(
            ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
                    BlockStateProvider.of(WWBlocks.PHOSPHOSHOOTS))), List.of(Blocks.GRASS_BLOCK))));

    public static final ConfiguredFeature<?, ?> SHELFSHROOMS = registerFF("shelfshrooms", Feature.RANDOM_PATCH.configure(
            ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
                    BlockStateProvider.of(WWBlocks.SHELFSHROOM))), List.of(Blocks.GRASS_BLOCK)))); */

    public static final ConfiguredFeature<SimpleRandomFeatureConfig, ?> FF_CUSTOM_FLOWERS = registerFF("ff_custom_flowers", Feature.SIMPLE_RANDOM_SELECTOR.configure(
            new SimpleRandomFeatureConfig(List.of(() ->
                    Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(WWBlocks.BIRD_OF_PARADISE))))).withPlacement(), () ->
                    Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(WWBlocks.CHAMOMILE))))).withPlacement(), () ->
                    Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(WWBlocks.RAGING_VIOLET))))).withPlacement(), () ->
                    Feature.NO_BONEMEAL_FLOWER.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(WWBlocks.PHOSPHOSHOOTS))))).withPlacement()))));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> ASPEN_BIRCH_TREE = registerFFTree("aspen_birch_tree", Feature.TREE.configure(new TreeFeatureConfig.Builder(
            BlockStateProvider.of(Blocks.BIRCH_LOG), new MegaJungleTrunkPlacer(11, 3, 12), BlockStateProvider.of(WWBlocks.ASPEN_LEAVES),
            new BlobFoliagePlacer(
                    UniformIntProvider.create(3, 4),
                    UniformIntProvider.create(3, 4),
                    6),
            new TwoLayersFeatureSize(1, 1, 2))
            .decorators(ImmutableList.of(new BeehiveTreeDecorator(0.5f), new ShelfshroomTreeDecorator()))
            .ignoreVines()
            .build()));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> WISTERIA_TREE = registerFFTree("wisteria_tree", Feature.TREE.configure(new TreeFeatureConfig.Builder(
            BlockStateProvider.of(WWBlocks.WISTERIA.LOG), new LargeOakTrunkPlacer(4, 14, 0), BlockStateProvider.of(WWBlocks.WISTERIA.LEAVES),
            new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), RANDOM.nextInt(50) + 30),
            new TwoLayersFeatureSize(1, 1, 2))
            .decorators(ImmutableList.of(new BeehiveTreeDecorator(0.5f), new TrunkVineTreeDecorator()))
            .forceDirt()
            .build()));

    public WWBiomeModifications() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), SpawnGroup.AMBIENT, WWEntities.BUTTERFLY,
                20, 1, 4);
    }

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerFF(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("wilderworld", id)));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier("wilderworld", id)).getValue(), configuredFeature.withPlacement(RarityFilterPlacementModifier.of(7),
                SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, CountPlacementModifier
                        .of(ClampedIntProvider.create(UniformIntProvider.create(-1, 5), 0, 5)), BiomePlacementModifier.of()));
        return configuredFeature;
    }

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerFFTree(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("wilderworld", id)));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                new Identifier("wilderworld", id)).getValue(), configuredFeature.withPlacement(VegetationPlacedFeatures
                .modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.1f, 3), Blocks.OAK_SAPLING)));
        return configuredFeature;
    }

    private static DataPool.Builder<BlockState> pool() {
        return DataPool.builder();
    }
}
