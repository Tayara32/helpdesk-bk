package dao;

import model.KnowledgeEntry;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class KnowledgeBaseDAO {

    private KnowledgeEntry map(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String pergunta = rs.getString("pergunta");
        String resposta = rs.getString("resposta");
        boolean eficaz = rs.getBoolean("eficaz");
        Timestamp ts = rs.getTimestamp("ultima_atualizacao");
        LocalDateTime dt = ts != null ? ts.toLocalDateTime() : null;
        return new KnowledgeEntry(id, pergunta, resposta, eficaz, dt);
    }

    public List<KnowledgeEntry> listarTodos() {
        List<KnowledgeEntry> lista = new ArrayList<>();
        String sql = "SELECT * FROM knowledge_base ORDER BY id";
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

    public KnowledgeEntry buscarPorId(int id) {
        String sql = "SELECT * FROM knowledge_base WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void adicionar(String pergunta, String resposta) {
        String sql = "INSERT INTO knowledge_base (pergunta, resposta, eficaz) VALUES (?, ?, 1)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, pergunta);
            ps.setString(2, resposta);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(int id, String pergunta, String resposta) {
        String sql = "UPDATE knowledge_base SET pergunta = ?, resposta = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, pergunta);
            ps.setString(2, resposta);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void definirEficaz(int id, boolean eficaz) {
        String sql = "UPDATE knowledge_base SET eficaz = ? WHERE id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setBoolean(1, eficaz);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

