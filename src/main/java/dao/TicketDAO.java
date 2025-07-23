package dao;

import model.Ticket;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private Ticket map(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String pergunta = rs.getString("pergunta");
        Timestamp ts = rs.getTimestamp("data_abertura");
        LocalDateTime dt = ts != null ? ts.toLocalDateTime() : null;
        String status = rs.getString("status");
        String nomeUtilizador = rs.getString("nome_utilizador");

        Ticket t = new Ticket(id, pergunta, dt, status);
        t.setNomeUtilizador(nomeUtilizador);
        return t;
    }


    public List<Ticket> listarTodos() {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT u.nome, t.* FROM tickets t INNER JOIN utilizadores u ON t.id_utilizador = u.id ORDER BY id DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizarStatus(int id, String novoStatus) {
        String sql = "UPDATE tickets SET status = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, novoStatus);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
