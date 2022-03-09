package util.enums;

public enum SozlukKullanimSekliEnum {
	DIZIN (1, "Dizin"), 
	SOZLUK (2, "Sözlük");

	private final int value;
	private final String label;

	SozlukKullanimSekliEnum(int value, String label) {
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

		for (SozlukKullanimSekliEnum enumElement : SozlukKullanimSekliEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

