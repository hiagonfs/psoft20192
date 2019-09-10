console.log("app rodando!"); 

let disciplinas = []; 

const endereço = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas'; 

function fetch_disciplinas(dados) {

    disciplinas = dados;
    let $disciplinas = document.querySelector("#disciplinas"); 
    $disciplinas.innerHTML = ''; 
    disciplinas.forEach((e,i) => {
        let $p = document.createElement("p");
        $disciplinas.appendChild($p); 
        $p.innerText = "Nome: " + disciplinas[i].nome + ", Nota: " + disciplinas[i].nota;  
    }) 
}

function save_disciplina() {

    let nome = document.querySelector("#nome").value;
    let nota = document.querySelector("#nota").value; 

    fetch(endereço, {
        'method' : 'POST',
        'body': `{"nome": "${nome}", "nota": ${nota}}`,
        'headers': {'Content-Type': 'application/json'}
    })
    .then(r => r.json())
    .then(d => {
        disciplinas.push(d);
        fetch_disciplinas(disciplinas);
    }); 

    document.querySelector("#nome").value = '';
    document.querySelector("#nota").value = '';  
    
}

(function run() {
    let $button = document.querySelector("#buttonCadastro");
    $button.addEventListener('click', save_disciplina);
    fetch(endereço)
    .then(r => r.json())
    .then(fetch_disciplinas)
}()); 

