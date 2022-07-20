package tn.esprit.vbank.enums;

public enum TypeFeeling {
	
	Negative("Negative"),
	Positive("Positive");
	
	private String typeFeeling;
	
	TypeFeeling(String typeFeeling) {
		this.typeFeeling = typeFeeling;
	}
	
	public String getName() {
		return typeFeeling;
	}
}
