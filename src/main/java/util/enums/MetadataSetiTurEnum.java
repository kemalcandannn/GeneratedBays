package util.enums;

public enum MetadataSetiTurEnum {
	NESNE (11, "Nesne"), 
	ARSIV_KURUMLARI (1, "Arşiv Kurumları"), 
	FOTOGRAF (12, "Fotoğraf"), 
	ARSIV_SAHIBI (2, "Arşiv Sahibi"), 
	FON (3, "Fon"), 
	KLASOR (4, "Klasör"), 
	GOMLEK (5, "Gömlek"), 
	DEFTER (6, "Defter"), 
	DEFTER_SAYFASI (7, "Defter Sayfası"), 
	KARTOGRAFIK (8, "Kartografik"), 
	SES_KAYDI (9, "Ses Kaydı"), 
	VIDEO_KAYDI (10, "Video Kaydı");

	private final int value;
	private final String label;

	MetadataSetiTurEnum(int value, String label) {
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

		for (MetadataSetiTurEnum enumElement : MetadataSetiTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

