package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemEnchantedBook;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ModifiedItemEnchantedBook extends ItemEnchantedBook {
	private final Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemEnchantedBook(ItemEnchantedBook item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super();
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
