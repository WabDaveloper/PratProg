package br.com.usjt.aula02.teste;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.usjt.aula02.service.PaisService;
import br.com.usjt.aula02.model.Pais;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTeste {
	Pais Pais,copia;
	PaisService PaisService;
	static int id = 0;
	static long populacao;
	static double area;
	static String nome;
	

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma linha
	 * com o id igual ao do escolhido para o to instanciado abaixo. Se houver,
	 * delete. Certifique-se também que sobrecarregou o equals na classe Cliente.
	 * Certifique-se que a fixture cliente1 foi criada no banco. Além disso, a ordem
	 * de execução dos testes é importante; por isso a anotação FixMethodOrder logo
	 * acima do nome desta classe
	 */
	
	@Before
	public void setUp() throws Exception {
		System.out.println("\nsetup");
		Pais = new Pais();
		Pais.setId(id);
		Pais.setNome("Belgica");
		Pais.setPopulacao(11400000);
		Pais.setArea(30689);
		
		copia = new Pais();
		copia.setId(id);
		copia.setNome("Belgica");
		copia.setPopulacao(11400000);
		copia.setArea(30689);
		
		PaisService = new PaisService();
		System.out.println(Pais);
		System.out.println(copia);
		System.out.println(id);
	}

	@Test
	public void test00Carregar() {
		System.out.println("\ncarregar");
		Pais fixture = new Pais();
		fixture.setId(1);
		fixture.setNome("Brazil");
		fixture.setPopulacao(1246700);
		fixture.setArea(9.90);
		PaisService newPaisService = new PaisService();
		Pais novo = newPaisService.carregar(1);
		assertEquals("testa inclusao", novo, fixture);
	}
	@Test
	public void test01Criar() {
		System.out.println("\ncriar");
		id = PaisService.criar(Pais);
		System.out.println(id);
		copia.setId(id);
		System.out.println("--------------------------------------------");
		System.out.println(Pais);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("testa criacao", Pais, copia);
	}
	

	@Test
	public void test02Atualizar() {
		System.out.println("\natualizar");
		Pais.setArea(9.9);
		copia.setArea(9.9);
		PaisService.atualizar(Pais);
		Pais = PaisService.carregar(copia.getId());
		System.out.println("--------------------------------------------");
		System.out.println(Pais);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("testa atualizacao", Pais, copia);
	
	}

	@Test
	public void test03Excluir() {
		System.out.println("\nexcluir");
		copia.setId(0);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0.0);
		PaisService.excluir(id);
		Pais = PaisService.carregar(id);
		System.out.println("--------------------------------------------");
		System.out.println(Pais);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("testa exclusao", Pais, copia);
	}
	
	@Test
	public void test04menorArea() {
		System.out.println("\nMenor Area");
		PaisService.menorArea(Pais);
		area = Pais.getArea();
		copia.setArea(area);
		System.out.println("--------------------------------------------");
		System.out.println(Pais);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("testa a menor area", copia, Pais);
	}
	
	@Test
	public void test04maiorPopulacao() {
		System.out.println("\nMaiorPopulação");
		PaisService.maiorPopulacao(Pais.getPopulacao());
		populacao = Pais.getPopulacao();
		copia.setPopulacao(populacao);
		System.out.println("--------------------------------------------");
		System.out.println(Pais);
		System.out.println(copia);
		System.out.println("--------------------------------------------");
		assertEquals("Testa o menor valor da tabela", copia, Pais);
	}
	
	@Test
	public void test05testarVetor() {
		System.out.println("\nVetor 3 Pais");
		Pais[] vetor = PaisService.tresPais();
		for (Pais Pais : vetor) {
			id = Pais.getId();
			area = Pais.getArea();
			nome = Pais.getNome();
			populacao = Pais.getPopulacao();
			copia.setId(id);
			copia.setArea(area);
			copia.setNome(nome);
			copia.setPopulacao(populacao);
			System.out.println("--------------------------------------------");
			System.out.println(Pais);
			System.out.println(copia);
			System.out.println("--------------------------------------------");
			assertEquals("Testa o menor valor da tabela", copia, Pais);
		}
	}
}