package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.ItemReed;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemReed extends ItemReed {
	Multimap<String, AttributeModifier> modifiers;

	protected static Field blockField;
	static {
		try {
			blockField = ItemReed.class.getDeclaredField("block");
			blockField.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	public ModifiedItemReed(ItemReed item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Block) blockField.get(item));
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
