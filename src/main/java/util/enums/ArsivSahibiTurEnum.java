package util.enums;

public enum ArsivSahibiTurEnum {
	YUKUMLU (1, "Yükümlü"), 
	OZEL (2, "Özel"), 
	YURT_DISI (3, "Yurt Dışı");

	private final int value;
	private final String label;

	ArsivSahibiTurEnum(int value, String label) {
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

		for (ArsivSahibiTurEnum enumElement : ArsivSahibiTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

