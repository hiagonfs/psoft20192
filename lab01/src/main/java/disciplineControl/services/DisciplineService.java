package disciplineControl.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import disciplineControl.entities.Disciplina;

@Service
public class DisciplineService {

	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private int idDiscipline = 1;

	public List<Disciplina> getDisciplines() {
		return disciplinas;
	}

	public Disciplina addDisciplina(Disciplina newDiscipline) {
		Disciplina d = newDiscipline;
		d.setId(idDiscipline);
		disciplinas.add(d);
		idDiscipline++;
		return d;
	}

	public Disciplina getDisciplinas(int id) {
		for (int i = 0; i < disciplinas.size(); i++) {
			if (disciplinas.get(i).getId() == id) {
				return disciplinas.get(i);
			}
		}
		return null;
	}

	public Disciplina setNomeDisciplina(Disciplina d) {
		Disciplina dSearch = getDisciplinas(d.getId());
		dSearch.setNome(d.getNome());
		return dSearch;
	}
}
