package service;

import dao.Idao;
import model.Veterinario;

import java.util.List;

public class VeterinarioService {
    private Idao<Veterinario> veterinarioIdao;
    public VeterinarioService(Idao<Veterinario> veterinarioIdao){
        this.veterinarioIdao = veterinarioIdao;
    }
    public Veterinario guardarVeterinario(Veterinario veterinario) {
        return veterinarioIdao.guardar(veterinario);
    }
    public Veterinario buscarVeterinario(Integer id) {
        return veterinarioIdao.buscarXID(id);
    }
    public List<Veterinario> buscarTodosLosVeterinarios(){
        return veterinarioIdao.buscarTodos();
    }
}
