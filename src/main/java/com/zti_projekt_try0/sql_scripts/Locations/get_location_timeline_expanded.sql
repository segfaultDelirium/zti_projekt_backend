CREATE OR REPLACE FUNCTION zti_projekt2.get_location_timeline_expanded(p_location_id INTEGER)
    RETURNS TABLE ("timestamp" TIMESTAMP, is_active boolean, street_address text, city text, zipcode text, "state" text,
                   company_name text, country_code_id text, country_code_code text, activity_id text, activity_name text) AS $$
BEGIN
    RETURN QUERY
        select zti_projekt2.location_is_active.timestamp, zti_projekt2.location_is_active.is_active, null, null, null, null, null, null, null, null, null
        from zti_projekt2.location_is_active
        where zti_projekt2.location_is_active.location_id = p_location_id
        union

        select zti_projekt2.location_street_address.timestamp, null, zti_projekt2.location_street_address.street_address::text, null, null, null, null, null, null, null, null
        from zti_projekt2.location_street_address
        where zti_projekt2.location_street_address.location_id = p_location_id
        union

        select zti_projekt2.location_city.timestamp, null, null, zti_projekt2.location_city.city::text, null, null, null, null, null, null, null
        from zti_projekt2.location_city
        where zti_projekt2.location_city.location_id = p_location_id
        union

        select zti_projekt2.location_zipcode.timestamp, null, null, null, zti_projekt2.location_zipcode.zipcode::text, null, null, null, null, null, null
        from zti_projekt2.location_zipcode
        where zti_projekt2.location_zipcode.location_id = p_location_id
        union

        select zti_projekt2.location_state.timestamp, null, null, null, null, zti_projekt2.location_state.state::text, null, null, null, null, null
        from zti_projekt2.location_state
        where zti_projekt2.location_state.location_id = p_location_id
        union

        select zti_projekt2.location_company_name.timestamp, null, null, null, null, null, zti_projekt2.location_company_name.company_name::text, null, null, null, null
        from zti_projekt2.location_company_name
        where zti_projekt2.location_company_name.location_id = p_location_id
        union

        select zti_projekt2.location_country_code.timestamp, null, null, null, null, null, null, zti_projekt2.location_country_code.country_code_id::text,
               (
                   select zti_projekt2.country_code_code.country_code
                   from zti_projekt2.country_code_code
                   where zti_projekt2.country_code_code.country_code_id = zti_projekt2.location_country_code.country_code_id
                   order by zti_projekt2.country_code_code.timestamp desc
                   limit 1
               ) as country_code, null, null
        from zti_projekt2.location_country_code
        where zti_projekt2.location_country_code.location_id = p_location_id
        union
        select zti_projekt2.location_activity.timestamp, null, null, null, null, null, null, null, null, zti_projekt2.location_activity.activity_id::text,
               (
                   select zti_projekt2.activity_name.activity_name
                   from zti_projekt2.activity_name
                   where zti_projekt2.activity_name.activity_id = zti_projekt2.location_activity.activity_id
                   order by zti_projekt2.activity_name.timestamp desc
                   limit 1
               ) as activity_name
        from zti_projekt2.location_activity
        where zti_projekt2.location_activity.location_id = p_location_id
        order by timestamp desc;

END;
$$ LANGUAGE plpgsql;


select * from zti_projekt2.get_location_timeline(4);


select timestamp, bool_and(is_active), max(street_address),	max(city),	max(zipcode),	max(state),	max(company_name),	max(country_code_id),	max(country_code_code),	max(activity_id),	max(activity_name)
FROM zti_projekt2.get_location_timeline(5)
GROUP BY timestamp
order by timestamp desc;
