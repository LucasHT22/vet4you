package model;

public class Veterinario {
    private Integer idVeterinario;
    private String nome;
    private String especialidade;
    private Integer crmvet;

    public Veterinario() {}

    public Veterinario(String nome, String especialidade, Integer crmvet) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crmvet = crmvet;
    }

    // Getters e Setters
    public Integer getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(Integer idVeterinario) { this.idVeterinario = idVeterinario; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    
    public Integer getCrmvet() { return crmvet; }
    public void setCrmvet(Integer crmvet) { this.crmvet = crmvet; }
}