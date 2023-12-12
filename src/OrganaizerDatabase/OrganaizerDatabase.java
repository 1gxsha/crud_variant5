package OrganaizerDatabase;

import DatabaseConnector.DatabaseConnector;
import Domain.Organaizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganaizerDatabase {

    private Connection connection;
    private List<Organaizer> events;

    public OrganaizerDatabase() {
        try {
            connection = DatabaseConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static final String SELECT_ALL_ORGANAIZER = "SELECT * FROM organaizer.public.events";
    private static final String INSERT_ORGANAIZER = "INSERT INTO organaizer.public.events (type, date, time, description) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ORGANAIZER = "UPDATE organaizer.public.events SET type = ?, date = ?, time = ?, description = ? WHERE id = ?";
    private static final String DELETE_ORGANAIZER = "DELETE FROM organaizer.public.events WHERE id = ?";

    private Connection getConnection() throws SQLException {
        return DatabaseConnector.getConnection();
    }
    public List<Organaizer> getAllOrganaizer() {
        List<Organaizer> Organaizer = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORGANAIZER);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String description = resultSet.getString("description");

                Organaizer events = new Organaizer(id, type, date, time, description);
                Organaizer.add(events);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Organaizer;
    }

    public void addOrganaizerEvents(Organaizer organaizer) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ORGANAIZER)) {

            statement.setString(1, organaizer.getType());
            statement.setString(2, organaizer.getDate());
            statement.setString(3, organaizer.getTime());
            statement.setString(4, organaizer.getDescription());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrganaizer(Organaizer organaizer) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORGANAIZER)) {

            statement.setString(1, organaizer.getType());
            statement.setString(2, organaizer.getDate());
            statement.setString(3, organaizer.getTime());
            statement.setString(4, organaizer.getDescription());
            statement.setInt(5, organaizer.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrganaizer(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ORGANAIZER)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Organaizer getOrganaizer(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM organaizer.public.events WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Organaizer organaizer = new Organaizer();
                    organaizer.setId(resultSet.getInt("id"));
                    organaizer.setOrganaizerType(resultSet.getString("type"));
                    organaizer.setDate(resultSet.getString("date"));
                    organaizer.setTime(resultSet.getString("time"));
                    organaizer.setDescription(resultSet.getString("description"));
                    return organaizer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
