package com.zti_projekt_try0.CountryCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

@Repository
public class CountryCodeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CountryCode> getCurrentCountryCodes() {
        String sql = "SELECT * FROM zti_projekt2.get_current_country_codes()";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int countryCodeId = rs.getInt("country_code_id");
            boolean isActive = rs.getBoolean("is_active");
            String countryCode = rs.getString("country_code");
            return new CountryCode(countryCodeId, isActive, countryCode);
        });
    }


    public void addCountryCode(boolean isActive, String code) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Object[] params = { timestamp, isActive, code };
        int[] types = { Types.TIMESTAMP, Types.BOOLEAN, Types.VARCHAR };
        jdbcTemplate.update("CALL zti_projekt2.insert_country_code(?, ?, ?)", params, types);
    }

}
