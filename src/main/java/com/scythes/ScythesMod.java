package com.scythes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ScythesMod implements ModInitializer {
    public static final String MODID = "scythes";
    public static final int TOTAL_DAMAGE = 11;

    public static Item WOODEN, STONE, IRON, GOLDEN, DIAMOND, NETHERITE;

    private static Item scythe(String name, ToolMaterial mat, boolean fireproof) {
        int attackDamage = TOTAL_DAMAGE - 1 - (int) mat.getAttackDamage();
        Item.Settings s = new Item.Settings();
        if (fireproof) s = s.fireproof();
        return Registry.register(Registries.ITEM, new Identifier(MODID, name),
                new SwordItem(mat, attackDamage, -2.8f, s));
    }

    @Override
    public void onInitialize() {
        WOODEN = scythe("wooden_scythe", ToolMaterials.WOOD, false);
        STONE = scythe("stone_scythe", ToolMaterials.STONE, false);
        IRON = scythe("iron_scythe", ToolMaterials.IRON, false);
        GOLDEN = scythe("golden_scythe", ToolMaterials.GOLD, false);
        DIAMOND = scythe("diamond_scythe", ToolMaterials.DIAMOND, false);
        NETHERITE = scythe("netherite_scythe", ToolMaterials.NETHERITE, true);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(e -> {
            e.add(WOODEN); e.add(STONE); e.add(IRON);
            e.add(GOLDEN); e.add(DIAMOND); e.add(NETHERITE);
        });
    }
}
