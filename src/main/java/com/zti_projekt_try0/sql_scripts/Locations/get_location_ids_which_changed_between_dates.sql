CREATE OR REPLACE FUNCTION zti_projekt2.get_location_ids_which_changed_between_dates(start_date timestamp, end_date timestamp)
    RETURNS TABLE(location_id INTEGER) AS $$
BEGIN
    RETURN QUERY

        SELECT zti_projekt2.location.location_id
        FROM zti_projekt2.location
        where (
            select count(*) > 0
            from zti_projekt2.location_is_active
            where zti_projekt2.location.location_id = zti_projekt2.location_is_active.location_id
              and zti_projekt2.location_is_active.timestamp > start_date
              and zti_projekt2.location_is_active.timestamp < end_date
            group by zti_projekt2.location_is_active.location_id
        )
           or (
            select count(*) > 0
            from zti_projekt2.location_street_address
            where zti_projekt2.location.location_id = zti_projekt2.location_street_address.location_id
              and zti_projekt2.location_street_address.timestamp > start_date
              and zti_projekt2.location_street_address.timestamp < end_date
            group by zti_projekt2.location_street_address.location_id
        )
           or (
            select count(*) > 0
            from zti_projekt2.location_city
            where zti_projekt2.location.location_id = zti_projekt2.location_city.location_id
              and zti_projekt2.location_city.timestamp > start_date
              and zti_projekt2.location_city.timestamp < end_date
            group by zti_projekt2.location_city.location_id
        )

           or (
            select count(*) > 0
            from zti_projekt2.location_zipcode
            where zti_projekt2.location.location_id = zti_projekt2.location_zipcode.location_id
              and zti_projekt2.location_zipcode.timestamp > start_date
              and zti_projekt2.location_zipcode.timestamp < end_date
            group by zti_projekt2.location_zipcode.location_id
        )

           or (
            select count(*) > 0
            from zti_projekt2.location_state
            where zti_projekt2.location.location_id = zti_projekt2.location_state.location_id
              and zti_projekt2.location_state.timestamp > start_date
              and zti_projekt2.location_state.timestamp < end_date
            group by zti_projekt2.location_state.location_id
        )

           or (
            select count(*) > 0
            from zti_projekt2.location_country_code
            where zti_projekt2.location.location_id = zti_projekt2.location_country_code.location_id
              and zti_projekt2.location_country_code.timestamp > start_date
              and zti_projekt2.location_country_code.timestamp < end_date
            group by zti_projekt2.location_country_code.location_id
        )

           or (
            select count(*) > 0
            from zti_projekt2.location_activity
            where zti_projekt2.location.location_id = zti_projekt2.location_activity.location_id
              and zti_projekt2.location_activity.timestamp > start_date
              and zti_projekt2.location_activity.timestamp < end_date
            group by zti_projekt2.location_activity.location_id
        )

    ;


END;
$$ LANGUAGE plpgsql;


SELECT * FROM zti_projekt2.get_location_ids_which_changed_between_dates('2023-05-01 00:00:00'::timestamp, '2023-05-10 23:59:59'::timestamp);