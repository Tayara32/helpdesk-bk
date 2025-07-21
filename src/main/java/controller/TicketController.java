package controller;

import dao.TicketDAO;
import model.Ticket;

import javax.swing.*;
import java.util.List;

public class TicketController {
    private final TicketDAO dao = new TicketDAO();

    public List<Ticket> listar() {
        return dao.listarTodos();
    }

    public Object[][] toTableData(List<Ticket> lista) {
        Object[][] data = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            Ticket t = lista.get(i);
            data[i][0] = t.getId();
            data[i][1] = t.getPergunta();
            data[i][2] = t.getDataAbertura();
            data[i][3] = t.getStatus();
        }
        return data;
    }

    public void atualizarStatus(JTable tabela, String novoStatus) {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
            return;
        }
        int id = (int) tabela.getValueAt(row, 0);
        dao.atualizarStatus(id, novoStatus);
        JOptionPane.showMessageDialog(null, "Status atualizado para: " + novoStatus + ". Atualize a lista.");
    }

    public void excluirTicket(JTable tabela) {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha.");
            return;
        }
        int id = (int) tabela.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(null, "Excluir ticket #" + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.excluir(id);
            JOptionPane.showMessageDialog(null, "Ticket excluído. Atualize a lista.");
        }
    }
}
