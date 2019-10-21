exports.disciplina = disciplina; 
exports.turma = turma; 
exports.professor = professor; 

function disciplina(id, nome, creditos, pre_requisitos) {  

    let _id = id;
  
      _disciplina = {
  
        // atributos
          nome,
          creditos,
          pre_requisitos: [],
          
          // functions

          id: () => _id,
          set_nome: (novoNome) => nome = novoNome,
          get_nome: () => nome,
          addPreRequisito: (pre_requisito) => pre_requisitos.push(pre_requisito)

      };
  
      return _disciplina; 
  
}

function turma(disciplina, periodo) {

    let _periodo = periodo;

    _turma = {

        // atributos
        disciplina, 
        nomeProfessor : null,
        estudantesMatriculados: [],
        statusDaTurma: null,

        // functions
        periodo: () => _periodo, 
        getProfessor: () => nomeProfessor,
        defineProfessor: (nomeDoProfessor) => nomeProfessor = nomeDoProfessor,
        matriculaEstudante: (estudante) => estudantesMatriculados.push(estudante),
        get_estudantesMatriculados: () => 0,
        set_status: (novoStatus) => statusDaTurma = novoStatus,
        desmatriculaEstudante: (estudante) => estudantesMatriculados.forEach((element, i) => {
            console.log(element, i);
        })

    };

    return _turma; 

}  

function professor (matricula, nome, rg, cpf) {

    _professor = {

        matricula,
        nome,
        cpf,
        rg,

        turmas: [],

        aloca_turma: (t) => turmas.push(t), 
        turmas: (semestre) => {}

    };

    return _professor;
}


