package util.enums;

public enum GomlekRestorasyonIhtiyaciEnum {
	YOK (1, "Yok"), 
	SEVIYE_1 (2, "Seviye 1"), 
	SEVIYE_2 (3, "Seviye 2"), 
	SEVIYE_3 (4, "Seviye 3");

	private final int value;
	private final String label;

	GomlekRestorasyonIhtiyaciEnum(int value, String label) {
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

		for (GomlekRestorasyonIhtiyaciEnum enumElement : GomlekRestorasyonIhtiyaciEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

