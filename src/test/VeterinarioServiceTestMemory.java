package test;
import dao.impl.Memory.VeterinarioDaoMemory;
import dao.impl.h2DB.VeterinarioDaoH2;
import model.Veterinario;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import static org.junit.jupiter.api.Assertions.*;

class VeterinarioServiceTestMemory {
    static Logger logger = Logger.getLogger(VeterinarioServiceTestMemory.class);
    VeterinarioService veterinarioService = new VeterinarioService(new VeterinarioDaoMemory());
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
        Veterinario veterinario = new Veterinario("123","Juan","Lopes","Perro");
        veterinarioService.guardarVeterinario(veterinario);
        Veterinario veterinario2 = new Veterinario("12341","Lionel","Lopes","Gatos");
        veterinarioService.guardarVeterinario(veterinario2);
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