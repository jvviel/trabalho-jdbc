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

import br.edu.fema.model.Liga;
import br.edu.fema.service.LigaService;

@RestController
@RequestMapping("/ligas")
public class LigaResource {

	@Autowired
	private LigaService ligaService;
	
	@GetMapping("/{id}")
	public Liga buscarLiga(@PathVariable Long id) {
		return ligaService.buscarPorId(id);
	}
	
	@GetMapping
	public List<Liga> listarTodos() {
		return ligaService.listarTodos();
	}
	
	@PostMapping
	public ResponseEntity<?> gravarLiga(@RequestBody Liga liga) {
		return ligaService.gravar(liga);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarEsporte(@RequestBody Liga liga, @PathVariable Long id) {
		return ligaService.alterar(liga, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerEsporte(@PathVariable Long id) {
		return ligaService.remover(id);
	}
}
