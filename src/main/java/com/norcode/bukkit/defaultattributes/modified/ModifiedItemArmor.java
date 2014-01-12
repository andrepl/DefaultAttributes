package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemArmor;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ModifiedItemArmor extends ItemArmor {
	private final Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemArmor(ItemArmor armor, Multimap<String, AttributeModifier> modifiers) {
		super(armor.m_(), armor.d, armor.b);
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
