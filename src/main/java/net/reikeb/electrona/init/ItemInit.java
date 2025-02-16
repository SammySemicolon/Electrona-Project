package net.reikeb.electrona.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.reikeb.electrona.Electrona;
import net.reikeb.electrona.items.*;
import net.reikeb.electrona.setup.ItemGroups;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Electrona.MODID);

    // Normal items
    public static final RegistryObject<Item> ADVANCED_TOTEM_OF_UNDYING = ITEMS.register("advanced_totem_of_undying", AdvancedTotemOfUndying::new);

    // Mechanic items
    public static final RegistryObject<Item> PORTABLE_BATTERY = ITEMS.register("portable_battery", PortableBattery::new);
    public static final RegistryObject<Item> ELECTRONIC_CIRCUIT = ITEMS.register("electronic_circuit", ElectronicCircuit::new);
    public static final RegistryObject<Item> MOTOR = ITEMS.register("motor", Motor::new);
    public static final RegistryObject<Item> PADDLE = ITEMS.register("paddle", Paddle::new);

    // Ore items
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", TinIngot::new);
    public static final RegistryObject<Item> TIN_PLATE = ITEMS.register("tin_plate", TinPlate::new);
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", SteelIngot::new);
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate", SteelPlate::new);

    // Tools and armors
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", Hammer::new);
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", SteelAxe::new);
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", SteelHoe::new);
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", SteelPickaxe::new);
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", SteelShovel::new);
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", SteelSword::new);
    public static final RegistryObject<Item> MECHANIC_WINGS = ITEMS.register("mechanic_wings", MechanicWings::new);

    // Generators - BlockItems
    public static final RegistryObject<Item> SOLAR_PANEL_T_1_ITEM = ITEMS.register("solar_panel_tiers1", () ->
            new BlockItem(BlockInit.SOLAR_PANEL_T_1.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_MACHINES)));
    public static final RegistryObject<Item> SOLAR_PANEL_T_2_ITEM = ITEMS.register("solar_panel_tiers2", () ->
            new BlockItem(BlockInit.SOLAR_PANEL_T_2.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_MACHINES)));
    public static final RegistryObject<Item> WATER_TURBINE_ITEM = ITEMS.register("water_turbine", () ->
            new BlockItem(BlockInit.WATER_TURBINE.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_MACHINES)));
    public static final RegistryObject<Item> HEAT_GENERATOR_ITEM = ITEMS.register("heat_generator", () ->
            new BlockItem(BlockInit.HEAT_GENERATOR.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_MACHINES)));

    // Machines - BlockItems
    public static final RegistryObject<Item> BATTERY_ITEM = ITEMS.register("battery", () ->
            new BlockItem(BlockInit.BATTERY.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_MACHINES)));
    public static final RegistryObject<Item> COMPRESSOR_ITEM = ITEMS.register("compressor", () ->
            new BlockItem(BlockInit.COMPRESSOR.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_MACHINES)));

    // Others - BlockItems
    public static final RegistryObject<Item> TIN_ORE_ITEM = ITEMS.register("tin_ore", () ->
            new BlockItem(BlockInit.TIN_ORE.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_BLOCKS)));
    public static final RegistryObject<Item> TiN_BLOCK_ITEM = ITEMS.register("tin_block", () ->
            new BlockItem(BlockInit.TIN_BLOCK.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_BLOCKS)));
    public static final RegistryObject<Item> STEEL_BLOCK_ITEM = ITEMS.register("steel_block", () ->
            new BlockItem(BlockInit.STEEL_BLOCK.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_BLOCKS)));
    public static final RegistryObject<Item> MACHINE_CASING_ITEM = ITEMS.register("machine_casing", () ->
            new BlockItem(BlockInit.MACHINE_CASING.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_BLOCKS)));
    public static final RegistryObject<Item> COMPRESSED_OBSIDIAN_ITEM = ITEMS.register("compressed_obsidian", () ->
            new BlockItem(BlockInit.COMPRESSED_OBSIDIAN.get(), new Item.Properties().tab(ItemGroups.ELECTRONA_BLOCKS)));
}
