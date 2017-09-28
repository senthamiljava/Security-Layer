package com.revature.security.boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This is to bootstrap the whole application when application server direct deployment happens
 * 
 * @author MUTHU G.K
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class SecurityLayerApplicationWar extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SecurityLayerApplicationWar.class);
  }

  @Bean
  public HibernateJpaSessionFactoryBean sessionFactory() {
    return new HibernateJpaSessionFactoryBean();
  }

  /**
   * To enable the JNDI please enable following lines. But before make sure that, your application
   * server have the same name data sources connection services to make the JNDI connection
   **/
  /*
   * @Bean public DataSource dataSource() { return new
   * JndiDataSourceLookup().getDataSource("java:comp/env/jdbc/revProd"); }
   */

}
