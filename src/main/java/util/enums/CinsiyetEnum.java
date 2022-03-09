package util.enums;

import util.BaysResource;

public enum CinsiyetEnum {
	ERKEK (1, "Erkek"), 
	KADIN (2, "Kadın");

	private final int value;
	private final String label;

	CinsiyetEnum(int value, String label) {
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

		for (CinsiyetEnum cinsiyetEnum : CinsiyetEnum.values()) {
			if (cinsiyetEnum.getValue() == val) {
				label = cinsiyetEnum.getLabel();
				break;
			}
		}

		return label;
	}

}