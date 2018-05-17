package com.gutiere.activityservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Map;

public class IoManager {

    private OutputStream outputStream;
    private Map inputMap;

    public IoManager( InputStream inputStream, OutputStream outputStream, ObjectMapper objectMapper ) throws IOException {
        this.outputStream = outputStream;
        this.inputMap = objectMapper.readValue( inputStream, Map.class );
    }

    public Map getInputMap() {
        return inputMap;
    }

    public void respond( String responseJson ) throws IOException {
        System.out.println( "Responding with ");
        OutputStreamWriter writer = new OutputStreamWriter( outputStream, "UTF-8" );
        writer.write( responseJson );
        writer.close();
    }
}
