package restauranteTest.negocio.cliente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;

public class updateClienteTest {
    private static TCliente cliente_1 = new TCliente(0, "John Doe", "12345678A", true);
    private static TCliente cliente_1_modificacion = new TCliente(0, "Nombre cambiado", "123456789Z", true);
    private static TCliente cliente_4 = new TCliente(0, "Obama", "999999999K", true);
    private static SACliente saCliente;

    @BeforeClass
    public static void setupUpdate() {
        saCliente = FactoriaAbstractaNegocio.getInstance().createSACliente();
        cliente_1.setId(saCliente.create(cliente_1));
        cliente_1_modificacion.setId(cliente_1.getId());
        cliente_4.setId(saCliente.create(cliente_4));
    }

    @Test
    public void updateCliente() {
        int res_update_cliente_1 = saCliente.update(cliente_1_modificacion); //Se cambian datos del cliente_1
        saCliente.delete(cliente_4.getId()); //Se da de baja al cliente_5
        int res_update_cliente_4 = saCliente.update(cliente_4);
        try {
            assertTrue(res_update_cliente_1 >= 0);
            assertTrue(res_update_cliente_4 >= 0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterClass
    public static void concludeUpdate() {
        saCliente.deleteFisico(cliente_1.getId());
        saCliente.deleteFisico(cliente_4.getId());
    }
}
