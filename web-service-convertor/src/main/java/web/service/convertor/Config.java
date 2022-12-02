package web.service.convertor;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "convertor")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema bikeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ConvertorPort");
        wsdl11Definition.setLocationUri("/ws/convertor");
        wsdl11Definition.setTargetNamespace("http://www.springframework.org/schema/web-services");
        wsdl11Definition.setSchema(bikeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema bikeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("convertor.xsd"));
    }

    @Bean
    public Convertor getService() {
        var service = new Convertor();
        service.addCurrency(new Convertor.Currency("USD", "EURO", 0.5));
        service.addCurrency(new Convertor.Currency("EURO", "USD", 2.0));
        return service;
    }

}