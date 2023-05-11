package com.zti_projekt_try0.other;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificationResultMapper implements RowMapper<ModificationResult> {
    public ModificationResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        boolean success = rs.getBoolean("success");
        String message = rs.getString("message");
        return new ModificationResult(success, message);
    }
}
