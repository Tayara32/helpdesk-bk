package model;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private String pergunta;  // assunto/descrição do problema
    private LocalDateTime dataAbertura;
    private String status; // Aberto | Em Andamento | Resolvido

    private String nomeUtilizador;

    public Ticket() {}

    public Ticket(int id, String pergunta, LocalDateTime dataAbertura, String status) {
        this.id = id;
        this.pergunta = pergunta;
        this.dataAbertura = dataAbertura;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPergunta() { return pergunta; }
    public void setPergunta(String pergunta) { this.pergunta = pergunta; }

    public LocalDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDateTime dataAbertura) { this.dataAbertura = dataAbertura; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNomeUtilizador() { return nomeUtilizador; }
    public void setNomeUtilizador(String nomeUtilizador) { this.nomeUtilizador = nomeUtilizador; }
}
