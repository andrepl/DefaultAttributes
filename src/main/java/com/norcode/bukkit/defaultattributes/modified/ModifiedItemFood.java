package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.ItemFood;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemFood extends ItemFood {

	Multimap<String, AttributeModifier> modifiers;
	public static Field b;
	public static Field c;
	public static Field d;

	static {
		try {
			b = ItemFood.class.getDeclaredField("b");
			b.setAccessible(true);
			c = ItemFood.class.getDeclaredField("c");
			c.setAccessible(true);
			d = ItemFood.class.getDeclaredField("d");
			d.setAccessible(true);
		} catch (NoSuchFieldException ex) {
			ex.printStackTrace();
		}

	}
	public ModifiedItemFood(ItemFood item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Integer) b.get(item), (Float) c.get(item), (Boolean) d.get(item));
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
