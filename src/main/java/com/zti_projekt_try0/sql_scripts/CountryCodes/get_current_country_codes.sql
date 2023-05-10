CREATE OR REPLACE FUNCTION zti_projekt2.get_current_country_codes()
    RETURNS TABLE(country_code_id INTEGER, is_active boolean, country_code varchar) AS $$
BEGIN
    RETURN QUERY
        SELECT zti_projekt2.country_code.country_code_id,

               (select zti_projekt2.country_code_is_active.is_active
                from zti_projekt2.country_code_is_active
                where zti_projekt2.country_code.country_code_id = zti_projekt2.country_code_is_active.country_code_id
                order by timestamp desc
                limit 1
               ),
               (select zti_projekt2.country_code_code.country_code
                from zti_projekt2.country_code_code
                where zti_projekt2.country_code.country_code_id = zti_projekt2.country_code_code.country_code_id
                order by timestamp desc
                limit 1
               )

        FROM zti_projekt2.country_code;

END;
$$ LANGUAGE plpgsql;

select * from zti_projekt2.get_current_country_codes();
