package com.example.PetFriends_Almoxarifado;

import com.example.PetFriends_Almoxarifado.domain.OrdemDeServico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.Console;

@SpringBootApplication
@EnableFeignClients
public class PetFriendsAlmoxarifadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetFriendsAlmoxarifadoApplication.class, args);

		OrdemDeServico os = new OrdemDeServico(1L);
		System.out.println(os);
	}

}
