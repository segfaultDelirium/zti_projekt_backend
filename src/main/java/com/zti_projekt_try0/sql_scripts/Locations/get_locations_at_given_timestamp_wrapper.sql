

CREATE OR REPLACE FUNCTION zti_projekt2.get_locations_at_given_timestamp_wrapper(upto_date timestamp)
    RETURNS TABLE(location_id INTEGER, is_active boolean, street_address varchar,
                  city varchar, zipcode varchar, state varchar, country_code varchar, country_code_id integer, is_country_code_active boolean, activity_name varchar, activity_id integer, is_activity_active boolean, company_name varchar
                 ) AS $$
BEGIN
    RETURN QUERY

        select * from zti_projekt2.get_locations_at_given_timestamp(upto_date) as locations
        where locations.is_active is not null;

END;
$$ LANGUAGE plpgsql;


select  * from zti_projekt2.get_locations_at_given_timestamp_wrapper('2023-05-10 00:00:00')