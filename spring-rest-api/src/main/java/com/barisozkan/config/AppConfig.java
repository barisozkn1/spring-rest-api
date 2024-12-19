package com.barisozkan.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.barisozkan.model.Employee;
/*
 * BU SINIF VERİTABANI BAGLANTISINI HENÜZ YAPMADIGIMIZ İÇİN OLUŞTURULDU 
 * NORMALDE BÖYLE BİR YAPI YAPMAYACAGIZ VERİLERİ VERİTABANINDAN CEKECEGİZ
 */
@Configuration //konfigurasyon sınıfı diye işaretledik
public class AppConfig { 
	
	@Bean
	public List<Employee> employeeList(){
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee("38", "Baris", "Ozkan"));
		employeeList.add(new Employee("19", "Barkin", "Candir"));
		employeeList.add(new Employee("64", "Merve", "Sahin"));
		employeeList.add(new Employee("44", "Bugra", "Yildirim"));
		employeeList.add(new Employee("12", "Ozan Omer", "Ozkan"));
		
		return employeeList;
	}
}
