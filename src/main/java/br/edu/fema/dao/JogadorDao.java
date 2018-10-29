package br.edu.fema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fema.conexao.FabricaConexao;
import br.edu.fema.exception.ExcecaoGeral;
import br.edu.fema.model.Jogador;

public class JogadorDao {

	private FabricaConexao cnn;
	private PreparedStatement stmt;

	public JogadorDao() {
		cnn = FabricaConexao.getInstance();
		stmt = null;
	}

	public boolean inserir(Jogador jogador) {
		String sql = "INSERT INTO jogadores (nome, posicao, time) values (?,?,?);";
		boolean retorno = false;

		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setString(1, jogador.getNome());
			stmt.setString(2, jogador.getPosicao());
			stmt.setLong(3, jogador.getTime().getId());
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao incluir um Jogador!");
		} finally {
			cnn.desconectar();
		}
		return retorno;
	}

	public boolean alterar(Jogador jogador, Long id) {
		String sql = "UPDATE jogadores set nome = ?, posicao = ?,"
				+ "time = ? where id = ?;";
		boolean retorno = false;

		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setString(1, jogador.getNome());
			stmt.setString(2, jogador.getPosicao());
			stmt.setLong(3, jogador.getTime().getId());
			stmt.setLong(4, id);
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao alterar o Jogador!");
		} finally {
			cnn.desconectar();
		}

		return retorno;
	}

	public Jogador objectFactory(ResultSet rs) {
		Jogador jogador = null;
		TimeDao timeDao = new TimeDao();
		try {
			jogador = new Jogador(rs.getLong("id"), rs.getString("nome"), rs.getString("posicao"),
					timeDao.buscarPorId(rs.getLong("time")));
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao criar o objeto Factory");
		}
		return jogador;
	}
	
	public boolean remover(Long id) {
		String sql = "DELETE FROM jogadores where id = ?;";
		boolean retorno = false;
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			retorno = true;
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao remover Jogador!");
		} finally {
			cnn.desconectar();
		}
		return retorno;
	}
	
	public Jogador buscarPorId(Long id) {
		String sql = "SELECT * FROM jogadores where id = ?;";
		Jogador jogador = new Jogador();
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				jogador = objectFactory(rs);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao buscar Jogador por id!");
		} finally {
			//cnn.desconectar();
		}
		return jogador;
	}
	
	public List<Jogador> listarTodos() {
		String sql = "SELECT * FROM jogadores ORDER BY nome;";
		List<Jogador> jogadores = new ArrayList<>();
		
		try {
			cnn.conectar();
			stmt = cnn.getPreparedStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				jogadores.add(objectFactory(rs));
			}
		} catch (SQLException e) {
			throw new ExcecaoGeral("Erro ao listar todos os Jogadores!");
		} finally {
			cnn.desconectar();
		}
		return jogadores;
	}
}
