package it.prova.municipioabitantejpa.dao;

import it.prova.municipioabitantejpa.dao.abitante.AbitanteDAO;
import it.prova.municipioabitantejpa.dao.abitante.AbitanteDAOImpl;
import it.prova.municipioabitantejpa.dao.municipio.MunicipioDAO;
import it.prova.municipioabitantejpa.dao.municipio.MunicipioDAOImpl;

public class MyDaoFactory {

	// rendiamo questo factory SINGLETON
	private static AbitanteDAO abitanteDAOInstance = null;
	private static MunicipioDAO municipioDAOInstance = null;

	public static AbitanteDAO getAbitanteDAOInstance() {
		if (abitanteDAOInstance == null)
			abitanteDAOInstance = new AbitanteDAOImpl();
		return abitanteDAOInstance;
	}

	public static MunicipioDAO getMunicipioDAOInstance(){
		if(municipioDAOInstance == null)
			municipioDAOInstance= new MunicipioDAOImpl();
		return municipioDAOInstance;
	}

}
