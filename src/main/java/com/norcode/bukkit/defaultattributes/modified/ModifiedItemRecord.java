package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemRecord;
import net.minecraft.util.com.google.common.collect.Multimap;

public class ModifiedItemRecord extends ItemRecord {
	private final Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemRecord(ItemRecord item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super(item.a);
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
