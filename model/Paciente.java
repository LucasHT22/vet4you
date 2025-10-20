package model;

public class Paciente {
    private Integer idPaciente;
    private String nome;
    private String raca;
    private String animal;
    private Integer idade;
    private Character sexo;
    private Integer idUsuario;
    private Integer plano;
    private Usuario usuario;
    private Cartao cartao;

    public Paciente() {}

    public Paciente(String nome, String raca, String animal, Integer idade, Character sexo, Integer idUsuario, Integer plano) {
        this.nome = nome;
        this.raca = raca;
        this.animal = animal;
        this.idade = idade;
        this.sexo = sexo;
        this.idUsuario = idUsuario;
        this.plano = plano;
    }

    // Getters e Setters
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    
    public String getAnimal() { return animal; }
    public void setAnimal(String animal) { this.animal = animal; }
    
    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }
    
    public Character getSexo() { return sexo; }
    public void setSexo(Character sexo) { this.sexo = sexo; }
    
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    
    public Integer getPlano() { return plano; }
    public void setPlano(Integer plano) { this.plano = plano; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public Cartao getCartao() { return cartao; }
    public void setCartao(Cartao cartao) { this.cartao = cartao; }
}