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
        return new ServletRegistrationBean(servlet, "/ws/convertor/*");
    }

    @Bean(name = "convertor")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema bikeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ConvertorPort");
        wsdl11Definition.setLocationUri("/ws/convertor/convertor");
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
        service.addCurrency(new Convertor.Currency("USD", "EURO", 0.9498));
        service.addCurrency(new Convertor.Currency("USD", "CNY", 7.0189));
        service.addCurrency(new Convertor.Currency("EURO", "USD", 1.0528532323));
        service.addCurrency(new Convertor.Currency("EURO", "CNY", 7.3898715519));
        service.addCurrency(new Convertor.Currency("CNY", "EURO", 0.1353203493));
        service.addCurrency(new Convertor.Currency("CNY", "USD", 0.1424724672));
        return service;
    }

}