package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UrlRepository extends BaseRepository {
    public static void save(Url url) throws SQLException {
        var sql = "INSERT INTO urls(name) VALUES(?)";
        var datetime = Timestamp.valueOf(LocalDateTime.now());
        try (var conn = dataSource.getConnection();
            var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getInt(1));
                url.setCreatedAt(datetime);
            } else {
                throw new SQLException("Ошибка записи в базу банных");
            }
        }
    }
    public static Optional<Url> findByName(String name) throws SQLException {
        var sql = "SELECT * FROM urls WHERE name = ?";
        var datetime = Timestamp.valueOf(LocalDateTime.now());
        try (var conn = dataSource.getConnection();
            var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            var result = preparedStatement.executeQuery();
            if (result.next()) {
                Url url = new Url();
                url.setId(result.getInt(1));
                url.setName(name);
                url.setCreatedAt(datetime);
                return Optional.of(url);
            } else {
                return Optional.empty();
            }
        }
    }

    public static Optional<Url> findById(int id) throws SQLException {
        var sql = "SELECT * FROM urls WHERE id = ?";
        var datetime = Timestamp.valueOf(LocalDateTime.now());
        try (var conn = dataSource.getConnection();
            var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            var result = preparedStatement.executeQuery();
            if (result.next()) {
                Url url = new Url();
                url.setId(result.getInt(1));
                url.setName(result.getString(2));
                url.setCreatedAt(datetime);
                return Optional.of(url);
            }
            return Optional.empty();
        }
    }

    public static List<Url> getEntities() throws SQLException {
        var sql = "SELECT * FROM urls";
        var datetime = Timestamp.valueOf(LocalDateTime.now());

        try (var conn = dataSource.getConnection();
            var preparedStatement = conn.prepareStatement(sql)) {
            var result = preparedStatement.executeQuery();
            List<Url> urls = new ArrayList<>();
            while (result.next()) {
                Url url = new Url();
                url.setId(result.getInt(1));
                url.setName(result.getString(2));
                url.setCreatedAt(datetime);
                urls.add(url);
            }
            return urls;
        }
    }
}
