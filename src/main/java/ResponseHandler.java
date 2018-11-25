import Common.Working;
import Common.Broken;
import com.sun.net.httpserver.HttpExchange;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.io.OutputStream;

public class ResponseHandler {

    public Broken broken;
    public Working working;

    private JAXBContext jaxbContext;
    private Marshaller marshaller;


    public ResponseHandler(Working working)
            throws JAXBException
    {
        this.working = working;
        this.Init();
    }

    public ResponseHandler(Broken broken)
            throws JAXBException
    {
        this.broken = broken;
        this.Init();
    }


    private void Init()
        throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Working.class);
        this.marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
    }

    public void RespondWorking(HttpExchange httpExchange)
            throws JAXBException, IOException
    {
        java.io.StringWriter output = new StringWriter();
        this.marshaller.marshal(this.working, output);
        String response = output.toString();
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    public void RespondBroken(HttpExchange httpExchange)
            throws JAXBException, IOException
    {
        java.io.StringWriter output = new StringWriter();
        this.marshaller.marshal(this.broken, output);
        String response = output.toString();
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
