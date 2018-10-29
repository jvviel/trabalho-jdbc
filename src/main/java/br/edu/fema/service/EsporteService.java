package br.edu.fema.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.fema.dao.EsporteDao;
import br.edu.fema.model.Esporte;
import br.edu.fema.model.Mensagem;

@Service
public class EsporteService {
	
	public Esporte buscarPorId(Long id) {
		EsporteDao esporteDao = new EsporteDao();
		
		Esporte esporte = esporteDao.buscarPorId(id);
		if(esporte == null) {
			throw new RuntimeException();
		}
		return esporte;
	}
	
	public List<Esporte> listarTodos() {
		EsporteDao esporteDao = new EsporteDao();
		List<Esporte> esportes = esporteDao.listarTodos();
		
		return esportes;
	}
	
	public ResponseEntity<?> gravar(Esporte esporte) {
		EsporteDao esporteDao = new EsporteDao();
		
		boolean retorno = esporteDao.inserir(esporte);
		if (retorno) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Mensagem("Esporte salvo com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível salvar o Esporte!"));
		}
	}
	
	public ResponseEntity<?> alterar(Esporte esporte, Long id) {
		EsporteDao esporteDao = new EsporteDao();
		
		boolean retorno = esporteDao.alterar(esporte, id);
		if(retorno) {
			return ResponseEntity.ok(new Mensagem("Esporte alterado com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível alterar o Esporte!"));
		}
	}
	
	public ResponseEntity<?> remover(Long id) {
		EsporteDao esporteDao = new EsporteDao();
		
		boolean retorno = esporteDao.remover(id);
		if(retorno) {
			return ResponseEntity.status(HttpStatus.OK).body(new Mensagem("Esporte removido com sucesso!"));
		} else {
			return ResponseEntity.badRequest().body(new Mensagem("Não foi possível remover o Esporte"));
		}
	}
}
