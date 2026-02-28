import java.util.List;

public class TesteUsuarioDAO {
    public static void main(String[] args) {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        
        List<Usuario> usuarios = usuarioDAO.getAllUsuarios();
        for (Usuario u : usuarios) {
            System.out.println(u.getNome() + " - " + u.getEmail());
        }
        
        Usuario usuario = usuarioDAO.getUsuario("11111");
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario.getNome());
        }
        
        if (usuario != null) {
            usuario.setNome("Novo Nome");
            usuarioDAO.updateUsuario(usuario);
        }
                usuarioDAO.closeConnection();
    }
}