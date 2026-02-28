public class Usuario {
    private String matricula;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private String turma;
    private String setor;

    //gets

    public String getMatricula (){
        return matricula;
    }

    public String getNome (){
        return nome;
    }

    public String getEmail (){
        return email;
    }

    public String getSenha (){
        return senha;
    }

    public String getCargo (){
        return cargo;
    }

    public String getTurma (){
        return turma;
    }

    public String getSetor (){
        return setor;
    }

    //sets

    public void setMatricula (String matricula){
        this.matricula = matricula;
    }

    public void setNome (String nome){
        this.nome = nome;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public void setSenha (String senha){
        this.senha = senha;
    }

    public void setCargo (String cargo){
        this.cargo = cargo;
    }

    public void setTurma (String turma){
        this.turma = turma;
    }

    public void setSetor (String setor){
        this.setor = setor;
    }
    
}
