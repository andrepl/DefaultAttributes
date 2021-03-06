package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.BlockTallPlant;
import net.minecraft.server.v1_7_R1.ItemTallPlant;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ModifiedItemTallPlant extends ItemTallPlant {
	private final Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemTallPlant(ItemTallPlant item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Block) ModifiedItemBlock.blockField.get(item), (BlockTallPlant) ModifiedItemMultiTexture.block2Field.get(item), (String[]) ModifiedItemMultiTexture.stringArrayField.get(item));
		this.modifiers = modifiers;
	}

	@Override
	public Multimap k() {
		Multimap localMultimap = super.k();

		for (String s: modifiers.keySet()) {
			localMultimap.putAll(s, modifiers.get(s));
		}
		return localMultimap;
	}

}
