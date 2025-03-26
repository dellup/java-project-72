package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class UrlCheckRepository extends BaseRepository {
    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description) VALUES(?, ?, ?, ?, ?)";
        var datetime = Timestamp.valueOf(LocalDateTime.now());

        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, urlCheck.getUrlId());
            stmt.setInt(2, urlCheck.getStatusCode());
            stmt.setString(3, urlCheck.getH1());
            stmt.setString(4, urlCheck.getTitle());
            stmt.setString(5, urlCheck.getDescription());
            stmt.executeUpdate();

            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getInt(1));
                urlCheck.setCreatedAt(datetime);
            } else {
                throw new SQLException("Ошибка записи в базу данных");
            }
        }
    }

    public static List<UrlCheck> getEntities() {
        var sql = "SELECT * FROM url_checks";
        var datetime = Timestamp.valueOf(LocalDateTime.now());
        var resultList = new ArrayList<UrlCheck>();

        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)) {
            var result = stmt.executeQuery();
            while (result.next()) {
                UrlCheck urlCheck = createCheckObject(result);
                resultList.add(urlCheck);
            }
            return resultList;
        } catch (SQLException e) {
            return resultList;
        }
    }

    public static List<UrlCheck> getByUrlId(int id) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE url_id = ?";
        var resultList = new ArrayList<UrlCheck>();

        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var result = stmt.executeQuery();
            while (result.next()) {
                UrlCheck urlCheck = createCheckObject(result);
                resultList.add(urlCheck);
            }
            return resultList;
        }
    }

    private static UrlCheck createCheckObject(ResultSet result) throws SQLException {
        var urlCheck = new UrlCheck();
        urlCheck.setUrlId(result.getInt("url_id"));
        urlCheck.setStatusCode(result.getInt("status_code"));
        urlCheck.setH1(result.getString("h1"));
        urlCheck.setTitle(result.getString("title"));
        urlCheck.setDescription(result.getString("description"));
        urlCheck.setId(result.getInt("id"));
        urlCheck.setCreatedAt(result.getTimestamp("created_at"));
        return urlCheck;
    }

    public static Map<Integer, UrlCheck> findLatestChecks() throws SQLException {
        String sql = "SELECT DISTINCT ON (url_id) * from url_checks ORDER BY url_id DESC, id DESC";
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(sql);
             var resultSet = preparedStatement.executeQuery()) {

            Map<Integer, UrlCheck> result = new HashMap<>();
            while (resultSet.next()) {
                int urlId = resultSet.getInt("url_id");
                int statusCode = resultSet.getInt("status_code");
                String title = resultSet.getString("title");
                String h1 = resultSet.getString("h1");
                String description = resultSet.getString("description");
                Timestamp createdAt = resultSet.getObject("created_at", Timestamp.class);

                UrlCheck urlCheck = new UrlCheck();
                urlCheck.setUrlId(urlId);
                urlCheck.setH1(h1);
                urlCheck.setStatusCode(statusCode);
                urlCheck.setTitle(title);
                urlCheck.setDescription(description);
                urlCheck.setCreatedAt(createdAt);
                result.put(urlId, urlCheck);
            }
            return result;
        }
    }
}
