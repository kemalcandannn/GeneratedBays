package util.enums;

public enum CalismaGozuEnum {
	YOK (1, "Yok"), 
	DOLU (2, "Dolu"), 
	CALISMA_GOZU (3, "Çalışma Gözü"), 
	ARKASI_DOLU (4, "Arkası Dolu");

	private final int value;
	private final String label;

	CalismaGozuEnum(int value, String label) {
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

		for (CalismaGozuEnum enumElement : CalismaGozuEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

