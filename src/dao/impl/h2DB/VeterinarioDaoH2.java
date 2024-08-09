package dao.impl.h2DB;

import dao.Idao;
import db.H2Connection;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDaoH2 implements Idao<Veterinario> {
    public static final Logger logger = Logger.getLogger(VeterinarioDaoH2.class);
    public static final String INSERT = "INSERT INTO VETERINARIO VALUES (DEFAULT,?,?,?,?)";
    public static final String SELECT_ID = "SELECT * FROM VETERINARIO WHERE ID = ?";
    public static final String SELECT_ALL = "SELECT * FROM VETERINARIO";

    @Override
    public Veterinario guardar(Veterinario veterinario) {
        Connection connection = null;
        Veterinario veterinarioNew = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, veterinario.getNUMERO_LICENCIA());
            preparedStatement.setString(2, veterinario.getNOMBRE());
            preparedStatement.setString(3, veterinario.getAPELLIDO());
            preparedStatement.setString(4, veterinario.getESPECIALIDAD());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                veterinarioNew = new Veterinario(id, veterinario.getNUMERO_LICENCIA(), veterinario.getNOMBRE(), veterinario.getAPELLIDO(), veterinario.getESPECIALIDAD());
            }
            logger.info("Veterinario nuevo! " + veterinarioNew);


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return veterinarioNew;
    }

    @Override
    public Veterinario buscarXID(Integer id) {
        Connection connection = null;
        Veterinario veterinatrioMatch = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer idDB = resultSet.getInt(1);
                String NUMERO_LICENCIA = resultSet.getNString(2);
                String NOMBRE = resultSet.getNString(3);
                String APELLIDO = resultSet.getNString(4);
                String ESPECIALIDAD = resultSet.getNString(5);
                veterinatrioMatch = new Veterinario(idDB, NUMERO_LICENCIA, NOMBRE, APELLIDO, ESPECIALIDAD);
            }
            if (veterinatrioMatch != null) {
                logger.info("Veterinario encontrado! " + veterinatrioMatch);
            } else logger.info("No se encontro nada :(");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return veterinatrioMatch;
    }


    @Override
    public List<Veterinario> buscarTodos() {
        List<Veterinario> todosVeterinarios = new ArrayList<>();
        Veterinario veterinarioParaAgregar = null;
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer idDB = resultSet.getInt(1);
                String NUMERO_LICENCIA = resultSet.getNString(2);
                String NOMBRE = resultSet.getNString(3);
                String APELLIDO = resultSet.getNString(4);
                String ESPECIALIDAD = resultSet.getNString(5);
                veterinarioParaAgregar = new Veterinario(idDB,NUMERO_LICENCIA,NOMBRE,APELLIDO,ESPECIALIDAD);
                todosVeterinarios.add(veterinarioParaAgregar);
            }
            if (!todosVeterinarios.isEmpty()){
                logger.info("Se encontraron estos veterinario " + todosVeterinarios);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return todosVeterinarios;
    }
}
