package service;

import java.util.List;

import model.*;
import repository.*;

public class Service {
	private Repository repository = new Repository();
	
	public void InsertNewLanguage(String nLanguage, String country) {
		repository.InsertLanguageTable(nLanguage);
		repository.InsertCountryTable(country, nLanguage);
	}
	
	public void insertCountry(String language, String country) {
		repository.InsertCountryTable(country, language);
	}
	
	public void createTables() {
		repository.CreateCountryTable();
		repository.CreateLanguageTable();
	}
	
	public void DeleteLanguage(String language) {
		repository.DeleteTable(language);
	}

	public List<Countries> listCountries() {
		return repository.listCountries();
	}
	
	public List<Languages> listLanguages() {
		return repository.listLanguages();
	}

	public Repository getRepository() {
		return repository;
	}s

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
}
