package br.edu.fema.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.fema.dao.JogadorDao;
import br.edu.fema.model.Jogador;
import br.edu.fema.model.Mensagem;

@Service
public class JogadorService {

	public Jogador buscarPorId(Long id) {
		JogadorDao jogadorDao = new JogadorDao();
		
		Jogador jogador = jogadorDao.buscarPorId(id);
		if(jogador == null) {
			throw new RuntimeException();
		}
		return jogador;
	}
	
	public List<Jogador> listarTodos() {
		JogadorDao jogadorDao = new JogadorDao();
		List<Jogador> jogadores = jogadorDao.listarTodos();
		
		return jogadores;
	}
	
	public ResponseEntity<?> gravar(Jogador jogador) {
		JogadorDao jogadorDao = new JogadorDao();
		
		boolean retorno = jogadorDao.inserir(jogador);
		if (retorno) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Mensagem("Jogador salvo com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível salvar o jogador!"));
		}
	}
	
	public ResponseEntity<?> alterar(Jogador jogador, Long id) {
		JogadorDao jogadorDao = new JogadorDao();
		
		boolean retorno = jogadorDao.alterar(jogador, id);
		if(retorno) {
			return ResponseEntity.ok(new Mensagem("Jogador alterado com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível alterar o Jogador!"));
		}
	}
	
	public ResponseEntity<?> remover(Long id) {
		JogadorDao jogadorDao = new JogadorDao();
		
		boolean retorno = jogadorDao.remover(id);
		if(retorno) {
			return ResponseEntity.status(HttpStatus.OK).body(new Mensagem("Jogador removido com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível remover o jogador!"));
		}
	}
}
