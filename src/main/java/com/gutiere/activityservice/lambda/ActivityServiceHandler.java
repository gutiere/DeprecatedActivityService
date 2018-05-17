package com.gutiere.activityservice.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gutiere.activityservice.utils.IoManager;
import org.json.simple.JSONObject;


public class ActivityServiceHandler {

    public void handleRequest( InputStream inputStream, OutputStream outputStream ) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final IoManager io = new IoManager( inputStream, outputStream, objectMapper );
        final String resource = io.getInputMap().get( "resource" ).toString();


        final String response = executeActivity( resource );

        io.respond( response );
    }

    private String executeActivity( String resource ) {

        System.out.println( String.format( "Resource: %s", resource ) );

        return buildDemoResponse();
    }

    private String buildDemoResponse(  ) {
        final JSONObject responseJson = new JSONObject();
        final String responseCode = "200";
        final JSONObject headerJson = new JSONObject();
        headerJson.put( "x-custom-header", "my custom header value" );
        responseJson.put( "isBase64Encoded", false );
        responseJson.put( "statusCode", responseCode );
        responseJson.put( "headers", headerJson );
        responseJson.put( "body", "YO I'M THE BODY" );

        return responseJson.toJSONString();

    }

}
