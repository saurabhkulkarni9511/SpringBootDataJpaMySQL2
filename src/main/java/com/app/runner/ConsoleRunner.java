package com.app.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.app.model.Product;
import com.app.repo.ProductRepsitory;

@Component
public class ConsoleRunner implements CommandLineRunner{

	@Autowired
	private ProductRepsitory repo;


	@Override
	public void run(String... args) throws Exception {

		//finAll(sort)
		repo.findAll(Sort.by(Direction.DESC,"prodCode")).
		forEach(System.out::println);
		
		repo.findAll(Sort.by("prodCode")).forEach(System.out::println);
		
		//findAll(pagable)
		PageRequest p=new PageRequest(1, 3);
		repo.findAll(p).forEach(System.out::println);
		
		//findAll(Example)
		//1.
		Product p1=new Product();
		p1.setProdCost(2.2);
		Example<Product> ex=Example.of(p1);
		repo.findAll(ex).forEach(System.out::println);
		
		//2.with sorting
		Product p2=new Product();
		p2.setProdCost(2.2);
		Example <Product> ex1=Example.of(p2);
		repo.findAll(ex1,Sort.by(Direction.DESC,"prodCode")).forEach(System.out::println);
	
		//3.with pagination
		Product p3=new Product();
		p3.setProdCost(2.2);
		Example<Product> ex2=Example.of(p3);
		repo.findAll(ex2,PageRequest.of(1, 2)).forEach(System.out::println);
		
		//findAllById
		repo.findAllById(Arrays.asList(2,6,8,10)).forEach(System.out::println);
	}
}
