package com.norcode.bukkit.defaultattributes;

import com.norcode.bukkit.defaultattributes.modified.*;
import net.minecraft.server.v1_7_R1.*;
import net.minecraft.util.com.google.common.collect.HashMultimap;
import net.minecraft.util.com.google.common.collect.Multimap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;


public class DefaultAttributes extends JavaPlugin {

	private static Field nameField;
	private static Field altNameField;
	private static Field toolMaterialField;
	private static Field hoeMaterialField;
	private static Field creativeModeTabField;
	private static Field durabilityField;
	private static Field itemIField;
	public static Field itemJField;
	private static Field itemDField;
	private static Field craftingResultField;

	@Override
	public void onEnable() {
		try {
			nameField = Item.class.getDeclaredField("name");
			nameField.setAccessible(true);
			altNameField = Item.class.getDeclaredField("l");
			altNameField.setAccessible(true);
			toolMaterialField = ItemTool.class.getDeclaredField("b");
			toolMaterialField.setAccessible(true);
			hoeMaterialField = ItemHoe.class.getDeclaredField("a");
			hoeMaterialField.setAccessible(true);
			creativeModeTabField = Item.class.getDeclaredField("a");
			creativeModeTabField.setAccessible(true);
			durabilityField = Item.class.getDeclaredField("durability");
			durabilityField.setAccessible(true);
			itemIField = Item.class.getDeclaredField("i");
			itemIField.setAccessible(true);
			itemJField = Item.class.getDeclaredField("j");
			itemJField.setAccessible(true);
			itemDField = Item.class.getDeclaredField("d");
			itemDField.setAccessible(true);
			craftingResultField = Item.class.getDeclaredField("craftingResult");
			craftingResultField.setAccessible(true);

		} catch (NoSuchFieldException ex) {
			ex.printStackTrace();
		}
		saveDefaultConfig();
		loadConfig();
	}

	private static final HashMap<String, IAttribute> attributes = new HashMap<String, IAttribute>();

	public void loadConfig() {
		ConfigurationSection cfg = getConfig().getConfigurationSection("items");
		for (String itemName: cfg.getKeys(false)) {
			ConfigurationSection itemCfg = cfg.getConfigurationSection(itemName);
			Multimap modifiers = HashMultimap.create();
			for (String modifierName: itemCfg.getKeys(false)) {
				ConfigurationSection modCfg = itemCfg.getConfigurationSection(modifierName);
				UUID uuid;
				if (modCfg.contains("uuid")) {
					uuid = UUID.fromString(modCfg.getString("uuid"));
				} else {
					uuid = UUID.randomUUID();
					getLogger().warning("Generated a random UUID(" + uuid.toString() + ") for " + itemName + "." + modifierName);
					modCfg.set("uuid", uuid.toString());
				}
				int op = modCfg.getInt("operation", 0);
				double amount = modCfg.getDouble("amount");
				modifiers.put(modCfg.getString("attribute"), new AttributeModifier(uuid, modifierName, amount, op));
			}
			Item item = (Item) Item.REGISTRY.a(itemName);
			Item newItem;
			try {
				newItem = getNewItem(item, modifiers);
				int id = Item.REGISTRY.b(item);
				Item.REGISTRY.a(id, itemName, newItem);
			} catch (IllegalAccessException e) {
				getLogger().severe("Failed to Initialize Modified" + item.getClass().getSimpleName());
				e.printStackTrace();
			}
		}
		saveConfig();
	}

