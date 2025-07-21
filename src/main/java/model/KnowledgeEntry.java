package model;

import java.time.LocalDateTime;

public class KnowledgeEntry {
    private int id;
    private String pergunta;
    private String resposta;
    private boolean eficaz; // usamos como "ativo" no backoffice
    private LocalDateTime ultimaAtualizacao;

    public KnowledgeEntry() {}

    public KnowledgeEntry(int id, String pergunta, String resposta, boolean eficaz, LocalDateTime ultimaAtualizacao) {
        this.id = id;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.eficaz = eficaz;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPergunta() { return pergunta; }
    public void setPergunta(String pergunta) { this.pergunta = pergunta; }

    public String getResposta() { return resposta; }
    public void setResposta(String resposta) { this.resposta = resposta; }

    public boolean isEficaz() { return eficaz; }
    public void setEficaz(boolean eficaz) { this.eficaz = eficaz; }

    public LocalDateTime getUltimaAtualizacao() { return ultimaAtualizacao; }
    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) { this.ultimaAtualizacao = ultimaAtualizacao; }
}