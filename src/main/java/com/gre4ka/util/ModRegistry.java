package com.gre4ka.util;

import com.gre4ka.PathOfMage;
import com.gre4ka.blockentity.storage.DiamondBlockEntity;
import com.gre4ka.blockentity.storage.RefinedDiamondBlockEntity;
import com.gre4ka.blocks.DiamondBlock;
import com.gre4ka.blocks.RefinedDiamondBlock;
import com.gre4ka.effects.MagicalExhaustionEffect;
import com.gre4ka.fluids.ModFluids;
import com.gre4ka.fluids.SkharnaFluid;
import com.gre4ka.items.CustomWritableBookItem;
import com.gre4ka.items.CustomWrittenBookItem;
import com.gre4ka.items.food.ManaAppleItem;
import com.gre4ka.items.tool.WandItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.Validate;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModRegistry {
    //Yeah, this is horrible
    private static final RegistryKey<ItemGroup> MAGPATH_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(PathOfMage.MOD_ID, "magpath_item_group"));
    private static final HashMap<Object, Identifier> objIdentMap = new HashMap<>();

    public static final ComponentType<Integer> MANA_COMPONENT = registerComponent(
            "mana_component", builder -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT)
    );

    public static Item WAND;
    public static Item MANA_APPLE;
    public static Item CUSTOM_WRITTEN_BOOK;
    public static Item CUSTOM_WRITABLE_BOOK;

    public static Block REFINED_DIAMOND_BLOCK;
    public static Block DIAMOND_BLOCK;

    private static final List<BlockEntityType<?>> TYPES = new ArrayList<>();
    public static BlockEntityType<DiamondBlockEntity> DIAMOND_MANA_STORAGE;
    public static BlockEntityType<RefinedDiamondBlockEntity> REFINED_DIAMOND_MANA_STORAGE;

    //public static StatusEffect MAGICAL_EXHAUSTION;
    public static RegistryEntry<StatusEffect> MAGICAL_EXHAUSTION;

    public static void register(){
        //registerPacketPayloads();
        registerItemGroups();
        registerItems();
        registerBlocks();
        registerBlockEntities();
        registerStatusEffects();
        ModFluids.registerFluids();

        /*		registerFluids();
		registerSounds();
		registerApis();
		TRVillager.registerVillagerTrades();
		TRVillager.registerWanderingTraderTrades();
		TRVillager.registerVillagerHouses();
		*/
    }

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, MAGPATH_ITEM_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.magpath.magpath_item_group"))
                .icon(() -> new ItemStack(WAND))
                .build());

        ItemGroupEvents.modifyEntriesEvent(MAGPATH_ITEM_GROUP).register(itemGroup -> {
            itemGroup.add(WAND);
        });
        PathOfMage.LOGGER.info("Successfully added new item group");
    }
    private static <T> ComponentType<T> registerComponent(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(PathOfMage.MOD_ID, id),
                builderOperator.apply(ComponentType.builder()).build()
        );
    }

    public static void registerBlockEntities(){
       DIAMOND_MANA_STORAGE = registerBlockEntity(DiamondBlockEntity::new, "diamond_mana_storage", DIAMOND_BLOCK);
       REFINED_DIAMOND_MANA_STORAGE = registerBlockEntity(RefinedDiamondBlockEntity::new, "refined_diamond_mana_storage", REFINED_DIAMOND_BLOCK);
    }

    private static void registerBlocks(){
        Item.Settings itemGroup = new Item.Settings();

        registerBlock(DIAMOND_BLOCK = InitUtils.setup(new DiamondBlock(), "diamond_block"), itemGroup);
        registerBlock(REFINED_DIAMOND_BLOCK = InitUtils.setup(new RefinedDiamondBlock(), "refined_diamond_block"), itemGroup);
    }
    private static void registerItems(){
        registerItem(WAND = InitUtils.setup(new WandItem(), "wand"));
        registerItem(MANA_APPLE = InitUtils.setup(new ManaAppleItem(), "mana_apple"));
        registerItem(CUSTOM_WRITTEN_BOOK = InitUtils.setup(new CustomWrittenBookItem(), "written_book"));
        registerItem(CUSTOM_WRITABLE_BOOK = InitUtils.setup(new CustomWritableBookItem(), "writable_book"));
    }
    private static void registerStatusEffects(){
        MAGICAL_EXHAUSTION = registerStatusEffect("poison", new MagicalExhaustionEffect());
    }

    private static RegistryEntry<StatusEffect> registerStatusEffect(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(PathOfMage.MOD_ID, "magical_exhaustion"), statusEffect);
    }

    public static void registerBlock(Block block, Item.Settings builder, Identifier name) {
        Registry.register(Registries.BLOCK, name, block);
        BlockItem itemBlock = new BlockItem(block, builder);
        Registry.register(Registries.ITEM, name, itemBlock);
    }

    public static void registerBlock(Block block, Function<Block, BlockItem> blockItemFunction, Identifier name) {
        Registry.register(Registries.BLOCK, name, block);
        BlockItem itemBlock = blockItemFunction.apply(block);
        Registry.register(Registries.ITEM, name, itemBlock);
    }

    public static void registerBlock(Block block, Item.Settings itemGroup) {
        Validate.isTrue(objIdentMap.containsKey(block));
        registerBlock(block, itemGroup, objIdentMap.get(block));
    }

    public static void registerBlock(Block block, Function<Block, BlockItem> blockItemFunction){
        Validate.isTrue(objIdentMap.containsKey(block));
        registerBlock(block, blockItemFunction, objIdentMap.get(block));
    }

    public static void registerBlockNoItem(Block block) {
        Validate.isTrue(objIdentMap.containsKey(block));
        Registry.register(Registries.BLOCK, objIdentMap.get(block), block);
    }

    public static void registerItem(Item item, Identifier name) {
        Registry.register(Registries.ITEM, name, item);
    }

    public static void registerItem(Item item){
        Validate.isTrue(objIdentMap.containsKey(item));
        registerItem(item, objIdentMap.get(item));
    }

    public static void registerIdent(Object object, Identifier identifier){
        objIdentMap.put(object, identifier);
    }
    private static void entries(FabricItemGroupEntries entries) {

    }
    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(BiFunction<BlockPos, BlockState, T> supplier, String name, ItemConvertible... items) {
        return registerBlockEntity(supplier, name, Arrays.stream(items).map(itemConvertible -> Block.getBlockFromItem(itemConvertible.asItem())).toArray(Block[]::new));
    }

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(BiFunction<BlockPos, BlockState, T> supplier, String name, Block... blocks) {
        Validate.isTrue(blocks.length > 0, "no blocks for blockEntity entity type!");
        return registerBlockEntity(Identifier.of(PathOfMage.MOD_ID, name).toString(), FabricBlockEntityTypeBuilder.create(supplier::apply, blocks));
    }

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, FabricBlockEntityTypeBuilder<T> builder) {
        BlockEntityType<T> blockEntityType = builder.build(null);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(id), blockEntityType);
        TYPES.add(blockEntityType);
        return blockEntityType;
    }
}