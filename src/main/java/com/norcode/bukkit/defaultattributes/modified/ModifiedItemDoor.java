package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemDoor;
import net.minecraft.server.v1_7_R1.Material;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemDoor extends ItemDoor {
	private final Multimap<String, AttributeModifier> modifiers;

	private static Field materialField;
	static {
		try {
			materialField = ItemDoor.class.getDeclaredField("a");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		materialField.setAccessible(true);
	}
	public ModifiedItemDoor(ItemDoor door, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Material) materialField.get(door));
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
