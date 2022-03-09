package util.enums;

public enum HamEvrakBirimYonEnum {
	BIRIME_GITTI (1, "Birime Gitti"), 
	BIRIMDEN_GELDI (2, "Birimden Geldi");

	private final int value;
	private final String label;

	HamEvrakBirimYonEnum(int value, String label) {
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

		for (HamEvrakBirimYonEnum enumElement : HamEvrakBirimYonEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

