package util.enums;

public enum HamEvrakSaklanmaTuruEnum {
	PAKET (1, "Paket"), 
	RULO (2, "Rulo"), 
	KOLI (3, "Koli"), 
	TANIMSIZ_DEFTER (4, "Tanımsız Defter"), 
	CUVAL (5, "Çuval"), 
	TANIMSIZ_KLASOR (6, "Tanımsız Klasır"), 
	SANDIK (7, "Sand�k"), 
	KASA (8, "Kasa");

	private final int value;
	private final String label;

	HamEvrakSaklanmaTuruEnum(int value, String label) {
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

		for (HamEvrakSaklanmaTuruEnum enumElement : HamEvrakSaklanmaTuruEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

