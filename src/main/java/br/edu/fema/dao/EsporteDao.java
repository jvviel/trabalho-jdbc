package br.edu.fema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fema.conexao.FabricaConexao;
import br.edu.fema.exception.ExcecaoGeral;
import br.edu.fema.model.Esporte;

public class EsporteDao {

	private FabricaConexao cnn;
	private PreparedStatement stmt;

	public EsporteDao() {
		cnn = FabricaConexao.getInstance();
		stmt = null;
	}

	public boolean inserir(Esporte esporte) {
		String sql = "INSERT INTO esportes (descricao) values (?);";
		boolean retorno = false;

		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setString(1, esporte.getDescricao());
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao incluir um esporte!");
		} finally {
			cnn.desconectar();
		}
		return retorno;
	}

	public boolean alterar(Esporte esporte, Long id) {
		String sql = "UPDATE esportes set descricao = ? where id = ?;";
		boolean retorno = false;

		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setString(1, esporte.getDescricao());
			stmt.setLong(2, id);
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao alterar o esporte!");
		} finally {
			cnn.desconectar();
		}

		return retorno;
	}

	public Esporte objectFactoryInterface(ResultSet rs) {
		Esporte esporte = null;
		try {
			esporte = new Esporte(rs.getLong("id"), rs.getString("descricao"));
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao criar o objeto Factory");
		}
		return esporte;
	}
	
	public boolean remover(Long id) {
		String sql = "DELETE FROM esportes where id = ?;";
		boolean retorno = false;
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao remover esporte!");
		} finally {
			cnn.desconectar();
		}
		return retorno;
	}
	
	public Esporte buscarPorId(Long id) {
		String sql = "SELECT * FROM esportes where id = ?;";
		Esporte esporte = new Esporte();
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				esporte = objectFactoryInterface(rs);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao buscar Esporte por id!");
		} finally {
			//cnn.desconectar();
		}
		return esporte;
	}
	
	public List<Esporte> listarTodos() {
		String sql = "SELECT * FROM esportes ORDER BY descricao;";
		List<Esporte> esportes = new ArrayList<>();
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				esportes.add(objectFactoryInterface(rs));
			}
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao listar todos Esportes!");
		} finally {
			cnn.desconectar();
		}
		return esportes;
	}

}
