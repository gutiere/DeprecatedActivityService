package com.gutiere.activityservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gutiere.activityservice.data.Response;

import java.io.*;
import java.util.Map;

public class IoManager {

    private OutputStream outputStream;
    private Map inputMap;
    private ObjectMapper objectMapper;

    public IoManager( InputStream inputStream, OutputStream outputStream, ObjectMapper objectMapper ) throws IOException {
        this.outputStream = outputStream;
        this.inputMap = objectMapper.readValue( inputStream, Map.class );
        this.objectMapper = objectMapper;
    }

    public Map getInputMap() {
        return inputMap;
    }

    public void respond( Response responseJson ) throws IOException {
        String responseString = objectMapper.writeValueAsString( responseJson );
        System.out.println( responseString );
        OutputStreamWriter writer = new OutputStreamWriter( outputStream, "UTF-8" );
        writer.write( responseString );
        writer.close();
    }
}
