package util.enums;

public enum OnayRedEnum {
	ONAY (1, "Onay"), 
	RED (2, "Red");

	private final int value;
	private final String label;

	OnayRedEnum(int value, String label) {
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

		for (OnayRedEnum enumElement : OnayRedEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

