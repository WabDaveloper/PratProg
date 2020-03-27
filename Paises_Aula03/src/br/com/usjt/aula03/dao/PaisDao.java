package br.com.usjt.aula03.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.usjt.aula03.model.Pais;

public class PaisDao {
	//-----------------------------Create---------------------------------------

	public int criar(Pais Pais) {
		String sqlInsert = "INSERT INTO Pais(nome, populacao, area) VALUES(?,?,?)";

		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, Pais.getNome());
			stm.setLong(2, Pais.getPopulacao());
			stm.setDouble(3, Pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()"; 

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); 
					ResultSet rs = stm2.executeQuery();) { 
				if (rs.next()) {
					Pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) { 
				e.printStackTrace(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Pais.getId();
	}

	//-----------------------------Carregar---------------------------------------
	public Pais carregar(int id) {
		String sqlSelect = "SELECT nome, populacao, area From Pais WHERE Pais.id = ?";
		Pais Pais = new Pais();
		Pais.setId(id);
		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, Pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					Pais.setNome(rs.getString("nome"));
					Pais.setPopulacao(rs.getLong("populacao"));
					Pais.setArea(rs.getDouble("area"));
				} else {
					Pais.setId(0);
					Pais.setNome(null);
					Pais.setPopulacao(0);
					Pais.setArea(0);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return Pais;
	}

	//-----------------------------Update---------------------------------------

	public void atualizar(Pais Pais) {
		String sqlUpdate = "UPDATE Pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, Pais.getNome());
			stm.setLong(2, Pais.getPopulacao());
			stm.setDouble(3, Pais.getArea());
			stm.setInt(4, Pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//-----------------------------Delete---------------------------------------

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM Pais WHERE id=?";

		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//-----------------------------Menor Area---------------------------------------/

	public void menorArea(Pais Pais) {
		String sqlMenor = "SELECT  nome, area FROM Pais WHERE area = (select MIN(area) FROM Pais)LIMIT 1";

		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMenor);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					Pais.setNome(rs.getString("nome"));
					Pais.setArea(rs.getDouble("area"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
	}
	//-----------------------------Maior popula��o---------------------------------------
	public long maiorPopulacao(Long l) {
		String sqlMaior = "SELECT  nome, populacao FROM Pais WHERE populacao = (select Max(populacao) FROM Pais)";

		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMaior);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					rs.getString("nome");
					l = rs.getLong("populacao");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	//-----------------------------Tr�s Paises---------------------------------------
	public Pais[] tresPais() {
		
		Pais Pais = null;
		Pais[] vetor = new Pais[3];
		int i = 0;

		String sqltres = "SELECT * FROM Pais LIMIT 3";

		
		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqltres);) {
			 
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");
					Long populacao = rs.getLong("populacao");
					Double area = rs.getDouble("area");
				
				Pais = new Pais(id,nome,populacao,area);
				vetor[i++] = Pais;
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return vetor;
	}
}