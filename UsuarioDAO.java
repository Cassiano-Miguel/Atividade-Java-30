import java.util.List;

public interface UsuarioDAO {
    public List<Usuario> getAllUsuarios();
    public Usuario getUsuario (String matricula);
    public void updateUsuario (Usuario Usuario);
    public void deleteUsuario (Usuario Usuario);
    
}
