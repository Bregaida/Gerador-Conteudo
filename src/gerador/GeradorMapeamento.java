package gerador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import util.Util;

import mapeamento.GrdAtributosDicionario;
import mapeamento.GrdEnumEntidadeMpt;
import mapeamento.GrdEnumMapeamento;
import mapeamento.GrdEnumTipoInfoAtributo;
import mapeamento.xml.AtributoMptXml;
import mapeamento.xml.EntidadeMptXml;
import mapeamento.xml.MapeamentoXml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import enumerador.EnumArquivoNomes;
import exception.GeradorException;

/**
 * Responsável por gerar e atualizar o conteudo dos arquivos necessarios apos atualizacao 'mapeamento.xml'. Arquivos: - EnumMapeamento.java - AtributosDicionario.java - EnumEntidadeMpt.java (Entidade deve ser o nome da entidade, ex: EnumEmpresaMpt.java) - EnumTipoInfoAtributo.java
 * 
 * @author Fradico e Bregaida
 */
public class GeradorMapeamento
{

	private MapeamentoXml	mpt	= null;

	public GeradorMapeamento() throws GeradorException
	{
		try
		{
			File fileMapeamento = new File(Util.caminhoArquivo(EnumArquivoNomes.MAPEAMENTO_XML.getNome()));
			BufferedReader readerXML;
			readerXML = new BufferedReader(new FileReader(fileMapeamento));

			XStream xstream = new XStream(new DomDriver());
			xstream.alias("mapeamento", MapeamentoXml.class);
			xstream.alias("entidade", EntidadeMptXml.class);
			xstream.alias("atributo", AtributoMptXml.class);
			mpt = (MapeamentoXml) xstream.fromXML(readerXML);
		}
		catch (FileNotFoundException e)
		{
			throw new GeradorException("Arquivo não encontrado: " + e.getMessage());
		}
	}

	public void gerar()
	{
		try
		{

			GrdEnumEntidadeMpt grdEnumEntidade = new GrdEnumEntidadeMpt(mpt, EnumArquivoNomes.ENUM_ENTIDADE_MPT.getNome());
			GrdEnumTipoInfoAtributo grdEnumTipo = new GrdEnumTipoInfoAtributo(mpt, EnumArquivoNomes.ENUM_TIPO_INFO_ATRIBUTO.getNome());
			GrdEnumMapeamento grdEnumMapeamento = new GrdEnumMapeamento(mpt, EnumArquivoNomes.ENUM_MAPEAMENTO.getNome());
			GrdAtributosDicionario grdAtrDic = new GrdAtributosDicionario(mpt, EnumArquivoNomes.ATRIBUTOS_DICIONARIO.getNome());

			grdEnumEntidade.gerar();
			grdEnumTipo.gerar();
			grdEnumMapeamento.gerar();
			grdAtrDic.gerar();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws GeradorException
	{
		GeradorMapeamento grdMap = new GeradorMapeamento();
			grdMap.gerar();
			System.out.println("Executou com sucesso");
	}
}
