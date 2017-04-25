package Communication;

/**
 *
 * @author badWarden
 */
public class Response {

    private Object response;
    private Request initialRequest;
    private String error;

    public Response() {
    }
    
    public Response(Request req, Object r) {
        if (r instanceof Exception) {
            initialRequest = req;
            error = r.toString();
            response = null;
        }
        else {
            initialRequest = req;
            response = r;
            error = null;
        }
    }

    public boolean isCorrect() {
        return error == null;
    }

    public Exception getError() {
        return new Request.IncorrectRequestException(error);
    }

    /**
     * @return the response sent by the server
     */
    public Object getResponseObject() {
        return response;
    }

    /**
     * @return the initial request sent to the server
     */
    public Request getInitialRequest() {
        return initialRequest;
    }
    
    public String getResponseInfo() {
        return isCorrect() ? response.toString() : getError().toString();
    }

}
