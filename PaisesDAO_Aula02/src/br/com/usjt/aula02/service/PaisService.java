package br.com.usjt.aula02.service;

import java.util.ArrayList;

import br.com.usjt.aula02.DAO.PaisDao;
import br.com.usjt.aula02.model.Pais;

public class PaisService {
	
	PaisDao dao = new PaisDao();
	
	public int criar(Pais Pais) {
		return dao.criar(Pais);
	}
	
	public void atualizar(Pais Pais) {
		 dao.atualizar(Pais);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Pais carregar(int id){
		 return dao.carregar(id);
	}
	
	public void menorArea(Pais Pais) {
		dao.menorArea(Pais);
	}
	
	public long maiorPopulacao(long populacao) {
		return dao.maiorPopulacao(populacao);
	}
	
	
	public Pais[] tresPais() {
		return dao.tresPais();
		
	}

}