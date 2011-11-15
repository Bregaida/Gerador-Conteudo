//package entidade;
//
//import java.util.List;
//
//import org.hibernate.annotations.Entity;
//import org.hibernate.type.EnumType;
//
//@Entity
//public class Espetaculo {
//
//	@Id
//	@GeneratedValue
//	private Long id;
//
//	private String nome;
//
//	private String descricao;
//
//	@Enumerated(EnumType.STRING)
//	private TipoDeEspetaculo tipo;
//
//	private List<String> sessoes;
//
//	@ManyToOne
//	private Estabelecimento estabelecimento;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public TipoDeEspetaculo getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(TipoDeEspetaculo tipo) {
//		this.tipo = tipo;
//	}
//
//	public List<Sessao> getSessoes() {
//		return sessoes;
//	}
//
//	public void setEstabelecimento(Estabelecimento estabelecimento) {
//		this.estabelecimento = estabelecimento;
//	}
//
//	public Estabelecimento getEstabelecimento() {
//		return estabelecimento;
//	}
//
//	public List<Sessao> criaSessoes(LocalDate inicio, LocalDate fim, LocalTime horario, Periodicidade periodicidade) {
//		return null;
//	}
//
//}
