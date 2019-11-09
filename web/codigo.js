function criarAjaxGet(recurso, funcao)
        {
        ajax = new XMLHttpRequest();
                ajax.onreadystatechange = funcao;
                ajax.open("GET", recurso, true);
                ajax.send();
                }
                
            function criarAjaxPost(recurso)
{
    console.log('criarAjaxPost(' + recurso + ')');
    let nome = document.getElementById('nome').value;
    let telefone = document.getElementById('telefone').value;
    
    ajax = new XMLHttpRequest();
    ajax.onreadystatechange = function () {
        alertar("contato inserido!", this);
    }
    ajax.open("POST", recurso, true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    let xml;
    
    xml = "xml=<Pessoa><nome>" + nome + "</nome>" +
    "<telefone>" + telefone + "</telefone>" +
    "<acao>Inserir</acao></Pessoa>";
    
    console.log(xml);
    ajax.send(xml);
}
function mostrar()
        {
        if (this.readyState == 4 && this.status == 200)
        {
        doc = this.responseXML;
             
             raiz = doc.documentElement;
                texto = "";
                filhos =raiz.childNodes;
           tam = filhos.length;
                j = 1;
                for (i = 0; i < tam; i++)
        {        
        {
        if (filhos[i].nodeType == 1)
        {
      
        document.getElementById('la').innerHTML+=filhos[i].children[0].childNodes[0].nodeValue+": "+filhos[i].children[1].childNodes[0].nodeValue+"<br>"
        
        }
        }
       
        }
        }}




