package util.enums;

public enum GizlilikEnum {
	COK_GIZLI (1, "Çok Gizli"), 
	GIZLI (2, "Gizli"), 
	ACIK (3, "Açık");

	private final int value;
	private final String label;

	GizlilikEnum(int value, String label) {
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

		for (GizlilikEnum enumElement : GizlilikEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

