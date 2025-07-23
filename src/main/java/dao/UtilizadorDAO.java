package dao;

import model.Utilizador;
import util.DBConnection;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações sobre a tabela utilizador
 */
public class UtilizadorDAO {

    /**
     * Lista todos os utilizadores
     *
     * @return lista de utilizadores
     */
    public List<Utilizador> listarTodos() {
        List<Utilizador> lista = new ArrayList<>();
        try (Connection c = DBConnection.getConnection()) {
            String sql = "SELECT * FROM utilizadores";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilizador u = new Utilizador();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setPerfilId(rs.getInt("perfil_id"));
                lista.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Insere um novo utilizador
     *
     * @param u utilizador a inserir
     * @return true se inseriu com sucesso
     */
    public boolean inserir(Utilizador u) {
        try (Connection c = DBConnection.getConnection()) {
            String sql = "INSERT INTO utilizadores (nome, email, senha, perfil_id) VALUES (?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenha());
            ps.setInt(4, u.getPerfilId());
            ps.executeUpdate();
            return true;
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null,
                    "O email já existe na base de dados.\nPor favor escolha outro.",
                    "Erro ao criar utilizador",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verifica se um email já existe
     *
     * @param email email a verificar
     * @return true se existir, false caso contrário
     */
    public boolean existeEmail(String email) {
        try (Connection c = DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM utilizadores WHERE email=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Apaga um utilizador pelo ID
     *
     * @param id ID do utilizador a apagar
     */
    public void apagar(int id) {
        try (Connection c = DBConnection.getConnection()) {
            String sql = "DELETE FROM utilizadores WHERE id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza os dados de um utilizador (exceto password)
     *
     * @param u utilizador com dados atualizados
     */
    public void atualizar(Utilizador u) {
        try (Connection c = DBConnection.getConnection()) {
            String sql = "UPDATE utilizadores SET nome=?, email=?, perfil_id=? WHERE id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setInt(3, u.getPerfilId());
            ps.setInt(4, u.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém um utilizador pelo email
     *
     * @param email email do utilizador
     * @return objeto Utilizador ou null se não existir
     */
    public Utilizador obterPorEmail(String email) {
        Utilizador u = null;
        try (Connection c = DBConnection.getConnection()) {
            String sql = "SELECT * FROM utilizadores WHERE email=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Utilizador();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setPerfilId(rs.getInt("perfil_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    /**
     * Obtém um utilizador pelo ID
     *
     * @param id ID do utilizador
     * @return objeto Utilizador ou null se não existir
     */
    public Utilizador obterPorId(int id) {
        Utilizador u = null;
        try (Connection c = DBConnection.getConnection()) {
            String sql = "SELECT * FROM utilizadores WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Utilizador();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setPerfilId(rs.getInt("perfil_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    /**
     * Atualiza a password de um utilizador
     *
     * @param id ID do utilizador
     * @param novaPasswordHash password cifrada
     */
    public void atualizarPassword(int id, String novaPasswordHash) {
        try (Connection c = DBConnection.getConnection()) {
            String sql = "UPDATE utilizadores SET senha=? WHERE id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, novaPasswordHash);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Conta quantos utilizadores têm o perfil Administrador
     *
     * @return número de administradores
     */
    public int contarAdministradores() {
        int count = 0;
        try (Connection c = DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM utilizadores u INNER JOIN perfil p ON u.perfil_id = p.id WHERE p.nome = 'Administrador'";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}