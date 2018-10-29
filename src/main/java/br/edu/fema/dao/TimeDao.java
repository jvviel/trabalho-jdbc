package br.edu.fema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fema.conexao.FabricaConexao;
import br.edu.fema.exception.ExcecaoGeral;
import br.edu.fema.model.Time;

public class TimeDao {

	private FabricaConexao cnn;
	private PreparedStatement stmt;

	public TimeDao() {
		cnn = FabricaConexao.getInstance();
		stmt = null;
	}

	public boolean inserir(Time time) {
		String sql = "INSERT INTO times (descricao, liga, tecnico, estadio) values (?,?,?,?);";
		boolean retorno = false;

		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setString(1, time.getDescricao());
			stmt.setLong(2, time.getLiga().getId());
			stmt.setString(3, time.getTecnico());
			stmt.setString(4, time.getEstadio());
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao incluir um time!");
		} finally {
			cnn.desconectar();
		}
		return retorno;
	}

	public boolean alterar(Time time, Long id) {
		String sql = "UPDATE times set descricao = ?, liga = ?,"
				+ "tecnico = ?, estadio = ? where id = ?;";
		boolean retorno = false;

		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setString(1, time.getDescricao());
			stmt.setLong(2, time.getLiga().getId());
			stmt.setString(3, time.getTecnico());
			stmt.setString(4, time.getEstadio());
			stmt.setLong(5, id);
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao alterar o time!");
		} finally {
			cnn.desconectar();
		}

		return retorno;
	}

	public Time objectFactory(ResultSet rs) {
		Time time = null;
		LigaDao ligaDao = new LigaDao();
		try {
			time = new Time(rs.getLong("id"), rs.getString("descricao"),ligaDao.buscarPorId(rs.getLong("liga")),
					rs.getString("tecnico"), rs.getString("estadio"));
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao criar o objeto Factory");
		}
		return time;
	}
	
	public boolean remover(Long id) {
		String sql = "DELETE FROM times where id = ?;";
		boolean retorno = false;
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao remover time!");
		} finally {
			cnn.desconectar();
		}
		return retorno;
	}
	
	public Time buscarPorId(Long id) {
		String sql = "SELECT * FROM times where id = ?;";
		Time time = new Time();
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				time = objectFactory(rs);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao buscar time por id!");
		} finally {
			//cnn.desconectar();
		}
		return time;
	}
	
	public List<Time> listarTodos() {
		String sql = "SELECT * FROM times ORDER BY descricao;";
		List<Time> times = new ArrayList<>();
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				times.add(objectFactory(rs));
			}
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao listar todos os times!");
		} finally {
			cnn.desconectar();
		}
		return times;
	}
}
