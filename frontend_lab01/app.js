console.log("app rodando!"); 

let URL = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas'; 

function testandoOConsole() {
    console.log("Foi!"); 
}

function fetch_disciplinas() {

    fetch(URL)
    .then(resposta => resposta.json())
    .then(dados => {
        console.log(dados);
    })

}

window.teste = testandoOConsole; 

window.fd = fetch_disciplinas;
