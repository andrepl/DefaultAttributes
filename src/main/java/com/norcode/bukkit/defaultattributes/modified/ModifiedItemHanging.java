package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemHanging;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemHanging extends ItemHanging {
	Multimap<String, AttributeModifier> modifiers;

	protected static Field entityClassField;
	static {
		try {
			entityClassField = ItemHanging.class.getDeclaredField("a");
			entityClassField .setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	public ModifiedItemHanging(ItemHanging item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Class) entityClassField.get(item));
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
