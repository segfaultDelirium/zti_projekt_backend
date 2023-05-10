CREATE OR REPLACE FUNCTION zti_projekt2.update_location(
    p_location_id INTEGER,
    p_new_street_address TEXT DEFAULT NULL,
    p_new_city TEXT DEFAULT NULL,
    p_new_zipcode TEXT DEFAULT NULL,
    p_new_state TEXT DEFAULT NULL,
    p_new_country_code_id INTEGER DEFAULT NULL,
    p_new_activity_id INTEGER DEFAULT NULL,
    p_new_company_name TEXT DEFAULT NULL
)
    RETURNS zti_projekt2.modification_result AS $$
DECLARE
    result zti_projekt2.modification_result;
BEGIN

    -- begin checking validity of the request.
    -- Check if the record with the given ID exists
    if not EXISTS (SELECT 1 FROM zti_projekt2.location WHERE zti_projekt2.location.location_id = p_location_id) THEN
        result.success := false;
        result.message := 'Record of id: ' || p_location_id || ' doesnt exist.';
        RETURN result;
    end if;


    if (p_new_country_code_id IS NOT NULL and
        not EXISTS (SELECT 1 FROM zti_projekt2.country_code WHERE zti_projekt2.country_code.country_code_id = p_new_country_code_id)
        ) then
        result.success := false;
        result.message := 'Country code of id: ' || p_new_country_code_id || ' does not exist. No changes have been commited.';
        return result;
    end if;

    if (p_new_activity_id IS NOT NULL and
        not EXISTS (SELECT 1 FROM zti_projekt2.activity WHERE zti_projekt2.activity.activity_id = p_new_activity_id)
        ) then
        result.success := false;
        result.message := 'Activity of id: ' || p_new_activity_id || ' does not exist. No changes have been commited.';
        return result;
    end if;
    -- end checking validity of the request.

    -- update the record

    if (p_new_country_code_id IS NOT NULL) then
        insert into zti_projekt2.location_country_code(location_id, timestamp, country_code_Id) values (p_location_id, now(), p_new_country_code_id);
    end if;

    if (p_new_activity_id IS NOT NULL) then
        insert into zti_projekt2.location_activity(location_id, timestamp, activity_id) values (p_location_id, now(), p_new_activity_id);
    end if;

    if (p_new_street_address is not null) then
        insert into zti_projekt2.location_street_address(location_id, timestamp, street_address) values (p_location_id, now(), p_new_street_address);
    end if;

    if (p_new_city is not null) then
        insert into zti_projekt2.location_city(location_id, timestamp, city) values (p_location_id, now(), p_new_city);
    end if;

    if (p_new_zipcode is not null) then
        insert into zti_projekt2.location_zipcode(location_id, timestamp, zipcode) values (p_location_id, now(), p_new_zipcode);
    end if;

    if (p_new_state is not null) then
        insert into zti_projekt2.location_state(location_id, timestamp, state) values (p_location_id, now(), p_new_state);
    end if;

    if (p_new_company_name is not null) then
        insert into zti_projekt2.location_company_name(location_id, timestamp, company_name) values (p_location_id, now(), p_new_company_name);
    end if;


    result.success := true;
    result.message := 'Record of id: ' || p_location_id || ' has been updated.';
    RETURN result;
END;
$$ LANGUAGE plpgsql;



SELECT * from zti_projekt2.update_location(
        p_location_id := 5,
        p_new_country_code_id := 4
    );