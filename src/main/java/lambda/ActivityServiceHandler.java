package lambda;

import com.amazonaws.services.lambda.runtime.Context;

import java.io.InputStream;
import java.io.OutputStream;

public class ActivityServiceHandler {
    public void handleRequest( final InputStream inputStream, final OutputStream outputStream, Context context )
            throws Exception {

        outputStream.write( "Hello World".getBytes() );
    }
}
