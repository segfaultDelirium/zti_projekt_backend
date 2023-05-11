
create or replace function zti_projekt2.get_location_timeline_grouped_by_timestamp(p_location_id INTEGER)
    RETURNS TABLE ("timestamp" TIMESTAMP, is_active boolean, street_address text, city text, zipcode text, "state" text,
                   company_name text, country_code_id text, country_code_code text, activity_id text, activity_name text) AS $$
BEGIN
    RETURN QUERY

        select timeline.timestamp, bool_and(timeline.is_active), max(timeline.street_address),	max(timeline.city),	max(timeline.zipcode),	max(timeline.state),
               max(timeline.company_name),	max(timeline.country_code_id),
               max(timeline.country_code_code),	max(timeline.activity_id),	max(timeline.activity_name)

        FROM zti_projekt2.get_location_timeline(p_location_id) as timeline
        GROUP BY timeline.timestamp
        order by timeline.timestamp desc;

END;
$$ LANGUAGE plpgsql;


select * from zti_projekt2.get_location_timeline_grouped_by_timestamp(5);
