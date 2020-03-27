package br.paises.com;

import java.sql.*;

public class Pais {

	private int id;
	private String nome;
	private long populacao;
	private double area;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Conected!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	// Obtem conexao com o banco de dados
	public static Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/PratProgInt?user=root&password=teste&useSSL=false&useTimezone=true&serverTimezone=UTC");
	}

	public Pais(int id, String nome, long populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	public Pais() {}
	

	/****************************** Metodo C R U D ***************************/

	/****************************** Metodo CREATE ***************************/

	public void criar() {
		String sqlInsert = "INSERT INTO pais(populacao,nome, area) VALUES(?,?,?)";

		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(2, getNome());
			stm.setLong(1, getPopulacao());
			stm.setDouble(3, getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()"; 

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); 
					ResultSet rs = stm2.executeQuery();) { 
				if (rs.next()) {
					setId(rs.getInt(1));
				}
			} catch (SQLException e) { 
				e.printStackTrace(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/****************************** Metodo READ ***************************/
	public void carregar() {
		String sqlSelect = "SELECT nome, populacao, area From pais WHERE pais.id = ?";

		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				} else {
					setId(0);
					setNome(null);
					setPopulacao(0);
					setArea(0);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
	}

	/****************************** Metodo UPDATE ***************************/

	public void atualizar() {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.setInt(4, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************** Metodo UPDATE ***************************/

	public void excluir() {
		String sqlDelete = "DELETE FROM pais WHERE id=?";

		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/****************************Metodo para a MENOR AREA******************/

	public void menorArea() {
		String sqlMenor = "SELECT  nome, area FROM pais WHERE area = (select MIN(area) FROM pais)";

		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMenor);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setArea(rs.getDouble("area"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
	}

	public void maiorPopulacao() {
		String sqlMaior = "SELECT  nome, populacao FROM pais WHERE populacao = (select Max(populacao) FROM pais)";

		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlMaior);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Pais[] tresPaises() {
		
		Pais paises = null;
		Pais[] vetor = new Pais[3];
		int i = 0;

		String sqltres = "SELECT * FROM pais LIMIT 3";

		
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqltres);) {
			 
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");
					Long populacao = rs.getLong("populacao");
					Double area = rs.getDouble("area");
					
					
					//System.out.println("Paises [id= " + id + ", nome= " + rs.getString("nome") + 
					//", populacao= " + rs.getLong("populacao") + ", area= " + rs.getDouble("area") + "]");
				
				paises = new Pais(id,nome,populacao,area);
				vetor[i++] = paises;
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return vetor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	@Override
	public String toString() {
		return "Paises [id= " + id + ", nome= " + nome + ", populacao= " + populacao + ", area= " + area + "]";
	}
/*
	
	  @Override 
	  public boolean equals(Object obj) { 
		  if (this == obj) 
			  return true;
	  if (obj == null) 
		  return false; 
	  if (getClass() != obj.getClass()) 
		  return false; 
	  Paises other = (Paises) obj; 
	  if (id != other.id) return false; 
	  return true; 
	  }*/
	 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;

		if (area == 0.0) {
			if (other.area != 0.0)
				return false;
		}

		if (populacao == 0) {
			if (other.populacao != 0)
				return false;
		}

		if (id != other.id)
			return false;
		/*
		 if (nome == null) { 
			 if (other.nome != null) 
				 return false; 
			 } else if(!nome.equals(other.nome)) 
				 return false;		*/ 

		return true;
	}
}