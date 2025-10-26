package model;

public class Prescricao {
    private Integer idPrescricao;
    private Integer quantidade;
    private String instrucoes;
    private Integer idConsulta;
    private Integer idVeterinario;
    private Integer idProduto;
    private Produto produto;

    public Prescricao() {}

    public Prescricao(Integer quantidade, String instrucoes, Integer idConsulta, Integer idVeterinario, Integer idProduto) {
        this.quantidade = quantidade;
        this.instrucoes = instrucoes;
        this.idConsulta = idConsulta;
        this.idVeterinario = idVeterinario;
        this.idProduto = idProduto;
    }

    // Getters e Setters
    public Integer getIdPrescricao() { return idPrescricao; }
    public void setIdPrescricao(Integer idPrescricao) { this.idPrescricao = idPrescricao; }
    
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    
    public String getInstrucoes() { return instrucoes; }
    public void setInstrucoes(String instrucoes) { this.instrucoes = instrucoes; }
    
    public Integer getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Integer idConsulta) { this.idConsulta = idConsulta; }
    
    public Integer getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(Integer idVeterinario) { this.idVeterinario = idVeterinario; }
    
    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }
    
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
}