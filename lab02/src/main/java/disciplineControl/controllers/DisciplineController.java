package disciplineControl.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import disciplineControl.entities.Disciplina;
import disciplineControl.entities.Usuario;
import disciplineControl.services.DisciplineService;

@RestController
public class DisciplineController {

	private DisciplineService disciplineService;

	public DisciplineController(DisciplineService dService) {
		super();
		this.disciplineService = dService;
	}

	@RequestMapping("/disciplinas")
	public ResponseEntity<List<Disciplina>> getDisciplines() {
		return new ResponseEntity<List<Disciplina>>(disciplineService.getDisciplinas(), HttpStatus.OK);
	}

	@PostMapping("/disciplinas")
	public ResponseEntity<Disciplina> addNewDiscipline(@RequestBody Disciplina discipline) {
		return new ResponseEntity<Disciplina>(disciplineService.addDisciplina(discipline), HttpStatus.CREATED);
	}

	@RequestMapping("/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDiscipline(@PathVariable Long id) {

		Optional<Disciplina> disciplinaEncontrada = disciplineService.getDisciplina(id);

		if (disciplinaEncontrada.isPresent()) {
			return new ResponseEntity<Disciplina>(disciplinaEncontrada.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}

	}

	@PutMapping("/disciplinas/{id}/nome")
	@Transactional
	public ResponseEntity<Disciplina> setNomeDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {

		Optional<Disciplina> d = disciplineService.setNomeDisciplina(id, disciplina);

		if (d.isPresent()) {
			return new ResponseEntity<Disciplina>(d.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}

	}

	@PutMapping("/disciplinas/{id}/nota")
	public ResponseEntity<Disciplina> setNotaDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {

		Optional<Disciplina> d = disciplineService.setNotaDisciplina(id, disciplina);

		if (d.isPresent()) {
			return new ResponseEntity<Disciplina>(d.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}
	}

	@DeleteMapping("/disciplinas/{id}")
	public ResponseEntity<Disciplina> removeDisciplinaId(@PathVariable Long id) {

		Optional<Disciplina> disciplinaEncontrada = disciplineService.getDisciplina(id);

		if (disciplinaEncontrada.isPresent()) {
			return new ResponseEntity<Disciplina>(disciplineService.removeDisciplina(id).get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}

	}

	@RequestMapping("/disciplinas/ranking")
	public ResponseEntity<List<Disciplina>> getDisciplinesOrdered() {
		return new ResponseEntity<List<Disciplina>>(disciplineService.disciplinasRankedas(), HttpStatus.OK);
	}

	@PutMapping("/disciplinas/likes/{id}")
	public ResponseEntity<Disciplina> incrementaLike(@RequestBody Usuario usuario, @PathVariable Long id)
			throws Exception {
		if (!disciplineService.getDisciplina(id).isPresent()) {
			throw new Exception("Disciplina nao encontrada!");
		}
		return new ResponseEntity<Disciplina>(disciplineService.addLikeNaDisciplina(id).get(), HttpStatus.OK);
	}

}
