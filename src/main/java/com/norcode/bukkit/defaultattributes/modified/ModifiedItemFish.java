package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemFish;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemFish extends ItemFish {

	private static Field isCookedField;
	static {
		try {
			isCookedField = ItemFish.class.getDeclaredField("b");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		isCookedField.setAccessible(true);
	}

	private final Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemFish(ItemFish item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Boolean) isCookedField.get(item));
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
