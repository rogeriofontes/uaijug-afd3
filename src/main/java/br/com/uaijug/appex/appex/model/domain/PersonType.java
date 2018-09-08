package br.com.uaijug.appex.appex.model.domain;

public enum PersonType {
	LEGAL(1, "Pessoa Juridica"), PHYSICAL(2, "Pessoa Fisica");

	private int ordinal;
	private String name;

	private PersonType(int ordinal, String name) {
		this.ordinal = ordinal;
		this.name = name;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public String getName() {
		return name;
	}

	public String getNameById(int id) {
		for (PersonType personType : PersonType.values()) {
			if (personType.getOrdinal() == id) {
				return personType.getName();
			}
		}
		return null;
	}
}
