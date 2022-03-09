package util.enums;

public enum CalismaGrubuDurumEnum {
	HAZIRLANIYOR (1, "Hazırlanıyor"), 
	AKTIF (2, "Aktif"), 
	KAPANDI (3, "Kapandı");

	private final int value;
	private final String label;

	CalismaGrubuDurumEnum(int value, String label) {
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

		for (CalismaGrubuDurumEnum enumElement : CalismaGrubuDurumEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

