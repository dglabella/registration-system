package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public abstract class ServiceMessenger {

	private Messenger messenger;

	public ServiceMessenger(Messenger messenger) {
		this.messenger = messenger;
	}

	public Messenger getMessenger() {
		return this.messenger;
	}

	public String unspecifiedEntity(String entityName) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "need to specify: " + entityName;
				break;
			case "ES":
				ret = "es necesario especificar: " + entityName;
				break;
			default:
				ret = "need to specify: " + entityName;
		}

		return ret;
	}

	public String notFound(String entityName, int id) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "there is no '" + entityName + "' with id: " + id;
				break;
			case "ES":
				ret = "no existe '" + entityName + "' con id " + id;
				break;
			default:
				ret = "there is no '" + entityName + "' with id: " + id;
		}
		return ret;
	}

	public String updateNonExistentEntity(String entityName, int id) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "cannot update '" + entityName + "' with id " + id
						+ " because it doesn't exist";
				break;
			case "ES":
				ret = "no puede actualizarse '" + entityName + "' con id " + id
						+ " porque no existe";
				break;
			default:
				ret = "cannot update '" + entityName + "' with id " + id
						+ " because it doesn't exist";
		}
		return ret;
	}

	public String deleteNonExistentEntity(String entityName, int id) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "cannot be deleted'" + entityName + "' with id " + id
						+ " because it doesn't exist";
				break;
			case "ES":
				ret = "no se puede eliminar '" + entityName + "' con id " + id
						+ " porque no existe";
				break;
			default:
				ret = "cannot be deleted'" + entityName + "' with id " + id
						+ " because it doesn't exist";
		}
		return ret;
	}

	public String deleteNonExistentEntityCorruptDB(String independentEntityName,
			String dependentEntityName, int independentEntityId) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "cannot delete '" + dependentEntityName + "' of " + independentEntityName
						+ "' with id " + independentEntityId
						+ " because it does not exist. Corrupted DB integrity.";
				break;
			case "ES":
				ret = "no se puede eliminar '" + dependentEntityName + "' de '"
						+ independentEntityName + "' con id " + independentEntityId
						+ " porque no existe. Integridad de BD corrupta.";
				break;
			default:
				ret = "cannot delete '" + dependentEntityName + "' of " + independentEntityName
						+ "' with id " + independentEntityId
						+ " because it does not exist. Corrupted DB integrity.";
		}
		return ret;
	}

	public String alreadyExistConstraint(String entityName, String attributeName, String value) {
		String ret = null;

		switch (this.messenger.getLang()) {
			case "EN":
				ret = "cannot insert/update '" + entityName + "'. " + attributeName + " " + value
						+ " already exist";
				break;
			case "ES":
				ret = "no puede insertarse/actualizarse '" + entityName + "'. " + attributeName
						+ " " + value + " ya existe";
				break;
			default:
				ret = "cannot insert/update '" + entityName + "'. " + attributeName + " " + value
						+ " already exist";
		}

		return ret;
	}
}
