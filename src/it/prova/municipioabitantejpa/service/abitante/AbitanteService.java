package it.prova.municipioabitantejpa.service.abitante;

import java.util.List;

import it.prova.municipioabitantejpa.dao.abitante.AbitanteDAO;
import it.prova.municipioabitantejpa.model.Abitante;

public interface AbitanteService {

	public List<Abitante> listAllAbitanti() throws Exception;

	public Abitante caricaSingoloAbitante(Long id) throws Exception;

	public void aggiorna(Abitante abitanteInstance) throws Exception;

	public void inserisciNuovo(Abitante abitanteInstance) throws Exception;

	public void rimuovi(Long idAbitanteInstance) throws Exception;

	public List<Abitante> cercaTuttiGliAbitantiConNome(String nome) throws Exception;

	public List<Abitante> cercaTuttiGliAbitantiConCognome(String cognome) throws Exception;
	
	public List<Abitante> cercaTuttiGliAbitantiConCodiceMunicipio(String codice) throws Exception;

	// per injection
	public void setAbitanteDAO(AbitanteDAO abitanteDAO);

}
