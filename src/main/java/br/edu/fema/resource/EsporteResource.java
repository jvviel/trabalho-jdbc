package br.edu.fema.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.model.Esporte;
import br.edu.fema.service.EsporteService;

@RestController
@RequestMapping("/esportes")
public class EsporteResource {

	@Autowired
	private EsporteService esporteService;
	
	@GetMapping("/{id}")
	public Esporte buscarEsporte(@PathVariable Long id) {
		return esporteService.buscarPorId(id);
	}
	
	@GetMapping
	public List<Esporte> listarTodos() {
		return esporteService.listarTodos();
	}
	
	@PostMapping
	public ResponseEntity<?> gravarEsporte(@RequestBody Esporte esporte) {
		return esporteService.gravar(esporte);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarEsporte(@RequestBody Esporte esporte, @PathVariable Long id) {
		return esporteService.alterar(esporte, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerEsporte(@PathVariable Long id) {
		return esporteService.remover(id);
	}
}
