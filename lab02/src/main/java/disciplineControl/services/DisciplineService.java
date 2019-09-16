package disciplineControl.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import disciplineControl.entities.Disciplina;
import disciplineControl.repository.DisciplinaRepository;

@Service
public class DisciplineService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@PostConstruct
	public void initDisciplines() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>() {
		};
		InputStream input = TypeReference.class.getResourceAsStream("/json/disciplinas.json");
		try {
			List<Disciplina> disciplinas = mapper.readValue(input, typeReference);
			this.disciplinaRepository.save(disciplinas);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de alunos!");
		}

	}

	public Disciplina addDisciplina(Disciplina newDiscipline) {
		Disciplina d = newDiscipline;
		d.setId(idDiscipline);
		disciplinas.add(d);
		idDiscipline++;
		return d;
	}

	public Disciplina deleteDisciplina(int id) {
		Disciplina d = getDisciplinas(id);
		removeDisciplina(id);
		return d;
	}

	private void removeDisciplina(int id) {
		for (int i = 0; i < disciplinas.size(); i++) {
			if (disciplinas.get(i).getId() == id) {
				disciplinas.remove(i);
			}
		}
	}

	public Disciplina getDisciplinas(int id) {
		for (int i = 0; i < disciplinas.size(); i++) {
			if (disciplinas.get(i).getId() == id) {
				return disciplinas.get(i);
			}
		}
		return null;
	}

	public Disciplina setNomeDisciplina(int id, Disciplina d) {
		removeDisciplina(id);
		disciplinas.add(d);
		return d;
	}

	public Disciplina setNotaDisciplina(int id, Disciplina d) {
		removeDisciplina(id);
		disciplinas.add(d);
		return d;
	}

	public List<Disciplina> getDisciplinesOrdered() {
		Collections.sort(disciplinas);
		return disciplinas;
	}

}
