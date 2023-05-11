CREATE OR REPLACE FUNCTION zti_projekt2.create_location(
    p_is_active boolean,
    p_street_address TEXT,
    p_city TEXT,
    p_zipcode TEXT,
    p_state TEXT,
    p_country_code_id INTEGER,
    p_activity_id INTEGER,
    p_company_name TEXT
)
    RETURNS zti_projekt2.creation_result AS $$
DECLARE
    new_location_id INTEGER;
    result zti_projekt2.creation_result;
BEGIN

    -- begin checking validity of the request.
    if (not EXISTS (SELECT 1 FROM zti_projekt2.country_code WHERE zti_projekt2.country_code.country_code_id = p_country_code_id) ) then
        result.success := false;
        result.message := 'Country code of id: ' || p_country_code_id || ' does not exist. No changes have been commited.';
        result.created_record_id := null;
        return result;
    end if;

    if (not EXISTS (SELECT 1 FROM zti_projekt2.activity WHERE zti_projekt2.activity.activity_id = p_activity_id) ) then
        result.success := false;
        result.message := 'Activity of id: ' || p_activity_id || ' does not exist. No changes have been commited.';
        result.created_record_id := null;
        return result;
    end if;
    -- end checking validity of the request.

    -- update the record

    SELECT nextval('zti_projekt2.location_location_id_seq'::regclass) INTO new_location_id;
    INSERT INTO zti_projekt2.location(location_id) VALUES (new_location_id);
    INSERT INTO zti_projekt2.location_is_active(location_id, timestamp, is_active) values (new_location_id, now(), p_is_active);
    INSERT INTO zti_projekt2.location_street_address(location_id, timestamp, street_address) values (new_location_id, now(), p_street_address);
    INSERT INTO zti_projekt2.location_city(location_id, timestamp, city) values (new_location_id, now(), p_city);
    INSERT INTO zti_projekt2.location_zipcode(location_id, timestamp, zipcode) values (new_location_id, now(), p_zipcode);
    INSERT INTO zti_projekt2.location_state(location_id, timestamp, state) values (new_location_id, now(), p_state);

    INSERT INTO zti_projekt2.location_country_code(location_id, timestamp, country_code_id) values (new_location_id, now(), p_country_code_id);
    INSERT INTO zti_projekt2.location_activity(location_id, timestamp, activity_id) values (new_location_id, now(), p_activity_id);

    INSERT INTO zti_projekt2.location_company_name(location_id, timestamp, company_name) values (new_location_id, now(), p_company_name);


    result.success := true;
    result.message := 'Record of id: ' || new_location_id || ' has been created.';
    result.created_record_id := new_location_id;
    RETURN result;
END;
$$ LANGUAGE plpgsql;



select * from zti_projekt2.add_location(
        true, -- is_active
        '123 Main St', -- street_address
        'Krakow', -- city
        '30-123', -- zipcode
        'MA', -- state
        4, -- country_code_id
        2, -- activity_id
        'ABC Company' -- company_name

    );