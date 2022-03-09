package util.enums;

public enum GomlekDefterTarihTurEnum {
	MILADI (1, "Miladi"), 
	HICRI (2, "Hicri"), 
	RUMI (3, "Rumi");

	private final int value;
	private final String label;

	GomlekDefterTarihTurEnum(int value, String label) {
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

		for (GomlekDefterTarihTurEnum enumElement : GomlekDefterTarihTurEnum.values()) {
			if (enumElement.getValue() == val) {
				label = enumElement.getLabel();
				break;
			}
		}

		return label;
	}

}

