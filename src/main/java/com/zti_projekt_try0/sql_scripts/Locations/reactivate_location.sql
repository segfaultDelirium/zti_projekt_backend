CREATE OR REPLACE FUNCTION zti_projekt2.reactivate_location(p_location_id INTEGER)
    RETURNS zti_projekt2.modification_result AS $$
DECLARE
    currently_saved_is_active_status boolean;
    result zti_projekt2.modification_result;
BEGIN
    -- Check if the record with the given ID exists
    if not EXISTS (SELECT 1 FROM zti_projekt2.location WHERE zti_projekt2.location.location_id = p_location_id) THEN
        result.success := false;
        result.message := 'Record of id: ' || p_location_id || ' doesnt exist.';
        RETURN result;
    end if;


    SELECT is_active INTO currently_saved_is_active_status
    FROM zti_projekt2.location_is_active
    WHERE location_id = p_location_id
    ORDER BY timestamp DESC
    LIMIT 1;

    if (currently_saved_is_active_status is not null) then
        if(currently_saved_is_active_status) then
            result.success := true;
            result.message := 'Record of id: ' || p_location_id || ' current state is active. No modification has accured.';
            RETURN result;
        end if;
    end if;

    -- Deactivate the record
    insert into zti_projekt2.location_is_active(location_id, is_active, timestamp) values (p_location_id, true, now());

    result.success := true;
    result.message := 'Record of id: ' || p_location_id || ' has been activated.';
    RETURN result;

END;
$$ LANGUAGE plpgsql;


SELECT * from zti_projekt2.reactivate_location(5);