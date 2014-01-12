package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.EnumToolMaterial;
import net.minecraft.server.v1_7_R1.ItemSword;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemSword extends ItemSword {

	static Field materialField;

	static {
		try {
			materialField = ItemSword.class.getDeclaredField("b");
			materialField.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemSword(ItemSword item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((EnumToolMaterial) materialField.get(item));
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
