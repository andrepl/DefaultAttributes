package com.norcode.bukkit.defaultattributes.modified;


import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Blocks;
import net.minecraft.server.v1_7_R1.ItemSnow;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ModifiedItemSnow extends ItemSnow {
	private final Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemSnow(ItemSnow item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super(Blocks.SNOW, Blocks.SNOW);
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
