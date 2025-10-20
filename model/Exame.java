package model;

import java.time.LocalDate;

public class Exame {
    private Integer idExame;
    private String tipo;
    private String resultado;
    private LocalDate data;
    private Integer idConsulta;

    public Exame() {}

    public Exame(String tipo, String resultado, LocalDate data, Integer idConsulta) {
        this.tipo = tipo;
        this.resultado = resultado;
        this.data = data;
        this.idConsulta = idConsulta;
    }

    // Getters e Setters
    public Integer getIdExame() { return idExame; }
    public void setIdExame(Integer idExame) { this.idExame = idExame; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
    
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    
    public Integer getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Integer idConsulta) { this.idConsulta = idConsulta; }
}