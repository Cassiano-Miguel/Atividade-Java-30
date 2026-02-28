import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection con;

    public UsuarioDAOImpl() {
        try {
            this.con = ConnectionFabrica.getConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco: " + e.getMessage());
        }
    }

    @Override 
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setMatricula(rs.getString("matricula"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCargo(rs.getString("cargo"));
                usuario.setTurma(rs.getString("turma"));
                usuario.setSetor(rs.getString("setor"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuario(String matricula) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE matricula = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setMatricula(rs.getString("matricula"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setCargo(rs.getString("cargo"));
                    usuario.setTurma(rs.getString("turma"));
                    usuario.setSetor(rs.getString("setor"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, cargo = ?, turma = ?, setor = ? WHERE matricula = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCargo());
            stmt.setString(5, usuario.getTurma());
            stmt.setString(6, usuario.getSetor());
            stmt.setString(7, usuario.getMatricula());
            
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println(linhasAfetadas + " linha(s) atualizada(s)");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        String sql = "DELETE FROM usuarios WHERE matricula = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getMatricula());
            
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println(linhasAfetadas + " linha(s) deletada(s)");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
