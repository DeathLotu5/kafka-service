package jmaster.io.accountservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	NewTopic notification() {
		//							Topic name	   partition number	  replication number (Thông thường số lượng Replication sẽ bằng với số lượng Broker server)
		return new NewTopic("notification", 2,		  (short) 1);
	}

	@Bean
	NewTopic statistic() {
//							Topic name			partition number	replication number
		return new NewTopic("statistic", 1, 	    (short) 1);
	}
}
