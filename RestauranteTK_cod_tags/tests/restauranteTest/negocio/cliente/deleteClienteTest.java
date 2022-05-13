package restauranteTest.negocio.cliente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;

public class deleteClienteTest {
    private static TCliente cliente_1 = new TCliente(0, "John Doe", "12345678A", true);
    private static TCliente cliente_3 = new TCliente(0, "Jane Doe", "99999999B", true);
    private static SACliente saCliente;

    @BeforeClass
    public static void setupDelete() {
        saCliente = FactoriaAbstractaNegocio.getInstance().createSACliente();
        cliente_1.setId(saCliente.create(cliente_1));
        cliente_3.setId(saCliente.create(cliente_3));
    }

    @Test
    public void bajaCliente() {
        int res_baja_cliente_1 = saCliente.delete(cliente_1.getId());
        int res_baja_cliente_3 = saCliente.delete(cliente_3.getId());
        int res_baja_cliente_3_aux = saCliente.delete(cliente_3.getId());
        try {
            assertTrue(res_baja_cliente_1 >= 0); //Baja normal
            assertTrue(res_baja_cliente_3 >= 0); //Baja normal
            assertTrue(res_baja_cliente_3_aux < 0); //No se puede dar de baja de nuevo a un cliente que ya esta dado de baja

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterClass
    public static void concludeBaja() {
        saCliente.deleteFisico(cliente_1.getId());
        saCliente.deleteFisico(cliente_3.getId());
    }
}