	private Item getNewItem(Item item, Multimap<String, AttributeModifier> modifiers) throws IllegalAccessException {
		Item newItem = null;
		if (item instanceof ItemAnvil) {
			newItem = new ModifiedItemAnvil((ItemAnvil) item, modifiers);
		} else if (item instanceof ItemArmor) {
			newItem = new ModifiedItemArmor((ItemArmor) item, modifiers);
		} else if (item instanceof ItemAxe) {
			newItem = new ModifiedItemAxe((ItemAxe) item, modifiers);
		} else if (item instanceof ItemBed) {
			newItem = new ModifiedItemBed((ItemBed) item, modifiers);
		} else if (item instanceof ItemBoat) {
			newItem = new ModifiedItemBoat((ItemBoat) item, modifiers);
		} else if (item instanceof ItemBookAndQuill) {
			newItem = new ModifiedItemBookAndQuill((ItemBookAndQuill) item, modifiers);
		} else if (item instanceof ItemBow) {
			newItem = new ModifiedItemBow((ItemBow) item, modifiers);
		} else if (item instanceof ItemCarrotStick) {
			newItem = new ModifiedItemCarrotStick((ItemCarrotStick) item, modifiers);
		} else if (item instanceof ItemCloth) {
			newItem = new ModifiedItemCloth((ItemCloth) item, modifiers);
		} else if (item instanceof ItemCoal) {
			newItem = new ModifiedItemCoal((ItemCoal) item, modifiers);
		} else if (item instanceof ItemDoor) {
			newItem = new ModifiedItemDoor((ItemDoor) item, modifiers);
		} else if (item instanceof ItemDye) {
			newItem = new ModifiedItemDye((ItemDye) item, modifiers);
		} else if (item instanceof ItemEgg) {
			newItem = new ModifiedItemEgg((ItemEgg) item, modifiers);
		} else if (item instanceof ItemEnchantedBook) {
			newItem = new ModifiedItemEnchantedBook((ItemEnchantedBook) item, modifiers);
		} else if (item instanceof ItemEnderEye) {
			newItem = new ModifiedItemEnderEye((ItemEnderEye) item, modifiers);
		} else if (item instanceof ItemEnderPearl) {
			newItem = new ModifiedItemEnderPearl((ItemEnderPearl) item, modifiers);
		} else if (item instanceof ItemExpBottle) {
			newItem = new ModifiedItemExpBottle((ItemExpBottle) item, modifiers);
		} else if (item instanceof ItemFireball) {
			newItem = new ModifiedItemFireball((ItemFireball) item, modifiers);
		} else if (item instanceof ItemFireworksCharge) {
			newItem = new ModifiedItemFireworksCharge((ItemFireworksCharge) item, modifiers);
		} else if (item instanceof ItemFireworks) {
			newItem = new ModifiedItemFireworks((ItemFireworks) item, modifiers);
		} else if (item instanceof ItemFishingRod) {
			newItem = new ModifiedItemFishingRod((ItemFishingRod) item, modifiers);
		} else if (item instanceof ItemFish) {
			newItem = new ModifiedItemFish((ItemFish) item, modifiers);
		} else if (item instanceof ItemFlintAndSteel) {
			newItem = new ModifiedItemFlintAndSteel((ItemFlintAndSteel) item, modifiers);
		} else if (item instanceof ItemGlassBottle) {
			newItem = new ModifiedItemGlassBottle((ItemGlassBottle) item, modifiers);
		} else if (item instanceof ItemGoldenApple) {
			newItem = new ModifiedItemGoldenApple((ItemGoldenApple) item, modifiers);
		} else if (item instanceof ItemHanging) {
			newItem = new ModifiedItemHanging((ItemHanging) item, modifiers);
		} else if (item instanceof ItemHoe) {
			newItem = new ModifiedItemHoe((ItemHoe) item, modifiers);
		} else if (item instanceof ItemLeash) {
			newItem = new ModifiedItemLeash((ItemLeash) item, modifiers);
		} else if (item instanceof ItemLeaves) {
			newItem = new ModifiedItemLeaves((ItemLeaves) item, modifiers);
		} else if (item instanceof ItemMapEmpty) {
			newItem = new ModifiedItemMapEmpty((ItemMapEmpty) item, modifiers);
		} else if (item instanceof ItemMilkBucket) {
			newItem = new ModifiedItemMilkBucket((ItemMilkBucket) item, modifiers);
		} else if (item instanceof ItemMinecart) {
			newItem = new ModifiedItemMinecart((ItemMinecart) item, modifiers);
		} else if (item instanceof ItemMonsterEgg) {
			newItem = new ModifiedItemMonsterEgg((ItemMonsterEgg) item, modifiers);
		} else if (item instanceof ItemNameTag) {
			newItem = new ModifiedItemNameTag((ItemNameTag) item, modifiers);
		} else if (item instanceof ItemNetherStar) {
			newItem = new ModifiedItemNetherStar((ItemNetherStar) item, modifiers);
		} else if (item instanceof ItemPickaxe) {
			newItem = new ModifiedItemPickaxe((ItemPickaxe) item, modifiers);
		} else if (item instanceof ItemPiston) {
			newItem = new ModifiedItemPiston((ItemPiston) item, modifiers);
		} else if (item instanceof ItemPotion) {
			newItem = new ModifiedItemPotion((ItemPotion) item, modifiers);
		} else if (item instanceof ItemRecord) {
			newItem = new ModifiedItemRecord((ItemRecord) item, modifiers);
		} else if (item instanceof ItemRedstone) {
			newItem = new ModifiedItemRedstone((ItemRedstone) item, modifiers);
		} else if (item instanceof ItemReed) {
			newItem = new ModifiedItemReed((ItemReed) item, modifiers);
		} else if (item instanceof ItemSaddle) {
			newItem = new ModifiedItemSaddle((ItemSaddle) item, modifiers);
		} else if (item instanceof ItemSeedFood) {
			newItem = new ModifiedItemSeedFood((ItemSeedFood) item, modifiers);
		} else if (item instanceof ItemSeeds) {
			newItem = new ModifiedItemSeeds((ItemSeeds) item, modifiers);
		} else if (item instanceof ItemShears) {
			newItem = new ModifiedItemShears((ItemShears) item, modifiers);
		} else if (item instanceof ItemSign) {
			newItem = new ModifiedItemSign((ItemSign) item, modifiers);
		} else if (item instanceof ItemSkull) {
			newItem = new ModifiedItemSkull((ItemSkull) item, modifiers);
		} else if (item instanceof ItemSnowball) {
			newItem = new ModifiedItemSnowball((ItemSnowball) item, modifiers);
		} else if (item instanceof ItemSnow) {
			newItem = new ModifiedItemSnow((ItemSnow) item, modifiers);
		} else if (item instanceof ItemSoup) {
			newItem = new ModifiedItemSoup((ItemSoup) item, modifiers);
		} else if (item instanceof ItemSpade) {
			newItem = new ModifiedItemSpade((ItemSpade) item, modifiers);
		} else if (item instanceof ItemStep) {
			newItem = new ModifiedItemStep((ItemStep) item, modifiers);
		} else if (item instanceof ItemSword) {
			newItem = new ModifiedItemSword((ItemSword) item, modifiers);
		} else if (item instanceof ItemTallPlant) {
			newItem = new ModifiedItemTallPlant((ItemTallPlant) item, modifiers);
		} else if (item instanceof ItemWaterLily) {
			newItem = new ModifiedItemWaterLily((ItemWaterLily) item, modifiers);
		} else if (item instanceof ItemWorldMap) {
			newItem = new ModifiedItemWorldMap((ItemWorldMap) item, modifiers);
		} else if (item instanceof ItemWrittenBook) {
			newItem = new ModifiedItemWrittenBook((ItemWrittenBook) item, modifiers);
		} else if (item instanceof ItemBook) {
			newItem = new ModifiedItemBook((ItemBook) item, modifiers);
		} else if (item instanceof ItemBucket) {
			newItem = new ModifiedItemBucket((ItemBucket) item, modifiers);
		} else if (item instanceof ItemFood) {
			newItem = new ModifiedItemFood((ItemFood) item, modifiers);
		} else if (item instanceof ItemMultiTexture) {
			newItem = new ModifiedItemMultiTexture((ItemMultiTexture) item, modifiers);
		} else if (item instanceof ItemWithAuxData) {
			newItem = new ModifiedItemWithAuxData((ItemWithAuxData) item, modifiers);
		} else if (item instanceof ItemBlock) {
			newItem = new ModifiedItemBlock((ItemBlock) item, modifiers);
		} else if (item instanceof Item) {
			newItem = new ModifiedItem(item, modifiers);
		}

		itemCopy(item, newItem);

		return newItem;
	}

