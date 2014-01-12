package com.norcode.bukkit.defaultattributes.modified;

import com.norcode.bukkit.defaultattributes.DefaultAttributes;
import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemSpade;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ModifiedItemSpade extends ItemSpade {

	Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemSpade(ItemSpade item, Multimap<String, AttributeModifier> modifiers) {
		super(DefaultAttributes.getToolMaterial(item));
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
