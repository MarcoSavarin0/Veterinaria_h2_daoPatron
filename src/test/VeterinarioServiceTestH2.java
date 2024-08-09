package test;

import dao.impl.h2DB.VeterinarioDaoH2;
import model.Veterinario;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class VeterinarioServiceTestH2 {
    static Logger logger = Logger.getLogger(VeterinarioServiceTestH2.class);
    VeterinarioService veterinarioService = new VeterinarioService(new VeterinarioDaoH2());
    @BeforeAll
    static void cargarTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinicaVeterinaria;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear que se guarde el veterinario")
    void caso1(){
        Veterinario veterinario = new Veterinario("123","Juan","Lopes","Perro");
        Veterinario guardarVeteEnDB = veterinarioService.guardarVeterinario(veterinario);
        assertNotNull(guardarVeteEnDB.getId());
    }
    @Test
    @DisplayName("Testear que se busque x id")
    void caso2(){
        Integer id = 1;
        Veterinario verPacienteEnDB = veterinarioService.buscarVeterinario(id);
        assertEquals(id, verPacienteEnDB.getId());
    }
    @Test
    @DisplayName("Testear que se busque todos")
    void caso3(){
        Veterinario veterinario = new Veterinario("123","Juan","Lopes","Perro");
        veterinarioService.guardarVeterinario(veterinario);
        veterinarioService.buscarTodosLosVeterinarios();
    }

}