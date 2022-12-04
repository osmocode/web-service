package web.service.bank;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class Config {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/bank/*");
    }

    @Bean(name = "bank")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema bikeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BankPort");
        wsdl11Definition.setLocationUri("/ws/bank/bank");
        wsdl11Definition.setTargetNamespace("http://www.springframework.org/schema/web-services");
        wsdl11Definition.setSchema(bikeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema bikeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("bank.xsd"));
    }

}
