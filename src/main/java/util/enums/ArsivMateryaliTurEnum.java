package util.enums;

public enum ArsivMateryaliTurEnum {
	KARTOGRAFIK (1, "Kartografik"), 
	SES_KAYDI (2, "Ses Kaydı"), 
	VIDEO_KAYDI (3, "Video Kaydı"), 
	NESNE (4, "Nesne"), 
	FOTOGRAF (5, "Fotoğraf");

	private final int value;
	private final String label;

	ArsivMateryaliTurEnum(int value, String label) {
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

		for (ArsivMateryaliTurEnum enumElement : ArsivMateryaliTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

