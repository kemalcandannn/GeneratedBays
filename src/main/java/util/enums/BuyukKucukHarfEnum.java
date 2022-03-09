package util.enums;

import util.BaysResource;

public enum BuyukKucukHarfEnum {
	KUCUK (1, "Küçük"), 
	OLDUGU_GIBI (2, "Olduğu Gibi"),
	BUYUK (3, "Büyük");

	private final int value;
	private final String label;

	BuyukKucukHarfEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static String getLabel(int val) {
		String label = "";

		for (BuyukKucukHarfEnum buyukKucukHarfEnum : BuyukKucukHarfEnum.values()) {
			if (buyukKucukHarfEnum.getValue() == val) {
				label = buyukKucukHarfEnum.getLabel();
				break;
			}
		}

		return label;
	}

}