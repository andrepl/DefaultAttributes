package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemGoldenApple;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ModifiedItemGoldenApple extends ItemGoldenApple {
	Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemGoldenApple(ItemGoldenApple item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Integer) ModifiedItemFood.b.get(item), (Float) ModifiedItemFood.c.get(item), (Boolean) ModifiedItemFood.d.get(item));
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
