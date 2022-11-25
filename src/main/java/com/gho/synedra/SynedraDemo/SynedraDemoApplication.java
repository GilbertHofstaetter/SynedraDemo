package com.gho.synedra.SynedraDemo;

import com.gho.synedra.SynedraDemo.service.NameTransformerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class SynedraDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SynedraDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(NameTransformerService nameTransformerService) {
		return (args) -> {
			System.out.println("Stufe 1----------------------------------------------");
			System.out.println(nameTransformerService.transformName("KOESTNER"));
			System.out.println(nameTransformerService.transformName("RUESSWURM"));
			System.out.println(nameTransformerService.transformName("DUERMUELLER"));
			System.out.println(nameTransformerService.transformName("JAEAESKELAEINEN"));
			System.out.println(nameTransformerService.transformName("GROSSSCHAEDL"));

			System.out.println("Stufe 2----------------------------------------------");

			Set<String> names = nameTransformerService.transformNameFull("KOESTNOER");
			names.forEach(tName -> System.out.println(tName));

			System.out.println("----------------------------------------------------");

			names = nameTransformerService.transformNameFull("RUESSWURM");
			names.forEach(tName -> System.out.println(tName));

			System.out.println("----------------------------------------------------");

			names = nameTransformerService.transformNameFull("KOESTNER");
			names.forEach(tName -> System.out.println(tName));

			System.out.println("----------------------------------------------------");

			names = nameTransformerService.transformNameFull("RUESSWURM");
			names.forEach(tName -> System.out.println(tName));

			System.out.println("----------------------------------------------------");

			names = nameTransformerService.transformNameFull("DUERMUELLER");
			names.forEach(tName -> System.out.println(tName));

			System.out.println("----------------------------------------------------");

			names = nameTransformerService.transformNameFull("JAEAESKELAEINEN");
			names.forEach(tName -> System.out.println(tName));

			System.out.println("----------------------------------------------------");

			names = nameTransformerService.transformNameFull("GROSSSCHAEDL");
			names.forEach(tName -> System.out.println(tName));

			System.out.println("----------------------------------------------------");
		};
	}
}
