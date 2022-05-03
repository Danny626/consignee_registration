package com.albo.registroconsignatario;

import javax.annotation.Resource;

import com.albo.registroconsignatario.service.IFilesStorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistroConsignatarioApplication implements CommandLineRunner {

	@Resource
	IFilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(RegistroConsignatarioApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

}
