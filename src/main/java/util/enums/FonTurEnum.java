package util.enums;

public enum FonTurEnum {
	YOK (1, "Yok"), 
	GOMLEK (2, "Gömlek"), 
	DEFTER (3, "Defter"), 
	KARTOGRAFIK (4, "Kartografik"), 
	SES_KAYDI (5, "Ses Kaydı"), 
	VIDEO_KAYDI (6, "Video Kaydı"), 
	NESNE (7, "Nesne"), 
	FOTOGRAF (8, "Fotoğraf");

	private final int value;
	private final String label;

	FonTurEnum(int value, String label) {
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

		for (FonTurEnum enumElement : FonTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

