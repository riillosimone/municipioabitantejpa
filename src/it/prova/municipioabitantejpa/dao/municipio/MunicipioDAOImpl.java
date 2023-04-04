package it.prova.municipioabitantejpa.dao.municipio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.municipioabitantejpa.model.Municipio;

public class MunicipioDAOImpl implements MunicipioDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Municipio> list() throws Exception {
		// dopo la from bisogna specificare il nome dell'oggetto (lettera maiuscola) e
		// non la tabella
		return entityManager.createQuery("from Municipio", Municipio.class).getResultList();
	}

	@Override
	public Municipio get(Long id) throws Exception {
		return entityManager.find(Municipio.class, id);
	}

	@Override
	public Municipio getEagerAbitanti(Long id) throws Exception {
		// join restituirebbe solo i municipi con abitanti (LAZY)
		// join fetch come sopra ma valorizza anche la lista di abitanti (EAGER)
		// left join fetch come sopra ma cerca anche tra i municipi privi di abitanti
		// (EAGER)
		TypedQuery<Municipio> query = entityManager
				.createQuery("from Municipio m left join fetch m.abitanti where m.id = ?1", Municipio.class);
		query.setParameter(1, id);

		// return query.getSingleResult() ha il problema che se non trova elementi
		// lancia NoResultException
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public void update(Municipio municipioInstance) throws Exception {
		if (municipioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		municipioInstance = entityManager.merge(municipioInstance);
	}

	@Override
	public void insert(Municipio municipioInstance) throws Exception {
		if (municipioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(municipioInstance);
	}

	@Override
	public void delete(Municipio municipioInstance) throws Exception {
		if (municipioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(municipioInstance));
	}

	// TO DO PER ESERCIZIO
	@Override
	public List<Municipio> findAllByAbitantiMinorenni() throws Exception {
		TypedQuery<Municipio> query = entityManager.createQuery("select distinct m from Municipio m join m.abitanti a where a.eta <=18",
				Municipio.class);
		return query.getResultList();
	}

	@Override
	public List<Municipio> findAllByDescrizioneIniziaCon(String iniziale) throws Exception {
		TypedQuery<Municipio> query = entityManager.createQuery("from Municipio m where m.descrizione like ?1",Municipio.class);
		query.setParameter(1, iniziale + "%");
		return query.getResultList();
	}

}
