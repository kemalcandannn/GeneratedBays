package util.enums;

public enum GomlekBakimDurumEnum {
	TALEP_EDILDI (1, "Talep Edildi"), 
	ATANDI (2, "Atandı"), 
	GOMLEK_YENILENDI (3, "Gömlek Yenilendi");

	private final int value;
	private final String label;

	GomlekBakimDurumEnum(int value, String label) {
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

		for (GomlekBakimDurumEnum enumElement : GomlekBakimDurumEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

