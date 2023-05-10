CREATE OR REPLACE FUNCTION zti_projekt2.get_current_activities()
    RETURNS TABLE(activity_id INTEGER, is_active boolean, activity_name varchar) AS $$
BEGIN
    RETURN QUERY
        SELECT zti_projekt2.activity.activity_id,

               (select zti_projekt2.activity_is_active.is_active
                from zti_projekt2.activity_is_active
                where zti_projekt2.activity.activity_id = zti_projekt2.activity_is_active.activity_id
                order by timestamp desc
                limit 1
               ),
               (select zti_projekt2.activity_name.activity_name
                from zti_projekt2.activity_name
                where zti_projekt2.activity.activity_id = zti_projekt2.activity_name.activity_id
                order by timestamp desc
                limit 1
               )

        FROM zti_projekt2.activity;

END;
$$ LANGUAGE plpgsql;

select * from zti_projekt2.get_current_activities();