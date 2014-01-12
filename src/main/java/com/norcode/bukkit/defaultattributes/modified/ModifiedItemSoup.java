package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemSoup;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ModifiedItemSoup extends ItemSoup {
	Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemSoup(ItemSoup item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Integer) ModifiedItemFood.b.get(item));
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
