package br.edu.fema.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FabricaConexao {

	private static FabricaConexao gerenciaConexoes = null;
	
    private static Connection conn = null;

    private static PreparedStatement stmt = null;

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String IP = "localhost";

    private static final String MySQL = "jdbc:mysql://";

    private static final String DATABASE = "javafacul";

    private static final String USER = "root";
    
    private static final String PASSWORD = "660804";

    /**
     * Construtor private, garante que o único método que poderá criar uma nova
     * instância desta classe será o método getInstance()
     *
     */
    private FabricaConexao() {
    }

    /**
     * Método responsável pelo controle da existência de apenas uma instância
     * desta classe.
     *
     * @return GerenciaConexoes
     *
     */
    public static FabricaConexao getInstance() {

        if (gerenciaConexoes == null) {
           
            gerenciaConexoes = new FabricaConexao();
        }
        return gerenciaConexoes;
    }

    /**
     * Método responsável por conectar à base de dados
     *
     */
    public void conectar() {

        if (conn == null) {
            try {
 
                String dbPostgree = MySQL + IP + "/" + DATABASE;

                Class.forName(DRIVER);
      
                conn = DriverManager.getConnection(dbPostgree, USER, PASSWORD);
      
                conn.setAutoCommit(true);
             
                System.out.println("Conectou!!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Erro na Classe");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro em sql no metodo conectar");
            }
        }
    }

    /**
     * Método responsável por desconectar da base de dados
     *
     */
    public void desconectar()  {
       
        if (conn != null) {
            try {
                
                if (stmt != null) {
                    
                    stmt.close();
                }
                
                conn.close();
                
                conn = null;
                stmt = null;
                
                System.out.println("Desconectou!!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Erro ao Desconectar");
            }
        } 
    }

    /**
     * Método responsável por retornar um PreparedStatement
     *
     * @return PreparedStatement
     *
     */
    public PreparedStatement getPreparedStatement(String statement) {
        try {
            if (conn == null) {
                conectar();
            }
            
            stmt = conn.prepareStatement(statement);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no metodo PrepareStatement");
        }
        return stmt;
    }
}
