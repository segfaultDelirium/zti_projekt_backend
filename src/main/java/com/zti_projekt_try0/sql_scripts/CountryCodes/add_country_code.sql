CREATE OR REPLACE PROCEDURE zti_projekt2.add_country_code(
    p_is_active BOOLEAN,
    p_code varchar
) AS $$
DECLARE
    new_country_code_id INTEGER;
    new_timestamp timestamp;
BEGIN

    SELECT nextval('zti_projekt2.country_code_country_code_id_seq_2_1'::regclass) INTO new_country_code_id;
    select now() into new_timestamp;
    INSERT INTO zti_projekt2.country_code(country_code_id) VALUES (new_country_code_id);
    INSERT INTO zti_projekt2.country_code_is_active(country_code_id, timestamp, is_active) values (new_country_code_id, new_timestamp, p_is_active);
    INSERT INTO zti_projekt2.country_code_code(country_code_id, timestamp, country_code) values (new_country_code_id, new_timestamp, p_code);

END;
$$ LANGUAGE plpgsql;

call zti_projekt2.add_country_code(true, 'AR')
