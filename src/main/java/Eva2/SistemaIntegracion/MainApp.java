package Eva2.SistemaIntegracion;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;


/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
       MyRouteBuilder router = new MyRouteBuilder();
       CamelContext ctx = new DefaultCamelContext();
       ConnectionFactory conexion = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
       
       
       ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(conexion));
       
       try {
		ctx.addRoutes(router);
		ctx.start();
		Thread.sleep(5*60*1000);
		ctx.stop();
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
}

