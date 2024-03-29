/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML.ControlaXML;

import Modelo.Dao.PessoaDao;
import Modelo.Negocio.Pessoa;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author aluno
 */
public class Xml {
    Document doc;
    public Xml(String caminho)
    {
     
        try {
            DocumentBuilderFactory fabrica=DocumentBuilderFactory.newInstance();
            DocumentBuilder construtor=fabrica.newDocumentBuilder();
            doc=construtor.parse(caminho);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void mostraContatos(){
        try {
            PessoaDao dao = new PessoaDao();
            List<Pessoa> lista = dao.pegar();
             Element raiz=doc.getDocumentElement();
            Element pessoa = null;
            
            for(Pessoa p :lista){
                pessoa=doc.createElement("Pessoa");
                 Element nome=doc.createElement("nome");
                 Element telefone=doc.createElement("telefone");
                 Element acao=doc.createElement("acao");
 
        
        nome.appendChild(doc.createTextNode(p.getNome()));
        
        telefone.appendChild(doc.createTextNode(p.getTelefone()));
        
        acao.appendChild(doc.createTextNode("Consulta"));
        pessoa.appendChild(acao);
        pessoa.appendChild(nome);
        pessoa.appendChild(telefone);
       
        raiz.appendChild(pessoa);
      
            }
            
            dao.sair();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    public void criaContato(String pessoaXml){
       
        
        String gravarNome=null;
        String gravarTelefone=null;
        String[] arr = pessoaXml.split("<Pessoa>");

    // busca na string alvo cada pedaço da string separada
    for (String s : arr) { 
        if (s.contains("<acao>Inserir</acao>")) {
            String nome[]=s.split("<nome>");
            String nomePessoa[]=nome[1].split("</nome>");
            String telefone[]=s.split("<telefone>");
            String telefonePessoa[]=telefone[1].split("</telefone>");
           
            gravarNome=nomePessoa[0];
            
            gravarTelefone=telefonePessoa[0];
        }
    }
    
        
        try {
            Pessoa p=new Pessoa();
            PessoaDao dao = new PessoaDao();
           p.setNome(gravarNome);
           p.setTelefone(gravarTelefone);
           dao.criar(p);
            dao.sair();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
     public void serealizar(PrintStream out)
    {
        try {
            TransformerFactory fabrica=TransformerFactory.newInstance();
            Transformer transformador=fabrica.newTransformer();
            DOMSource fonte=new DOMSource(doc);
            StreamResult saida=new StreamResult(out);
            transformador.transform(fonte, saida);
        } catch (TransformerException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void serealizar(Writer out)
    {
        try {
            TransformerFactory fabrica=TransformerFactory.newInstance();
            Transformer transformador=fabrica.newTransformer();
            DOMSource fonte=new DOMSource(doc);
            StreamResult saida=new StreamResult(out);
            transformador.transform(fonte, saida);
        } catch (TransformerException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        }
 
   }
    
 

}
