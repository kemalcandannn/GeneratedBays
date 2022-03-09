package util.enums;

public enum DefterSayfaTurEnum {
	MADDE (1, "Madde"), 
	SAYFA (2, "Sayfa");

	private final int value;
	private final String label;

	DefterSayfaTurEnum(int value, String label) {
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

		for (DefterSayfaTurEnum enumElement : DefterSayfaTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

