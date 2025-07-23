
package controller;

// importa os DAOs e modelos necessários

import dao.PerfilDAO;
import dao.UtilizadorDAO;
import model.Perfil;
import model.Utilizador;
import service.PasswordEncryptionService;


/**
 * Controlador responsável pela autenticação de utilizadores no sistema
 */
public class LoginController {

    /**
     * Método para autenticar o utilizador no login
     *
     * @param email email do utilizador (campo utilizador na BD)
     * @param palavraChave password em texto simples
     * @return 1 se for válido e com perfil Administrador
     *         0 se credenciais inválidas
     *        -1 se perfil não autorizado
     */
    public int autenticar(String email, String palavraChave) {
        UtilizadorDAO dao = new UtilizadorDAO();
        Utilizador u = dao.obterPorEmail(email);

        if (u != null && PasswordEncryptionService.verifyPassword(palavraChave, u.getSenha())) {
            int perfilId = u.getPerfilId();

            if (perfilId == 1) {
                return 1; // Administrador
            } else if (perfilId == 3) {
                return 3; // Backoffice
            } else {
                return -1; // perfil não autorizado
            }
        }

        return 0; // credenciais inválidas
    }




}