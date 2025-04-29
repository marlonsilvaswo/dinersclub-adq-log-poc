package pe.dinersclub.adqlog;

import java.util.Random;

import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource implements Runnable {

	@Inject
	ConnectionFactory connectionFactory;

	private final Random random = new Random();

	private volatile String lastPrice;

	public String getLastPrice() {
		return lastPrice;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloProducer() {

		try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
			
		String msg = Integer.toString(random.nextInt(100));
				
		System.out.println("Last Message Price Producer");
		System.out.println(msg);
		
			context.createProducer().send(context.createQueue("prices"), msg);
			
		}
		return "Hello from Quarkus REST 123 Producer";

	}
	

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String helloConsumer() {

		System.out.println("helloConsumer Last Message Price");
		
		try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
			JMSConsumer consumer = context.createConsumer(context.createQueue("prices"));

			Message message = consumer.receive();
			if (message != null) {
				lastPrice = message.getBody(String.class);
				System.out.println("Last Message Price");
				System.out.println(lastPrice);
			}else {
				System.out.println("Last Message Price is null");
			}

		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

		return "Hello from Quarkus REST 123 Consumer";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

