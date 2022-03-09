package util.enums;

public enum ArsivSahibiustKurumIliskiEnum {
	YOK (1, "Yok"), 
	BAGLI (2, "Bağlı"), 
	ILGILI (3, "İlgili"), 
	ILISKILI (4, "İlişkili"), 
	MERKEZI (5, "Merkezi");

	private final int value;
	private final String label;

	ArsivSahibiustKurumIliskiEnum(int value, String label) {
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

		for (ArsivSahibiustKurumIliskiEnum enumElement : ArsivSahibiustKurumIliskiEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

