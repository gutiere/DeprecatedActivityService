package com.gutiere.activityservice.activities;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.gutiere.activityservice.data.Response;

import java.util.UUID;

public class CreateActivity {
    public Response handleRequest( String body ) {

        System.out.println( "Body(" + body + ")");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

        DynamoDB dynamoDB = new DynamoDB( client );

        Table activityTable = dynamoDB.getTable( "ActivityTable" );

        Item item = new Item()
                .with( "ActivityId", UUID.randomUUID().toString() )
                .with( "Name", "Sleeping" )
                .with( "Description", "Relaxing, healing, dreaming..." );

        activityTable.putItem( item );

        return new Response( 200, "You've hit create activity." );
    }
}
