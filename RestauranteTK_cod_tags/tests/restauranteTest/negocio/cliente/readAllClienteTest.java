package restauranteTest.negocio.cliente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;

public class readAllClienteTest {
    private static TCliente cliente_1 = new TCliente(0, "John Doe", "12345678A", true);
    private static SACliente saCliente;

    @BeforeClass
    public static void setupReadAll() {
        saCliente = FactoriaAbstractaNegocio.getInstance().createSACliente();
        cliente_1.setId(saCliente.create(cliente_1));
    }

    @Test
    public void readAllCliente() {
        Collection<TCliente> res = saCliente.readAll();
        try {
            assertTrue(res != null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterClass
    public static void concludeReadAll() {
        saCliente.deleteFisico(cliente_1.getId());
    }
}
