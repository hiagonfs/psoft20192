let assert = require('assert');
let disciplina = require('./scoord').disciplina;
let turma = require('./scoord').turma;

describe('factory Disciplina', function() {
  let d0;

    before(async () => {
        d0 = disciplina('prog1', 'Programação 1', 4, []); 
    })

    it('deve criar disciplinas distintas a cada invocação', function(){
        d0 = disciplina('prog1', 'Programação 1', 4, []);
        d1 = disciplina('prog1', 'Programação 1', 4, []);
        d2 = disciplina('prog1', 'Programação 1', 4, []);
        assert.notEqual(d0, d1);
        assert.notEqual(d0, d2);
        assert.notEqual(d1, d2);
    });

    it('deve reter os dados de inicialização', function(){
        assert.equal('prog1', d0.id());
        assert.equal('Programação 1', d0.get_nome());
        assert.equal(4, d0.creditos);
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('deve permitir atualização de nome', function(){
        d0.set_nome('Programação de Computadores I')
        assert.equal('prog1', d0.id());
        assert.equal('Programação de Computadores I', d0.get_nome());
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('não deve permitir atualização de id via set_id', function(){
        assert.throws(function () {
            d0.set_id('outro')
        }, TypeError);
        assert.equal('prog1', d0.id());
    });

});


describe('factory Turma', function() {
    
    let t0;
    let d0 = disciplina('prog1', 'Programação 1', 4, []);
  
      before(async () => {
          t0 = turma(d0, 1); 
      })
  
      it('deve criar turmas distintas a cada invocação', function(){
          t0 = turma(d0, 1);
          t1 = turma(d0, 1);
          t2 = turma(d0, 1);
          assert.notEqual(t0, t1);
          assert.notEqual(t0, t2);
          assert.notEqual(t1, t2);
      });

      it('deve reter os dados de inicialização', function(){
        assert.equal('prog1', t0.disciplina.id());
        assert.equal('Programação 1', t0.disciplina.get_nome());
        assert.equal(1, t0.periodo());
        assert.equal(0, t0.get_estudantesMatriculados().length);
    });

    it('deve permitir atualização de nome do professor', function(){
        t0.defineProfessor('Dalton')
        assert.equal('Dalton', t0.getProfessor());
    });
  
  });
