/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Modelo.Negocio.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author vinicius
 */
public class PessoaDao {
    Connection con;
    public PessoaDao() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/teste","root","lapo");
    }
    public void sair() throws SQLException
    {
        if(con!=null)
            con.close();
    }
    public void criar(Pessoa p) throws SQLException
    {
        PreparedStatement ps=con.prepareStatement("insert into pessoa values(null,?,?)");
        ps.setString(1, p.getNome());
        ps.setString(2, p.getTelefone());
        ps.executeUpdate();
        ps.close();
    }
    public Pessoa pegar(int  codigo) throws SQLException
    {
        PreparedStatement ps=con.prepareStatement("select * from pessoa where codigo=?");
        ps.setInt(1, codigo);
        ResultSet rs=ps.executeQuery();
        Pessoa p=null;
        if(rs.next())
        {
            p=new Pessoa();
            p.setCodigo(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setTelefone(rs.getString("telefone"));
        }
        ps.close();
        return p;
    }
    public  List<Pessoa> pegar() throws SQLException
    {
        PreparedStatement ps=con.prepareStatement("select * from pessoa");
        ResultSet rs=ps.executeQuery();
        List<Pessoa> lista=new ArrayList<>();
       
        while(rs.next())
        {
             Pessoa p=new Pessoa();
            p.setCodigo(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setTelefone(rs.getString("telefone"));
            lista.add(p);
        }
        ps.close();
        return lista;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        PessoaDao dao=new PessoaDao();
    
        List<Pessoa> lista=dao.pegar();
        for(Pessoa p:lista)
            System.out.println("Codigo: "+p.getCodigo()+", Nome: "+p.getNome()+
                ", Idade: "+p.getTelefone());
        
        dao.sair();
    }
}
