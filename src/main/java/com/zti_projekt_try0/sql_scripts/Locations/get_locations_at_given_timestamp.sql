
CREATE OR REPLACE FUNCTION zti_projekt2.get_locations_at_given_timestamp(upto_date timestamp)
    RETURNS TABLE(location_id INTEGER, is_active boolean, street_address varchar,
                  city varchar, zipcode varchar, state varchar, country_code varchar, country_code_id integer, is_country_code_active boolean, activity_name varchar, activity_id integer, is_activity_active boolean, company_name varchar
                 ) AS $$
BEGIN
    RETURN QUERY
        SELECT zti_projekt2.location.location_id,
               (select zti_projekt2.location_is_active.is_active
                from zti_projekt2.location_is_active
                where zti_projekt2.location.location_id = zti_projekt2.location_is_active.location_id
                  and zti_projekt2.location_is_active.timestamp < upto_date
                order by timestamp desc
                limit 1
               ) as is_active,
               (select zti_projekt2.location_street_address.street_address
                from zti_projekt2.location_street_address
                where zti_projekt2.location.location_id = zti_projekt2.location_street_address.location_id
                  and zti_projekt2.location_street_address.timestamp < upto_date
                order by timestamp desc

                limit 1
               ),
               (select zti_projekt2.location_city.city
                from zti_projekt2.location_city
                where zti_projekt2.location.location_id = zti_projekt2.location_city.location_id
                  and zti_projekt2.location_city.timestamp < upto_date
                order by timestamp desc
                limit 1
               ),
               (select zti_projekt2.location_zipcode.zipcode
                from zti_projekt2.location_zipcode
                where zti_projekt2.location.location_id = zti_projekt2.location_zipcode.location_id
                  and zti_projekt2.location_zipcode.timestamp < upto_date
                order by timestamp desc
                limit 1
               ),
               (select zti_projekt2.location_state.state
                from zti_projekt2.location_state
                where zti_projekt2.location.location_id = zti_projekt2.location_state.location_id
                  and zti_projekt2.location_state.timestamp < upto_date
                order by timestamp desc
                limit 1
               ),


               (select zti_projekt2.country_code_code.country_code
                from zti_projekt2.location_country_code
                         join zti_projekt2.country_code_code on zti_projekt2.country_code_code.country_code_id = zti_projekt2.location_country_code.country_code_id
                where zti_projekt2.location.location_id = zti_projekt2.location_country_code.location_id
                  and zti_projekt2.country_code_code.timestamp < upto_date
                  and zti_projekt2.location_country_code.timestamp < upto_date
                order by location_country_code.timestamp desc, country_code_code.timestamp desc
                limit 1
               ),

               (select zti_projekt2.location_country_code.country_code_id
                from zti_projekt2.location_country_code
                where zti_projekt2.location.location_id = zti_projekt2.location_country_code.location_id
                  and zti_projekt2.location_country_code.timestamp < upto_date
                order by location_country_code.timestamp desc
                limit 1
               ),
               (select zti_projekt2.country_code_is_active.is_active
                from zti_projekt2.location_country_code
                         join zti_projekt2.country_code_is_active on zti_projekt2.country_code_is_active.country_code_id = zti_projekt2.location_country_code.country_code_id
                where zti_projekt2.location.location_id = zti_projekt2.location_country_code.location_id
                  and zti_projekt2.location_country_code.timestamp < upto_date
                  and zti_projekt2.country_code_is_active.timestamp < upto_date
                order by location_country_code.timestamp desc, country_code_is_active.timestamp desc
                limit 1
               ),


               (select zti_projekt2.activity_name.activity_name
                from zti_projekt2.location_activity
                         join zti_projekt2.activity_name on zti_projekt2.activity_name.activity_id = zti_projekt2.location_activity.activity_id
                where zti_projekt2.location.location_id = zti_projekt2.location_activity.location_id
                  and zti_projekt2.location_activity.timestamp < upto_date
                  and zti_projekt2.activity_name.timestamp < upto_date
                order by location_activity.timestamp desc, activity_name.timestamp desc
                limit 1
               ),
               (select zti_projekt2.location_activity.activity_id
                from zti_projekt2.location_activity
                where zti_projekt2.location.location_id = zti_projekt2.location_activity.location_id
                  and zti_projekt2.location_activity.timestamp < upto_date
                order by location_activity.timestamp desc
                limit 1
               ),
               (select zti_projekt2.activity_is_active.is_active
                from zti_projekt2.location_activity
                         join zti_projekt2.activity_is_active on zti_projekt2.activity_is_active.activity_id = zti_projekt2.location_activity.activity_id
                where zti_projekt2.location.location_id = zti_projekt2.location_activity.location_id
                  and zti_projekt2.location_activity.timestamp < upto_date
                  and zti_projekt2.activity_is_active.timestamp < upto_date
                order by location_activity.timestamp desc, activity_is_active.timestamp desc
                limit 1
               ),


               (select zti_projekt2.location_company_name.company_name
                from zti_projekt2.location_company_name
                where zti_projekt2.location.location_id = zti_projekt2.location_company_name.location_id
                  and zti_projekt2.location_company_name.timestamp < upto_date
                order by timestamp desc
                limit 1
               )
        FROM zti_projekt2.location;

END;
$$ LANGUAGE plpgsql;




select  * from zti_projekt2.get_locations_at_given_timestamp('2023-05-10 00:00:00')
where is_active is not null