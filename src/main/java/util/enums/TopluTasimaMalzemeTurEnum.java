package util.enums;

public enum TopluTasimaMalzemeTurEnum {
	KLASOR (1, "Klasör"), 
	DEFTER (2, "Defter"), 
	ISLENMEMIS_KLASOR (3, "İşlenmemiş Klasör"), 
	ARSIV_MATERYALI (4, "Arşiv Materyali");

	private final int value;
	private final String label;

	TopluTasimaMalzemeTurEnum(int value, String label) {
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

		for (TopluTasimaMalzemeTurEnum enumElement : TopluTasimaMalzemeTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

