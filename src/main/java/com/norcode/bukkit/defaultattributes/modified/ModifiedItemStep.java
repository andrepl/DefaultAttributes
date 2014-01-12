package com.norcode.bukkit.defaultattributes.modified;

import net.minecraft.server.v1_7_R1.AttributeModifier;
import net.minecraft.server.v1_7_R1.Block;
import net.minecraft.server.v1_7_R1.BlockStepAbstract;
import net.minecraft.server.v1_7_R1.ItemStep;
import net.minecraft.util.com.google.common.collect.Multimap;

import java.lang.reflect.Field;

public class ModifiedItemStep extends ItemStep {

	private static Field bField;
	private static Field cField;
	private static Field dField;

	static {
		try {
			bField = ItemStep.class.getDeclaredField("b");
			cField = ItemStep.class.getDeclaredField("c");
			dField = ItemStep.class.getDeclaredField("d");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		bField.setAccessible(true);
		cField.setAccessible(true);
		dField.setAccessible(true);
	}

	private final Multimap<String, AttributeModifier> modifiers;

	public ModifiedItemStep(ItemStep item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		super((Block) ModifiedItemBlock.blockField.get(item), (BlockStepAbstract) cField.get(item), (BlockStepAbstract) dField.get(item), (Boolean) bField.get(item));
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
