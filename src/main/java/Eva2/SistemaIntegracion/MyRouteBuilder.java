package Eva2.SistemaIntegracion;

import java.nio.file.DirectoryStream.Filter;

//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.processor.Enricher;

/**
 * A Camel Java8 DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {

		from("file:C:/Enter").split().tokenize("\n").to("direct:Email");
	
		from("direct:Email")
		.choice().
		when(body().contains("Mensaje formato destinado a Archivo plano")).
		setBody(body().append(" palabras extras para archivo plano ")).to("jms:queue:Archivo Plano").
		when(body().contains("Mensaje formato destinado a JSON")).
		setBody(body().append(" palabras extras para archivos JSON")).to("jms:queue:JSON").
		when(body().contains("Mensaje formato destinado a XML")).
		setBody(body().append(" palabras extras para archivos XML")).to("jms:queue:XML").
		when(body().contains("Mensaje formato destinado a API Restful")).
		setBody(body().append(" palabras extras para archivos API Restfull")).to("jms:queue:API Restful").
		otherwise().filter(body().contains("otros")).to("jms:queue:Filtrados");


	}

}
