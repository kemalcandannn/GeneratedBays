package util.enums;

public enum DepoTalepMalzemeTurEnum {
	GOMLEK (1, "Gömlek"), 
	DEFTER (2, "Defter"), 
	ARSIV_MATERYALI (3, "Arşiv Materyali"), 
	KLASOR (4, "Klasör"), 
	ISLENMEMIS_KLASOR (5, "İşlenmemiş Klasör");

	private final int value;
	private final String label;

	DepoTalepMalzemeTurEnum(int value, String label) {
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

		for (DepoTalepMalzemeTurEnum enumElement : DepoTalepMalzemeTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

