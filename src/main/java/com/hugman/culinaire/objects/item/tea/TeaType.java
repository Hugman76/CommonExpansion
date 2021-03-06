package com.hugman.culinaire.objects.item.tea;

import com.hugman.culinaire.init.CulinaireTeaBundle;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TeaType {
	private final Strength strength;
	private final Flavor flavor;

	public TeaType(String strength, String flavor) {
		this(Strength.byName(strength), Flavor.byName(flavor));
	}

	public TeaType(Strength strength, Flavor flavor) {
		this.strength = strength;
		this.flavor = flavor;
	}

	public boolean isCorrect() {
		return this.getStrength() != null && this.getFlavor() != null;
	}

	public Strength getStrength() {
		return strength;
	}

	public Flavor getFlavor() {
		return flavor;
	}

	public Tag<Item> getTag() {
		return TagRegistry.item(new Identifier("c", "tea_ingredients/" + getFlavor().getName() + "/" + getStrength().getName()));
	}

	public int getBrewTime() {
		return flavor.getBrewTime() * strength.getPotency();
	}

	@Override
	public String toString() {
		return "TeaType{" + "strength=" + strength + ", flavor=" + flavor + '}';
	}

	public enum Flavor {
		SWEET("sweet", 9523743, CulinaireTeaBundle.FULFILLMENT),
		UMAMI("umami", 10059295, StatusEffects.RESISTANCE),
		SALTY("salty", 10251038, CulinaireTeaBundle.GUARD),
		SOUR("sour", 7238946, CulinaireTeaBundle.POISON_RESISTANCE),
		BITTER("bitter", 5057061, CulinaireTeaBundle.FORESIGHT),
		SHINING("shining", 16759902, StatusEffects.GLOWING),
		GLOOPY("gloopy", 9332621);

		private final String name;

		private final int color;
		private final int brewTime;
		private final StatusEffect effect;

		Flavor(String name, int color) {
			this(name, color, null);
		}

		Flavor(String name, int color, StatusEffect effect) {
			this(name, color, 200, effect);
		}

		Flavor(String name, int color, int brewTime, StatusEffect effect) {
			this.name = name;
			this.brewTime = brewTime;
			this.color = color;
			this.effect = effect;
		}

		public static Flavor byName(String name) {
			for(Flavor flavor : Flavor.values()) {
				if(flavor.getName().equals(name)) {
					return flavor;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public StatusEffect getEffect() {
			return effect;
		}

		public int getColor() {
			return color;
		}

		public int getBrewTime() {
			return brewTime;
		}
	}

	public enum Strength {
		WEAK("weak", 1),
		NORMAL("normal", 2),
		STRONG("strong", 3);

		private final String name;
		private final int potency;

		Strength(String name, int potency) {
			this.name = name;
			this.potency = potency;
		}

		public static Strength byName(String name) {
			for(Strength strength : Strength.values()) {
				if(strength.getName().equals(name)) {
					return strength;
				}
			}
			return null;
		}

		public static Strength byPotency(int potency) {
			for(Strength strength : Strength.values()) {
				if(strength.getPotency() == potency) {
					return strength;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public int getPotency() {
			return potency;
		}
	}
}
