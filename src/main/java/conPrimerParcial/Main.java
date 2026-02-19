package conPrimerParcial;

import Formularios.FMenuPrincipal;

/**
 *
 * @author miare
 */
public class Main {
    public static void main(String[] args) {
        //String sql = "SELECT NIF, NOMBRE ||\" \"|| substr(NIF,7} AS DESCRIPCION" + "FROM TRABAJADORES ORDER BY NOMBRE ASC";
        //Lo de arriba sirve para concatenar
        FMenuPrincipal mp = new FMenuPrincipal();
        mp.setVisible(true);
    }
}