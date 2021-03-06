/**
 * This file is part of D.A.L.G.S.
 *
 * D.A.L.G.S is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * D.A.L.G.S is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with D.A.L.G.S.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.ucm.fdi.dalgs.degree.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.ucm.fdi.dalgs.domain.Degree;

@Repository
public class DegreeRepository {
	protected EntityManager em;

	private static final Integer noOfRecords = 5;

	public EntityManager getEntityManager() {
		return em;
	}

	protected static final Logger logger = LoggerFactory
			.getLogger(DegreeRepository.class);

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		try {
			this.em = entityManager;
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
	}

	public boolean addDegree(Degree degree) {
		try {
			em.persist(degree);
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Degree> getAll() {

		return em
				.createQuery(
						"select d from Degree d where d.isDeleted = false order by d.id ")
				.getResultList();

	}

	public boolean saveDegree(Degree degree) {
		try {
			em.merge(degree);
		} catch (ConstraintViolationException e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	public Degree getDegree(Long id) {

		return em.find(Degree.class, id);
	}

	public boolean deleteDegree(Degree degree) {
		// Degree degree = em.getReference(Degree.class, id);
		try {
			degree.setDeleted(true);
			em.merge(degree);

			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	// public Degree getDegreeSubject(Subject p) {
	// Query query = em
	// .createQuery("select d from Degree d join d.subjects s where s=?1");
	// query.setParameter(1, p);
	// if (query.getResultList().isEmpty())
	// return null;
	// return (Degree) query.getSingleResult();
	// }

	public String getNextCode() {
		Query query = em.createQuery("Select MAX(e.id ) from Degree e");
		try {
			Long aux = (Long) query.getSingleResult() + 1;
			return "DEG" + aux;
		} catch (Exception e) {
			logger.error(e.getMessage());

			return null;
		}

	}

	public Degree existByCode(String code) {
		Query query = em
				.createQuery("select d from Degree d where d.info.code=?1");
		query.setParameter(1, code);
		if (query.getResultList().isEmpty())
			return null;
		else
			return (Degree) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Degree> getDegrees(Integer pageIndex, Boolean showAll) {
		Query query = null;

		if (showAll)
			query = em
					.createQuery("select a from Degree a  order by a.id DESC");
		else
			query = em
					.createQuery("select a from Degree a  where a.isDeleted='false' order by a.id DESC");

		if (query.getResultList().isEmpty())
			return null;

		return query.setMaxResults(noOfRecords)
				.setFirstResult(pageIndex * noOfRecords).getResultList();

	}

	public Integer numberOfPages(Boolean showAll) {
		Query query = null;
		if (showAll)
			query = em.createNativeQuery("select count(*) from degree");
		else
			query = em
					.createNativeQuery("select count(*) from degree where isDeleted='false'");

		logger.info(query.getSingleResult().toString());
		double dou = Double.parseDouble(query.getSingleResult().toString())
				/ ((double) noOfRecords);
		return (int) Math.ceil(dou);
	}

	public boolean persistListDegrees(List<Degree> degrees) {

		int i = 0;
		for (Degree d : degrees) {
			try {

				// In this case we have to hash the password (SHA-256)
				// StringSHA sha = new StringSHA();
				// String pass = sha.getStringMessageDigest(u.getPassword());
				// u.setPassword(pass);

				d.setId(null); // If not a detached entity is passed to persist
				em.persist(d);
				// em.flush();

				if (++i % 20 == 0) {
					em.flush();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				return false;
			}
		}

		return true;

	}
}
