package jmaster.io.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync //Khi chúng ta gửi email thì sẽ tạo ra 1 luồng mới thay vì gửi email trong luồng chính để giảm việc chờ đợi
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

//	Kết hợp sử dụng với Deserializer được cấu hình cho comsumer để convert ByteStream về đối tượng
	@Bean
	JsonMessageConverter converter() {
		return new JsonMessageConverter();
	}
}
