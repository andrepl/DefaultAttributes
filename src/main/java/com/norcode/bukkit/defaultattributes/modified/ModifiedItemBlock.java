package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.ItemBlock;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemBlock extends ItemBlock {
	private final Multimap<String, AttributeModifier> modifiers;

	protected static Field blockField;
	static {
		try {
			blockField = ItemBlock.class.getDeclaredField("block");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		blockField.setAccessible(true);
	}
	public ModifiedItemBlock(ItemBlock item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Block) blockField.get(item));
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
