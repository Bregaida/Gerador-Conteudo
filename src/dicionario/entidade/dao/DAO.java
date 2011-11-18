
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import entidade.Funcionario;

/**
 * @author Bregaida Dao Genérico
 */
public class DAO<T> {
	// T = Type ou seja tipo
	private Session session;

	private Logger logger = Logger.getLogger(DAO.class);

	private Class classe;

	public DAO(Session session, Class classe) {
		this.session = session;
		this.classe = classe;
	}

	public void save(T t) {
		logger.info("salvando " + t);
		session.save(t);
	}

	public T load(Long id) {
		logger.info("lendo " + classe + " id: " + id);
		return (T) session.load(classe, id);
	}

	public void salveOrUpdate(T t) {
		session.saveOrUpdate(t);
	}

	public void deleta(T t) {
		logger.info("deletando " + t);
		session.delete(t);
	}

	public List<T> list() {
		Criteria criteria = getSession().createCriteria(classe);
		return criteria.list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public List<String> buscaNomeFornecedor(String buscaNomeFornecedor) {
		Criteria criteria = session.createCriteria(Funcionario.class);
		criteria.add(Restrictions.ilike("nome", buscaNomeFornecedor + "%"));
		criteria.addOrder(Order.asc("nome"));
		criteria.setProjection(Projections.property("nome"));
		return criteria.list();
	}
}
