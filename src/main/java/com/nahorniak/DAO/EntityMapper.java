package com.nahorniak.DAO;

import java.sql.ResultSet;

/**
 * Entity mapper interface
 *
 * @author Oleh Nahorniak.
 */
public interface EntityMapper<T> {
    T map(ResultSet rs);
}
