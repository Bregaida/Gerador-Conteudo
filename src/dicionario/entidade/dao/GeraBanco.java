/**
 * 
 */


import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author Bregaida
 * 
 * Classe para gerar uma banco Fake
 */
public class GeraBanco {
	public static void main(String[] args) {
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure();
		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true, true);

	}
}
