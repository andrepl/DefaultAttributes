package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.ItemBucket;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemBucket extends ItemBucket {
	protected static Field blockField;
	static {
		try {
			blockField = ItemBucket.class.getDeclaredField("a");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		blockField.setAccessible(true);
	}
	private final Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemBucket(ItemBucket item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
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
