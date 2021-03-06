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
package es.ucm.fdi.dalgs.mailbox.respository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.ucm.fdi.dalgs.domain.MessageBox;

@Repository
public class MailBoxRepository {

	protected EntityManager em;
	protected static final Logger logger = LoggerFactory
			.getLogger(MailBoxRepository.class);

	public EntityManager getEntityManager() {
		return em;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		try {
			this.em = entityManager;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	

	public MessageBox getMessageBox (String code){
		
		Query query = null;

		query = em
				.createQuery("select m FROM MessageBox m where m.code=?1");
		query.setParameter(1, code);
		
		if(query.getResultList().isEmpty())
			return null;
		else return (MessageBox) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<? extends MessageBox> getAllMessages() {

		Query query = null;

		query = em
				.createQuery("FROM MessageBox m");
		
		
		return query.getResultList();
	}


}
