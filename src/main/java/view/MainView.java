package view;

import javax.swing.*;
import java.awt.*;
import controller.PerguntaController;
import controller.TicketController;

public class MainView {
    public void initUI() {
        JFrame frame = new JFrame("Backoffice - HelpDesk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton btnPerguntas = new JButton("Gerir Perguntas");
        JButton btnTickets = new JButton("Gerir Tickets");

        btnPerguntas.addActionListener(e -> new PerguntasView(new PerguntaController()).initUI());
        btnTickets.addActionListener(e -> new TicketsView(new TicketController()).initUI());

        // Painel com FlowLayout para deixar os botões lado a lado
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 100));

        // Tamanho fixo dos botões (opcional)
        Dimension buttonSize = new Dimension(140, 40);
        btnPerguntas.setPreferredSize(buttonSize);
        btnTickets.setPreferredSize(buttonSize);

        panel.add(btnPerguntas);
        panel.add(btnTickets);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

