
CREATE OR REPLACE FUNCTION zti_projekt2.deactivate_location(p_location_id INTEGER)
    RETURNS deactivate_record_result AS $$
DECLARE
    result deactivate_record_result;
BEGIN
    -- Check if the record with the given ID exists
    if not EXISTS (SELECT 1 FROM zti_projekt2.location WHERE zti_projekt2.location.location_id = p_location_id) THEN
        result.success := false;
        result.message := 'Record of id: ' || p_location_id || ' doesnt exist.';
        RETURN result;
    end if;

    -- Check if the record is already inactive (will return null which is interpreted as false in case the record of location_id doesn't exist in location_is_active table)
    IF NOT(
        SELECT is_active FROM zti_projekt2.location_is_active
        WHERE location_id = p_location_id
        order by timestamp desc
        limit 1
    ) then
        result.success := true;
        result.message := 'Record of id: ' || p_location_id || ' current state is deactivated. No modification has accured.';
        RETURN result;
    end if;

    -- Deactivate the record
    insert into zti_projekt2.location_is_active(location_id, is_active, timestamp) values (p_location_id, false, now());

    result.success := true;
    result.message := 'Record of id: ' || p_location_id || ' has been deactivated.';
    RETURN result;

END;
$$ LANGUAGE plpgsql;


SELECT * from zti_projekt2.deactivate_location(5);