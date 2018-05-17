package com.gutiere.activityservice.activities;

import com.gutiere.activityservice.data.Response;

public class DescribeActivities {
    public Response handleRequest( String body ) {
        return new Response( 200, "You've hit describe activities." );
    }
}
