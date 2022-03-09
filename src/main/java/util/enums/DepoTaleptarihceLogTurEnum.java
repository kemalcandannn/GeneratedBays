package util.enums;

public enum DepoTaleptarihceLogTurEnum {
	RAFTA (1, "Rafta"), 
	TALEP_EDILDI (2, "Talep Edildi"), 
	RAFTAN_ALINDI (3, "Raftan Alındı"), 
	BIRIMDE (4, "Birimde"), 
	BIRIMDEN_GELDI (5, "Birimden Geldi"), 
	IPTAL_EDILDI (6, "İptal Edildi"), 
	TALEP_GERI_CEKILDI (7, "Talep Geri Çekildi"), 
	TALEP_IPTAL_EDILDI (8, "Talep İptal Edildi");

	private final int value;
	private final String label;

	DepoTaleptarihceLogTurEnum(int value, String label) {
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

		for (DepoTaleptarihceLogTurEnum enumElement : DepoTaleptarihceLogTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