	private void setItemAltName(Item item, String itemAltName) {
		try {
			altNameField.set(item, itemAltName);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static String getItemName(Item item) {
		try {
			return (String) nameField.get(item);
		} catch (IllegalAccessException e) {
			return item.getName().substring(5);
		}
	}

	public static String getItemAltName(Item item) {
		try {
			return (String) altNameField.get(item);
		} catch (IllegalAccessException ex) {
			return underscore(getItemName(item));
		}
	}

	private static String underscore(String itemName) {
		StringBuilder builder = new StringBuilder();
		for (int i=0;i<itemName.length();i++) {
			char c = itemName.charAt(i);
			if (Character.isUpperCase(c)) {
				builder.append("_");
				builder.append(Character.toLowerCase(c));
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}

	public static EnumToolMaterial getToolMaterial(Item item) {

		try {
			if (item instanceof ItemHoe) {
				return (EnumToolMaterial) hoeMaterialField.get(item);
			} else {
				return (EnumToolMaterial) toolMaterialField.get(item);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return EnumToolMaterial.WOOD;
		}
	}

	public static CreativeModeTab getCreativeModeTab(Item item) {
		try {
			return (CreativeModeTab) creativeModeTabField.get(item);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return CreativeModeTab.b;
		}
	}

	public void itemCopy(Item original, Item newItem) {
		newItem.e(original.getMaxStackSize());
		newItem.a(getCreativeModeTab(original));
		newItem.c(getItemName(original));
		setItemAltName(newItem, getItemAltName(original));
		try {
			craftingResultField.set(newItem, craftingResultField.get(original));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			itemIField.set(newItem, itemIField.get(original));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			itemDField.set(newItem, itemDField.get(original));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			itemJField.set(newItem, itemJField.get(original));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			durabilityField.set(newItem, original.getMaxDurability());
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}

	}
}
