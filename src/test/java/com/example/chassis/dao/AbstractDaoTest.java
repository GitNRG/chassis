package com.example.chassis.dao;

import com.github.database.rider.core.DBUnitRule;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.stream.Stream;

public class AbstractDaoTest<T> {
    private static long ID = 1;

    @Autowired
    protected T dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(() -> jdbcTemplate.getDataSource().getConnection());

    @Autowired
    private TestEntityManager em;

    protected long addRecordToDatabase(String table, Map<String, Object> fields) {
        long id = ID++;
        return addRecordToDatabaseWithId(table, id, fields);
    }

    protected long addRecordToDatabaseWithId(String table, long id,  Map<String, Object> fields) {
        Object[] params = Stream.concat(Stream.of(id), fields.values().stream()).toArray();
        jdbcTemplate.update("INSERT INTO " + table +
                " (id, " + String.join(", ", fields.keySet()) +
                ") VALUES (?" + StringUtils.repeat(", ?", fields.size()) + ")", params);
        return id;
    }

    protected long updateRecordToDatabaseWithId(String table, long id,  Map<String, Object> fields) {
        Object[] params = Stream.concat(Stream.of(id), fields.values().stream()).toArray();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Object> entry : fields.entrySet()) {
            if(sb.length() > 0) {
                sb.append(",");
            }
            sb.append(entry.getKey()).append("='").append(entry.getValue()).append("'");
        }
        jdbcTemplate.update("UPDATE " + table + " SET " + sb.toString() + " WHERE id=" + id + ";");
        return id;
    }
}
