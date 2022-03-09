package util.enums;

public enum CrudEnum {
	EKLEME (1, "Ekleme"), 
	GUNCELLEME (2, "Güncelleme"), 
	DEPOYA_TESLIM_EDILDI (3, "Depoya Teslim Edildi"), 
	DEPOYA_ILK_DEFA_YERLESTIRILDI (4, "Depoya ilk Defa Yerleştirildi");

	private final int value;
	private final String label;

	CrudEnum(int value, String label) {
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

		for (CrudEnum enumElement : CrudEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

