package com.moroz;

import com.moroz.persistence.ConnectionUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionTest {

    @Test
    public void appMustBeConnectedToDb() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
        connection.close();
        assertTrue(connection.isClosed());
    }
}
