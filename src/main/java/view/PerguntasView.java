package view;

import controller.PerguntaController;
import model.KnowledgeEntry;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PerguntasView {
    private PerguntaController controller;

    public PerguntasView(PerguntaController controller) {
        this.controller = controller;
    }

    public void initUI() {
        JFrame frame = new JFrame("Gestão de Perguntas");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colunas = {"ID", "Pergunta", "Resposta", "Eficaz"};
        List<KnowledgeEntry> lista = controller.listar();
        Object[][] dados = controller.toTableData(lista);

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        JButton btnAdd = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnToggle = new JButton("Ativar/Desativar");

        btnAdd.addActionListener(e -> controller.adicionarPergunta(frame));
        btnEditar.addActionListener(e -> controller.editarPergunta(frame, tabela));
        btnToggle.addActionListener(e -> controller.togglePergunta(tabela));

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnAdd);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnToggle);

        frame.setLayout(new BorderLayout());
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(panelBotoes, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
