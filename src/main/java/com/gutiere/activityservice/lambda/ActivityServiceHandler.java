package com.gutiere.activityservice.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gutiere.activityservice.activities.*;
import com.gutiere.activityservice.data.Response;
import com.gutiere.activityservice.utils.IoManager;


public class ActivityServiceHandler {

    public void handleRequest( InputStream inputStream, OutputStream outputStream ) throws IOException {
        final IoManager io = new IoManager( inputStream, outputStream, new ObjectMapper(  ) );
        final String resource = io.getInputMap().get( "resource" ).toString();
        final String body = io.getInputMap().get( "body" ).toString();

        final Response response = executeActivity( resource, body );

        io.respond( response );
    }

    private Response executeActivity( String resource, String body ) {
        switch( resource ) {
            case "/createactivity":
                return new CreateActivity().handleRequest( body );
            case "/describeactivity":
                return new DescribeActivty().handleRequest( body );
            case "/describeactivities":
                return new DescribeActivities().handleRequest( body );
            case "/updateactivity":
                return new UpdateActivity().handleRequest( body );
            case "/deleteactivity":
                return new DeleteActivity().handleRequest( body );
            default:
                throw new IllegalArgumentException( String.format( "Unknown resource: %s", resource ) );
        }
    }

}
