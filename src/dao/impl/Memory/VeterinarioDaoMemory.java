package dao.impl.Memory;

import dao.Idao;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class VeterinarioDaoMemory  implements Idao<Veterinario> {

    public static final Logger logger = Logger.getLogger(VeterinarioDaoMemory.class);
    private List<Veterinario> veterinarios = new ArrayList<>();
    @Override
    public Veterinario guardar(Veterinario veterinario) {
        veterinario.setId(veterinarios.size()+1);
        veterinarios.add(veterinario);
        logger.info("paciente guardado "+ veterinario);
        return veterinario;
    }

    @Override
    public Veterinario buscarXID(Integer id) {
        Veterinario veterinarioMatch = null;
        for (Veterinario veterinario : veterinarios) {
            if (veterinario.getId().equals(id)) {
                veterinarioMatch = veterinario;
                break;
            }
        }
        if (veterinarioMatch != null){
            logger.info("Veterinario encontrado "  + veterinarioMatch);
        }else logger.info("err");
        return veterinarioMatch;
    }

    @Override
    public List<Veterinario> buscarTodos() {
        logger.info(veterinarios);
        return veterinarios;
    }
}
