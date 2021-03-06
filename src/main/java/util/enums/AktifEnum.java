package util.enums;

public enum AktifEnum {
	AKTIF (1, "Aktif"), 
	PASIF (2, "Pasif");

	private final int value;
	private final String label;

	AktifEnum(int value, String label) {
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

		for (AktifEnum enumElement : AktifEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

