package com.aercio.springtestecrud;

import com.aercio.springtestecrud.model.Pessoas;
import com.aercio.springtestecrud.repository.PessoasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.LongStream;

@SpringBootApplication
public class SpringTesteCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTesteCrudApplication.class, args);
	}
//	@Bean
//	CommandLineRunner init(PessoasRepository repository) {
//		return args -> {
//			repository.deleteAll();
//			LongStream.range(1, 11)
//					.mapToObj(i -> {
//						Pessoas pessoa = new Pessoas();
//						pessoa.setNome("Pessoa " + i);
//						pessoa.setDataNascimento(new Date(new java.util.Date().getTime()));
//						pessoa.setCpf("12345678923");
//						return pessoa;
//					})
//					.map(v -> repository.save(v))
//					.forEach(System.out::println);
//		};
//	}
}
