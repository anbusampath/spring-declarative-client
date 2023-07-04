package com.demo.client;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	PersonService personService(){
		WebClient client = WebClient.create("http://localhost:8080/");
				
		return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build()
                .createClient(PersonService.class);

	}

	@Bean
	CommandLineRunner commandLineRunnerBean() {
		return (args) -> {
			System.out.println("By name " + personService().personByName("Anbu"));
			System.out.println("By name " + personService().personByName("Sampath"));
			System.out.println("List " + personService().personList());
		};
	}
}

interface PersonService {

    @GetExchange("/person/{name}")
    Person personByName(@PathVariable String name);

    @GetExchange("/person")
    List<Person> personList();

}

record Person(String name, String city) {}
