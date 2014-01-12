package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.BlockLeaves;
import net.minecraft.server.v1_7_R1.ItemLeaves;
import net.minecraft.server.v1_7_R1.ItemMultiTexture;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemLeaves extends ItemLeaves {
	private final Multimap<String, AttributeModifier> modifiers;

	private static Field blockLeavesField;


	static {
		try {
			blockLeavesField = ItemMultiTexture.class.getDeclaredField("b");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		blockLeavesField.setAccessible(true);

	}

	public ModifiedItemLeaves(ItemLeaves item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((BlockLeaves) blockLeavesField.get(item));
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
