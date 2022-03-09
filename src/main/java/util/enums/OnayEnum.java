package util.enums;

public enum OnayEnum {
	ONAYLI (1, "Onaylı"), 
	ONAYSIZ (2, "Onaysız");

	private final int value;
	private final String label;

	OnayEnum(int value, String label) {
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

		for (OnayEnum enumElement : OnayEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

