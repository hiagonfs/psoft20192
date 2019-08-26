package disciplineControl.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import disciplineControl.entities.Disciplina;
import disciplineControl.services.DisciplineService;

@RestController
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;

	@RequestMapping("/")
	public String index() {
		return "...";
	}

	@RequestMapping("/v1/api/disciplinas")
	public ResponseEntity<List<Disciplina>> getDisciplines() {
		return new ResponseEntity<List<Disciplina>>(disciplineService.getDisciplines(), HttpStatus.OK);
	}

	@PostMapping("/v1/api/disciplinas")
	public ResponseEntity<Disciplina> addNewDiscipline(@RequestBody Disciplina discipline) {
		return new ResponseEntity<Disciplina>(disciplineService.addDisciplina(discipline), HttpStatus.CREATED);
	}

	@RequestMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDiscipline(@RequestParam(value = "id") int id) {

		Disciplina disciplinaEncontrada = disciplineService.getDisciplinas(id);

		if (disciplinaEncontrada != null) {
			return new ResponseEntity<Disciplina>(disciplinaEncontrada, HttpStatus.OK);
		} else {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/v1/api/disciplinas/{id}/nome")
	public ResponseEntity<Disciplina> setDisciplina(@PathVariable int id, @RequestBody Disciplina disciplina) {
		
		Disciplina disciplinaEncontrada = disciplineService.getDisciplinas(id);

		if (disciplinaEncontrada != null) {
			return new ResponseEntity<Disciplina>(disciplineService.setNomeDisciplina(disciplina), HttpStatus.OK);
		} else {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
	}

}
