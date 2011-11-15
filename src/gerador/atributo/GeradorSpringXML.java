//package atributo;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Reader;
//import java.lang.reflect.Field;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.beanutils.PropertyUtils;
//
//import util.ReflectionUtil;
//
//import com.thoughtworks.xstream.XStream;
//
//import dicionario.entidade.modelo.GrupoEntidadeE;
//import dicionario.entidade.to.BeanTO;
//import dicionario.entidade.to.PropertyTO;
//import dicionario.entidade.to.RefTO;
//import dicionario.entidade.to.RootTO;
//import enumerador.EnumArquivoNomes;
//import enumerador.EnumEntidadeConstante;
//import exception.GeradorException;
//
///**
// * @author Bregaida e Fradico
// * 
// *         Gera o XML do SPRING com a entidade preenchida
// */
//public class GeradorSpringXML
//{
//	private ConnectionImpl		conn;
//	private GrupoEntidadeDAO	grupoEntidadeDAO;
//
//	public GeradorSpringXML() throws GeradorException
//	{
//		try
//		{
//			conn = DBConnection.getConnection(EnumConfigAmbiente.CONTABILIDADE);
//		}
//		catch (DBException e)
//		{
//			throw new GeradorException("Nao conseguiu abrir uma conexao com o Banco de Dados: " + e.getMessage());
//		}
//		grupoEntidadeDAO = new GrupoEntidadeDAO();
//	}
//
//	public void iniciar() throws GeradorException
//	{
//		gerarEntidade(EnumEntidadeConstante.EMPRESA);
//		gerarEntidade(EnumEntidadeConstante.ESTADO); 
//		
//	}
//
//	private void gerarEntidade(EnumEntidadeConstante enumEntidade) throws GeradorException
//	{
//		Collection<GrupoEntidadeE> itemColl;
//		try
//		{
//			itemColl = grupoEntidadeDAO.listarEntidades(enumEntidade.getEntidadeClass(), conn);
//		}
//		catch (DBException e)
//		{
//			throw new GeradorException("Não conseguiu acessar o Banco de Dados: " + e.getMessage());
//		}
//		catch (SQLException e)
//		{
//
//			throw new GeradorException("Não conseguiu executar o comando SQL: " + e.getMessage());
//		}
//
//		RootTO root = new RootTO();
//		Map<String, Object> instanciasJaMapeadas = new HashMap<String, Object>();
//
//		imprimirXML(root, enumEntidade.getNomeArqXmlCompleto());
//	}
//
//	private void preencherTagsApartirDeEntidade(RootTO root, Object instancia, Map<String, Object> instanciasJaMapeadas)
//	{
//		try
//		{
//			BeanTO bean = new BeanTO();
//
//			String entidadeNome = instancia.getClass().getSimpleName().toLowerCase();
//			String entidadeNomeCanonico = instancia.getClass().getCanonicalName();
//			String entidadePkEncadeada = "";
//
//			if (instanciasJaMapeadas.get(entidadeNome + entidadePkEncadeada) != null) { return; }
//
//			instanciasJaMapeadas.put(entidadeNome + entidadePkEncadeada, instancia);
//
//			bean.setId(entidadeNome + entidadePkEncadeada);
//			bean.setClazz(entidadeNomeCanonico);
//
//			for (Field field : ReflectionUtil.findAllFields(instancia.getClass()))
//			{
//				if (isAtributoIgnoravel(field.getName())) continue;
//
//				if (PropertyUtils.getProperty(instancia, field.getName()) == null)
//				{
//					continue;
//				}
//
//				PropertyTO property = new PropertyTO();
//				property.setName(field.getName());
//
//				String valorOuRef = "";
//
//				/* Coleção */
//				if (ReflectionUtil.implementsOf(field.getType(), Collection.class) || ReflectionUtil.instanceOf(field.getType(), Collection.class))
//				{
//					// Não é necessário por enquanto
//				}
//				/* Tipos básicos */
//				else
//				{
//					property.setRef(null);
//					property.setValue(PropertyUtils.getProperty(instancia, field.getName()).toString());
//				}
//
//				bean.add(property);
//
//			}
//
//			root.add(bean);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//	}
//
//	private void imprimirXML(RootTO root, String arquivoNome) throws GeradorException
//	{
//		try
//		{
//			XStream xstream = new XStream();
//			xstream.alias("root", RootTO.class);
//			xstream.alias("bean", BeanTO.class);
//			xstream.alias("property", PropertyTO.class);
//			xstream.alias("ref", RefTO.class);
//
//			xstream.addImplicitCollection(RootTO.class, "beanList");
//			xstream.addImplicitCollection(BeanTO.class, "properties");
//
//			xstream.useAttributeFor(BeanTO.class, "id");
//			xstream.useAttributeFor(BeanTO.class, "clazz");
//
//			xstream.useAttributeFor(PropertyTO.class, "name");
//			xstream.useAttributeFor(PropertyTO.class, "value");
//			xstream.useAttributeFor(PropertyTO.class, "ref");
//			xstream.useAttributeFor(RefTO.class, "local");
//
//			xstream.aliasAttribute(BeanTO.class, "clazz", "class");
//			xstream.aliasAttribute(PropertyTO.class, "refList", "list");
//
//			String corpoXml = xstream.toXML(root);
//
//			/* Lendo o template do cabeçalho do XML específico do Spring */
//
//			File templateCabecalhoXml = new File(EnumArquivoNomes.CABECALHO_SPRING_XML.getNome());
//			Reader reader;
//			reader = new BufferedReader(new FileReader(templateCabecalhoXml));
//
//			char[] conteudoChars = new char[(int) templateCabecalhoXml.length()];
//			reader.read(conteudoChars);
//			String cabecalhoXml = String.valueOf(conteudoChars);
//
//			corpoXml = corpoXml.replace("<root>", "");
//			corpoXml = corpoXml.replace("</root>", "</beans>");
//
//			StringBuffer xml = new StringBuffer();
//			xml.append(cabecalhoXml);
//			xml.append(corpoXml);
//
//			File arquivo = new File(arquivoNome);
//			BufferedWriter out = new BufferedWriter(new FileWriter(arquivo));
//
//			out.write(xml.toString());
//			out.flush();
//			out.close();
//
//			System.out.println(xml);
//		}
//		catch (FileNotFoundException e)
//		{
//			throw new GeradorException("Arquivo não encontrado: " + e.getMessage());
//		}
//		catch (IOException e)
//		{
//			throw new GeradorException("Não conseguiu acessar o arquivo: " + e.getMessage());
//		}
//	}
//
//	// TODO Retirar USUARIO desta lista, caso ele seja necessário futuramente
//	private boolean isAtributoIgnoravel(String nomeAtributo)
//	{
//		if ("serialVersionUID".equals(nomeAtributo) ) { return true; }
//		return false;
//	}
//
//	public static void main(String[] args) throws GeradorException
//	{
//		GeradorSpringXML gerador = new GeradorSpringXML();
//		gerador.iniciar();
//	}
//}
