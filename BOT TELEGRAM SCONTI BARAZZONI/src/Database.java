import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/sconti nike";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(String link, String prezzo, String prezzos) {
        String query = "INSERT INTO prodotti (link, prezzo, prezzos) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, link);
            statement.setString(2, prezzo);
            statement.setString(3, prezzos);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM prodotti";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String link = resultSet.getString("link");
                String prezzo = resultSet.getString("prezzo");
                String prezzos = resultSet.getString("prezzos");
                productList.add(new Product(link, prezzo, prezzos));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
