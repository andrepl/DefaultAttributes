package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.ItemSeeds;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemSeeds extends ItemSeeds {
	Multimap<String, AttributeModifier> modifiers;

	protected static Field blockField;
	protected static Field block2Field;
	static {
		try {
			blockField = ItemSeeds.class.getDeclaredField("block");
			blockField.setAccessible(true);
			block2Field = ItemSeeds.class.getDeclaredField("b");
			block2Field.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	public ModifiedItemSeeds(ItemSeeds item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Block) blockField.get(item), (Block) block2Field.get(item));
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
