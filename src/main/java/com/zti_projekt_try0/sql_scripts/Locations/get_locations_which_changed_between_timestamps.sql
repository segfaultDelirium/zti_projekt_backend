
CREATE OR REPLACE FUNCTION zti_projekt2.get_locations_which_changed_between_timestamps(start_date timestamp, end_date timestamp)
    RETURNS TABLE(location_id INTEGER, is_active boolean, street_address varchar,
                  city varchar, zipcode varchar, state varchar, country_code varchar, country_code_id integer, is_country_code_active boolean,
                  activity_name varchar, activity_id integer, is_activity_active boolean, company_name varchar
                 ) AS $$
BEGIN
    RETURN QUERY

        SELECT ids.location_id, locations.is_active, locations.street_address, locations.city, locations.zipcode, locations.state, locations.country_code,
               locations.country_code_id, locations.is_country_code_active, locations.activity_name, locations.activity_id, locations.is_activity_active, locations.company_name
        FROM zti_projekt2.get_location_ids_which_changed_between_dates(start_date, end_date) as ids
                 join (select  * from zti_projekt2.get_locations_at_given_timestamp_wrapper(end_date)) as locations
                      on ids.location_id = locations.location_id;

END;
$$ LANGUAGE plpgsql;


select * from zti_projekt2.get_locations_which_changed_between_timestamps('2023-05-10 00:00:00'::timestamp, '2023-05-12 23:59:59'::timestamp)