/**
 * 
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import entidade.Funcionario;

/**
 * @author Bregaida
 * 
 *         Classe para gerar uma banco Fake
 */
public class GeraBanco {
	public static void main(String[] args) {
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure();
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true, true);

		// Insere dados no Banco
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Eduardo Bregaida");
		funcionario.setEmail("eduardo.bregaida@gmail.com");

		Funcionario funcionario2 = new Funcionario();
		funcionario2.setNome("Max The Dog");
		funcionario2.setEmail("maxTheDog@gmail.com");

		Transaction transaction = session.beginTransaction();
		session.save(funcionario);
		session.save(funcionario2);

		transaction.commit();

		session.close();
		sessionFactory.close();
	}
}
