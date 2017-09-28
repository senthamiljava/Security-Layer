package com.revature.security.boot;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.security.utils.AppConstants;

/**
 * This is to bootstrap the whole application when we need the stand alone application
 * 
 * @author MUTHU G.K
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class SecurityLayerApplicationJar implements ApplicationRunner {

  private static final Logger LOG = LogManager.getLogger(SecurityLayerApplicationJar.class);

  public static void main(String[] args) {
    SpringApplication.run(SecurityLayerApplicationJar.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    LOG.info(" SPRING BOOT for :: {}  :: started", "Security");
  }

  @Bean
  public HibernateJpaSessionFactoryBean sessionFactory() {
    return new HibernateJpaSessionFactoryBean();
  }

  // ----------- JNDI configuration for embedded Tomcat-Server ------ [MUTHU G.K]

  @Bean
  public TomcatEmbeddedServletContainerFactory tomcatFactory() {
    return new TomcatEmbeddedServletContainerFactory() {

      @Override
      protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
        tomcat.enableNaming();
        return super.getTomcatEmbeddedServletContainer(tomcat);
      }

      @Override
      protected void postProcessContext(Context context) {

        ContextResource resource = new ContextResource();

        resource.setName("jdbc/securityDataSource");
        resource.setType(DataSource.class.getName());
        resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
        resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        resource.setProperty("url","jdbc:mysql://localhost:3306/productiondb?autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        resource.setProperty("username","root");
        resource.setProperty("password","root");

        context.getNamingResources().addResource(resource);
      }
    };
  }

  /*
   * @Bean public DataSource jndiDataSource() throws NamingException {
   * 
   * JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
   * 
   * bean.setJndiName("java:comp/env/jdbc/securityDataSource");
   * bean.setProxyInterface(DataSource.class); bean.setLookupOnStartup(false);
   * bean.afterPropertiesSet();
   * 
   * return (DataSource) bean.getObject(); }
   */

}
