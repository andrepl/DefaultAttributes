package com.norcode.bukkit.defaultattributes.modified;

import com.norcode.bukkit.defaultattributes.DefaultAttributes;
import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.ItemWithAuxData;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemWithAuxData extends ItemWithAuxData {
	private final Multimap<String, AttributeModifier> modifiers;

	private static Field stringArrayField;
	private static Field blockField;

	static {
		try {
			blockField = ItemWithAuxData.class.getDeclaredField("b");
			stringArrayField = ItemWithAuxData.class.getDeclaredField("c");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		blockField.setAccessible(true);
		stringArrayField.setAccessible(true);

	}

	public ModifiedItemWithAuxData(ItemWithAuxData item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Block) blockField.get(item), (item.getMaxDurability() == 0 && ((Boolean) DefaultAttributes.itemJField.get(item))));
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
