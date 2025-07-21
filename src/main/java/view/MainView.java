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

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(btnPerguntas);
        panel.add(btnTickets);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
