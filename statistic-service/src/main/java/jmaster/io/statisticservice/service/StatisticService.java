package jmaster.io.statisticservice.service;

import jmaster.io.statisticservice.entity.Statistic;
import jmaster.io.statisticservice.repository.StatisticRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class StatisticService {
    private final StatisticRepository statisticRepository;

    @KafkaListener(id = "statisticGroup", topics = "statistic")
    public void listen(Statistic statistic) {
        log.info("Received: {}", statistic.getMessage());
        statisticRepository.save(statistic);
    }
}
