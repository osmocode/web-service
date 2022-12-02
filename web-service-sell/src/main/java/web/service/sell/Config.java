package web.service.sell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import rmi.bike.interfaces.bike.BikeListService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/service/*");
    }

    @Bean(name = "bike")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema bikeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BikePort");
        wsdl11Definition.setLocationUri("/service/bike");
        wsdl11Definition.setTargetNamespace("http://www.springframework.org/schema/web-services");
        wsdl11Definition.setSchema(bikeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema bikeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("bike.xsd"));
    }


    @Bean
    BikeListService getBikeService(@Value("#{environment.BIKE_SERVICE_HOST}") String bikeServiceHost) throws RemoteException, MalformedURLException, NotBoundException {
        return (BikeListService) Naming.lookup("rmi://"+bikeServiceHost);
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("web.service.wsdl.convertor");
        return marshaller;
    }

}
