package controller;

import dao.KnowledgeBaseDAO;
import model.KnowledgeEntry;

import javax.swing.*;
import java.util.List;

public class PerguntaController {
    private final KnowledgeBaseDAO dao = new KnowledgeBaseDAO();

    public List<KnowledgeEntry> listar() {
        return dao.listarTodos();
    }

    public Object[][] toTableData(List<KnowledgeEntry> lista) {
        Object[][] data = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            KnowledgeEntry k = lista.get(i);
            data[i][0] = k.getId();
            data[i][1] = k.getPergunta();
            data[i][2] = k.getResposta();
            data[i][3] = k.isEficaz();
        }
        return data;
    }

    public void adicionarPergunta(java.awt.Component parent) {
        String pergunta = JOptionPane.showInputDialog(parent, "Pergunta:");
        if (pergunta == null || pergunta.isBlank()) return;
        String resposta = JOptionPane.showInputDialog(parent, "Resposta:");
        if (resposta == null || resposta.isBlank()) return;
        dao.adicionar(pergunta, resposta);
        JOptionPane.showMessageDialog(parent, "Pergunta adicionada!");
    }

    public void editarPergunta(java.awt.Component parent, JTable tabela) {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(parent, "Selecione uma linha.");
            return;
        }
        int id = (int) tabela.getValueAt(row, 0);
        String curPergunta = (String) tabela.getValueAt(row, 1);
        String curResposta = (String) tabela.getValueAt(row, 2);

        String novaPergunta = JOptionPane.showInputDialog(parent, "Pergunta:", curPergunta);
        if (novaPergunta == null || novaPergunta.isBlank()) return;
        String novaResposta = JOptionPane.showInputDialog(parent, "Resposta:", curResposta);
        if (novaResposta == null || novaResposta.isBlank()) return;

        dao.atualizar(id, novaPergunta, novaResposta);
        JOptionPane.showMessageDialog(parent, "Atualizado!");
    }

    public void togglePergunta(JTable tabela) {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
            return;
        }
        int id = (int) tabela.getValueAt(row, 0);
        boolean atual = (boolean) tabela.getValueAt(row, 3);
        dao.definirEficaz(id, !atual);
        JOptionPane.showMessageDialog(null, (atual ? "Desativado" : "Ativado") + "! Atualize a lista.");
    }
}
