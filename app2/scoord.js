function disciplina(id, nome, creditos, pre_requisitos) {

    let _id = id; 
  
      _disciplina = {
  
        // atributos
          id: _id,
          nome : nome,
          creditos : creditos,
          pre_requisitos: [],
          
          // functions
          set_id: (novoId) => id = novoId,
          get_id: () => id,
          set_nome: (novoNome) => nome = novoNome,
          get_nome: () => nome,
          addPreRequisito: (pre_requisito) => pre_requisitos.push(pre_requisito)

  
      };
  
      return _disciplina; 
  
}

function turma() {

    _turma = {

        // atributos
        numero : numero, 
        nomeProfessor : null,
        estudantesMatriculados: [],
        statusDaTurma: null,

        // functions
        getProfessor: () => nomeProfessor,
        defineProfessor: (nomeDoProfessor) => nomeProfessor = nomeDoProfessor,
        matriculaEstudante: (estudante) => estudantesMatriculados.push(estudante),
        get_estudantesMatriculados: () => estudantesMatriculados,
        set_status: (novoStatus) => statusDaTurma = novoStatus

    };

    return _turma; 

}  

function Professor (matricula, nome, rg, cpf) {

    _professor = {

        matricula : matricula,
        nome: nome,
        cpf: cpf,
        rg: rg,

        turmas: [],

        aloca_turma: (t) => turmas.push(t), 
        turmas: (semestre) => {}

    };

    return _professor;
}