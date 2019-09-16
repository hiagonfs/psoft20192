package disciplineControl.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Disciplina implements Comparable<Disciplina> {

	private String nome;
	private float nota;
	@Id
	@GeneratedValue
	private Long id;

	public Disciplina(String nome, float nota) {
		this.nome = nome;
		this.nota = nota;
	}

	public Disciplina() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + Float.floatToIntBits(nota);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Float.floatToIntBits(nota) != Float.floatToIntBits(other.nota))
			return false;
		return true;
	}

	@Override
	public int compareTo(Disciplina d) {
		if (this.getNota() > d.getNota()) {
			return -1;
		} else if (this.getNota() < d.getNota()) {
			return 1;
		}
		return 0;
	}

}
