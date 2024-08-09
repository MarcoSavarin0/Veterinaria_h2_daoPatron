package model;

public class Veterinario {
    private Integer id;
    private String NUMERO_LICENCIA;
    private String NOMBRE;
    private String APELLIDO;
    private String ESPECIALIDAD;

    public Veterinario(String NUMERO_LICENCIA, String NOMBRE, String APELLIDO, String ESPECIALIDAD) {
        this.NUMERO_LICENCIA = NUMERO_LICENCIA;
        this.NOMBRE = NOMBRE;
        this.APELLIDO = APELLIDO;
        this.ESPECIALIDAD = ESPECIALIDAD;
    }

    public Veterinario(Integer id, String NUMERO_LICENCIA, String NOMBRE, String APELLIDO, String ESPECIALIDAD) {
        this.id = id;
        this.NUMERO_LICENCIA = NUMERO_LICENCIA;
        this.NOMBRE = NOMBRE;
        this.APELLIDO = APELLIDO;
        this.ESPECIALIDAD = ESPECIALIDAD;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNUMERO_LICENCIA() {
        return NUMERO_LICENCIA;
    }

    public void setNUMERO_LICENCIA(String NUMERO_LICENCIA) {
        this.NUMERO_LICENCIA = NUMERO_LICENCIA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public void setAPELLIDO(String APELLIDO) {
        this.APELLIDO = APELLIDO;
    }

    public String getESPECIALIDAD() {
        return ESPECIALIDAD;
    }

    public void setESPECIALIDAD(String ESPECIALIDAD) {
        this.ESPECIALIDAD = ESPECIALIDAD;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + id +
                ", NUMERO_LICENCIA='" + NUMERO_LICENCIA + '\'' +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", APELLIDO='" + APELLIDO + '\'' +
                ", ESPECIALIDAD='" + ESPECIALIDAD + '\'' +
                '}';
    }
}
