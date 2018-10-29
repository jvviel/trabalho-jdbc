package br.edu.fema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fema.conexao.FabricaConexao;
import br.edu.fema.exception.ExcecaoGeral;
import br.edu.fema.model.Liga;

public class LigaDao {

	private FabricaConexao cnn;
	private PreparedStatement stmt;

	public LigaDao() {
		cnn = FabricaConexao.getInstance();
		stmt = null;
	}

	public boolean inserir(Liga liga) {
		String sql = "INSERT INTO ligas (descricao, pais, esporte, premio) values (?,?,?,?);";
		boolean retorno = false;

		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setString(1, liga.getDescricao());
			stmt.setString(2, liga.getPais());
			stmt.setLong(3, liga.getEsporte().getId());
			stmt.setString(4, liga.getPremio());
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao incluir uma liga!");
		} finally {
			cnn.desconectar();
		}
		return retorno;
	}

	public boolean alterar(Liga liga, Long id) {
		String sql = "UPDATE ligas set descricao = ?, pais = ?,"
				+ "esporte = ?, premio = ? where id = ?;";
		boolean retorno = false;

		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setString(1, liga.getDescricao());
			stmt.setString(2, liga.getPais());
			stmt.setLong(3, liga.getEsporte().getId());
			stmt.setString(4, liga.getPremio());
			stmt.setLong(5, id);
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao alterar a liga!");
		} finally {
			cnn.desconectar();
		}

		return retorno;
	}

	public Liga objectFactory(ResultSet rs) {
		Liga liga = null;
		EsporteDao esporteDao = new EsporteDao();
		try {
			liga = new Liga(rs.getLong("id"), rs.getString("descricao"), rs.getString("pais"),
					esporteDao.buscarPorId(rs.getLong("esporte")), rs.getString("premio"));
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao criar o objeto Factory");
		}
		return liga;
	}
	
	public boolean remover(Long id) {
		String sql = "DELETE FROM ligas where id = ?;";
		boolean retorno = false;
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao remover liga!");
		} finally {
			cnn.desconectar();
		}
		return retorno;
	}
	
	public Liga buscarPorId(Long id) {
		String sql = "SELECT * FROM ligas where id = ?;";
		Liga liga = new Liga();
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				liga = objectFactory(rs);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao buscar Liga por id!");
		} finally {
			//cnn.desconectar();
		}
		return liga;
	}
	
	public List<Liga> listarTodos() {
		String sql = "SELECT * FROM ligas ORDER BY descricao;";
		List<Liga> ligas = new ArrayList<>();
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				ligas.add(objectFactory(rs));
			}
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao listar todas as ligas!");
		} finally {
			cnn.desconectar();
		}
		return ligas;
	}
}
