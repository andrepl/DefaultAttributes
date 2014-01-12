package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.ItemMultiTexture;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemMultiTexture extends ItemMultiTexture {
	private final Multimap<String, AttributeModifier> modifiers;

	public static Field block2Field;
	public static Field stringArrayField;

	static {
		try {
			block2Field = ItemMultiTexture.class.getDeclaredField("b");
			stringArrayField = ItemMultiTexture.class.getDeclaredField("c");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		block2Field.setAccessible(true);
		stringArrayField.setAccessible(true);
	}

	public ModifiedItemMultiTexture(ItemMultiTexture item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Block) ModifiedItemBlock.blockField.get(item), (Block) block2Field.get(item), (String[]) stringArrayField.get(item));
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
