package vn.ptit.configs;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//đánh dấu để spring-boot biết file này dùng để cấu hình.
@Configuration

//bật tính năng quản lí transaction của spring container.
//transaction có 2 loại là resource-local và jta.
//đối với resourece-local thì developer phải manual transaction.
@EnableTransactionManagement

//hướng dẫn springboot biết nơi chứa các Repository
@EnableJpaRepositories(basePackages = { "vn.ptit" })
public class HibernateConfig {
	/**
	 * nằm giữa database(mysql) và project. bao gồm connection-pool. Hikari là một
	 * connection pool.
	 * 
	 * @return
	 */
	@Bean(destroyMethod = "close")
	protected DataSource dataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceConfig.setJdbcUrl("jdbc:mysql://team1-java-web.c41fdgtlmuma.us-east-2.rds.amazonaws.com:3306/banking_project?useUnicode=true&characterEncoding=UTF-8");
		dataSourceConfig.setUsername("root");
		dataSourceConfig.setPassword("root123456");
		return new HikariDataSource(dataSourceConfig);
	}

	/**
	 * tạo EntityManagerFactory. Dùng để tạo EntiyManager.
	 * 
	 * @return
	 */
	@Bean
	protected LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		Properties jpaProperties = new Properties();
		// Cấu hình phương ngữ cơ sở dữ liệu sử dụng. Điều này cho phép Hibernate tạo SQL tối ưu hóa cho database sử dụng.
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		// Chỉ định hành động được gọi đến database when the Hibernate SessionFactory is created or closed.
		// validate là xác thực lược đồ, không thực hiện thay đổi nào đối với cơ sở dữ liệu.
		jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
		// Hibernate sử dụng chiến lược này để ánh xạ tên thuộc tính và thực thể java với cơ sở dữ liệu quan hệ và tên cột tương ứng.
		// Cấu hình chiến lược đặt tên được sử dụng khi Hibernate tạo các đối tượng cơ sở dữ liệu và phần tử lược đồ mới.
		jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		// Nếu giá trị của thuộc tính này là true, Hibernate sẽ ghi tất cả các câu lệnh SQL vào bảng điều khiển.
		jpaProperties.put("hibernate.show_sql", "true");
		// Nếu giá trị của thuộc tính này là true, Hibernate sẽ format the SQL.
		// written to the console.
		jpaProperties.put("hibernate.format_sql", "true");

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		// package chứa các entities.
		entityManagerFactoryBean.setPackagesToScan("vn.ptit");
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}

	/**
	 * vì bật tính năng @EnableTransactionManagement nên cần tạo TransactionManager
	 * để spring contatiner sử dụng.
	 * 
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean
	protected JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
