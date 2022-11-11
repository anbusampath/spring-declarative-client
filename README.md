Declarative Clients in Spring Framework 6

Rest App exposes two GET endpoints personByName - "/person/{name}" and personList - "/person"

Client consumes above endpoints with new Declarative Clients in Spring Framework 6 as below,

```
@Bean
	PersonService personService(){
		WebClient client = WebClient.create("http://localhost:8080/");
				
		return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build()
                .createClient(PersonService.class);

	}
  
  
  interface PersonService {

    @GetExchange("/person/{name}")
    Person personByName(@PathVariable String name);

    @GetExchange("/person")
    List<Person> personList();

}

record Person(String name, String city) {}
```
