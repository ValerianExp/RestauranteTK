package restauranteTest.negocio.cliente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.TCliente;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;

public class readClienteTest {
    private static TCliente cliente_1 = new TCliente(0, "John Doe", "12345678A", true);
    private static SACliente saCliente;

    @BeforeClass
    public static void setupRead() {
        saCliente = FactoriaAbstractaNegocio.getInstance().createSACliente();
        cliente_1.setId(saCliente.create(cliente_1));
    }

    @Test
    public void readCliente() {
        TCliente res = saCliente.read(cliente_1.getId());
        saCliente.delete(res.getId()); //Se da de baja
        TCliente res_inactivo = saCliente.read(res.getId());
        TCliente res_null = saCliente.read(-10); //ID incorrecto
        try {
            assertTrue(res != null);
            assertTrue(res_inactivo == null);
            assertTrue(res_null == null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterClass
    public static void concludeBaja() {
        saCliente.deleteFisico(cliente_1.getId());
    }
}
