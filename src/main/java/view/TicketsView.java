package view;

import controller.TicketController;
import model.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TicketsView {
    private TicketController controller;

    public TicketsView(TicketController controller) {
        this.controller = controller;
    }

    public void initUI() {
        JFrame frame = new JFrame("Gestão de Tickets");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colunas = {"ID", "Pergunta", "Data", "Status", "Utilizador"};
        List<Ticket> lista = controller.listar();
        Object[][] dados = controller.toTableData(lista);

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        JButton btnAndamento = new JButton("Marcar Em Andamento");
        JButton btnResolvido = new JButton("Marcar Resolvido");

        btnAndamento.addActionListener(e -> controller.atualizarStatus(tabela, "Em Andamento"));
        btnResolvido.addActionListener(e -> controller.atualizarStatus(tabela, "Resolvido"));

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnAndamento);
        panelBotoes.add(btnResolvido);

        frame.setLayout(new BorderLayout());
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(panelBotoes, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
