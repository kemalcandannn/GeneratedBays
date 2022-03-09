package util.enums;

public enum GomlekBakimKlasorDurumEnum {
	PLANLANDI (1, "Planlandı"), 
	ATANDI (2, "Atandı"), 
	YENILENDI (3, "Yenilendi");

	private final int value;
	private final String label;

	GomlekBakimKlasorDurumEnum(int value, String label) {
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

		for (GomlekBakimKlasorDurumEnum enumElement : GomlekBakimKlasorDurumEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

