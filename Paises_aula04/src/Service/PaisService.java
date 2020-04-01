package Service;

import Modelo.Pais;

import java.util.ArrayList;

import DAO.PaisesDAO;

public class PaisService{
	
	PaisesDAO dao = new PaisesDAO();
	
	public int criar(Pais paises) {
		return dao.criar(paises);
	}
	
	public void atualizar(Pais paises) {
		 dao.atualizar(paises);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Pais carregar(int id){
		 return dao.carregar(id);
	}
	
	public void menorArea(Pais paises) {
		dao.menorArea(paises);
	}
	
	public long maiorPopulacao(long populacao) {
		return dao.maiorPopulacao(populacao);
	}
	
	
	public Pais[] tresPaises() {
		return dao.tresPaises();
		
	}
	
	public ArrayList<Pais> listarTodos(){
		
		return dao.listarTodos();
	}

}