package restauranteTest.negocio.cliente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;

public class createClienteTest {
    private static TCliente cliente_1 = new TCliente(0, "John Doe", "12345678A", true);
    private static TCliente cliente_2 = new TCliente(0, "John Doe", "12345678A", true);
    private static SACliente saCliente;

    @BeforeClass
    public static void setupCreate() {
        saCliente = FactoriaAbstractaNegocio.getInstance().createSACliente();
        cliente_1.setId(saCliente.create(cliente_1));
        cliente_2.setId(saCliente.create(cliente_2));
    }

    @Test
    public void creacionCliente() {
        int res_create_cliente1 = cliente_1.getId();
        int res_create_cliente2 = cliente_2.getId();
        try {
            assertTrue(res_create_cliente1 >= 0);
            assertTrue(res_create_cliente2 < 0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterClass
    public static void concludeCreate() {
        saCliente.deleteFisico(cliente_1.getId());
        saCliente.deleteFisico(cliente_2.getId());
    }
}
