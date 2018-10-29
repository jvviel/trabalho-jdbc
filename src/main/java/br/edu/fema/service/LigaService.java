package br.edu.fema.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.fema.dao.LigaDao;
import br.edu.fema.model.Liga;
import br.edu.fema.model.Mensagem;

@Service
public class LigaService {

	public Liga buscarPorId(Long id) {
		LigaDao ligaDao = new LigaDao();
		
		Liga liga = ligaDao.buscarPorId(id);
		if(liga == null) {
			throw new RuntimeException();
		}
		return liga;
	}
	
	public List<Liga> listarTodos() {
		LigaDao ligaDao = new LigaDao();
		List<Liga> ligas = ligaDao.listarTodos();
		
		return ligas;
	}
	
	public ResponseEntity<?> gravar(Liga liga) {
		LigaDao ligaDao = new LigaDao();;
		
		boolean retorno = ligaDao.inserir(liga);
		if (retorno) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Mensagem("Liga salva com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível salvar a Liga!"));
		}
	}
	
	public ResponseEntity<?> alterar(Liga liga, Long id) {
		LigaDao ligaDao = new LigaDao();
		
		boolean retorno = ligaDao.alterar(liga, id);
		if(retorno) {
			return ResponseEntity.ok(new Mensagem("Liga alterado com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível alterar a Liga!"));
		}
	}
	
	public ResponseEntity<?> remover(Long id) {
		LigaDao ligaDao = new LigaDao();
		
		boolean retorno = ligaDao.remover(id);
		if(retorno) {
			return ResponseEntity.status(HttpStatus.OK).body(new Mensagem("Liga removido com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível remover a Liga!"));
		}
	}
}
