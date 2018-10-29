package br.edu.fema.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.fema.dao.TimeDao;
import br.edu.fema.model.Mensagem;
import br.edu.fema.model.Time;

@Service
public class TimeService {

	public Time buscarPorId(Long id) {
		TimeDao timeDao = new TimeDao();
		
		Time time = timeDao.buscarPorId(id);
		if(time == null) {
			throw new RuntimeException();
		}
		return time;
	}
	
	public List<Time> listarTodos() {
		TimeDao timeDao = new TimeDao();
		List<Time> times = timeDao.listarTodos();
		
		return times;
	}
	
	public ResponseEntity<?> gravar(Time time) {
		TimeDao timeDao = new TimeDao();
		
		boolean retorno = timeDao.inserir(time);
		if (retorno) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Mensagem("Time salvo com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível salvar o time!"));
		}
	}
	
	public ResponseEntity<?> alterar(Time time, Long id) {
		TimeDao timeDao = new TimeDao();
		
		boolean retorno = timeDao.alterar(time, id);
		if(retorno) {
			return ResponseEntity.ok(new Mensagem("Time alterado com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível alterar o Time!"));
		}
	}
	
	public ResponseEntity<?> remover(Long id) {
		TimeDao timeDao = new TimeDao();
		
		boolean retorno = timeDao.remover(id);
		if(retorno) {
			return ResponseEntity.status(HttpStatus.OK).body(new Mensagem("Time removido com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível remover o time!"));
		}
	}
}
