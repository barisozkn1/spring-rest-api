package com.barisozkan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barisozkan.model.Employee;
import com.barisozkan.model.UpdateEmployeeRequest;
import com.barisozkan.services.EmployeeService;

@RestController
@RequestMapping("/rest/api/employee") //kök adres ortak kök olur bu
public class RestEmployeeController {
	
	@Autowired //kullanılmazsa eğer liste null dönerdi. Bu enjekte etme işlemi sağladı
	private EmployeeService employeeService;

	@GetMapping(path = "/list")
	public List<Employee> getAllEmployeeList(){
		return employeeService.getAllEmployeeList();
		/*employeeService.getAllEmployeeList(); //service katmanına gecis
		return null;
		*/

	}
	@GetMapping(path = "/list/{id}")
	public Employee getEmployeeById(@PathVariable(name = "id", required = true) String id) {
		return employeeService.getEmployeeById(id);
		/*
		 * burada pathvariable anatasyonu sayesinde dışarıdan id üzerinden 
		 * istek gelecek ve bunu required ile gelmesi zorunludur diyorum.
		 * bu sayede id üzerinden gelen isteği karşılamış olacagız.
		 */
	}
	//requestParam ile parametreli işlem yapabiliyoruz.
	@GetMapping(path = "/with-params") 
	public List<Employee> getEmployeeWithParams(@RequestParam(name = "firstName", required = false)String firstName, 
												@RequestParam(name = "lastName", required = false)String lastName){
		return employeeService.getEmployeeWithParams(firstName, lastName);
		/*burada hata alırsın sebebi 2 tane mapping ve path aynı olur hata verir.
		 * 2 yöntemi var ya bu 39. satırdakı path de "/employee-list-with-params" yaparsın
		 * ya da ilk yazdıgın 23. satırdaki kodu kullanmazsın direkt buradaki yöntemle çağırırsın
		 * mesela hiçbir şey yazmazsan hepsi gelir, isim yazarsan o isim gelir vs.
		 * ama genellikle ikisi böyle yazılmaz with paramsla yazdıgın yöntem kullanılır
		 */
	}
	
	@PostMapping(path = "/save-employee")
	public Employee saveEmployee(@RequestBody Employee newEmployee) {
		return employeeService.saveEmployee(newEmployee);
		/*
		 * Postmapping anatasyonu sayesinde postman üzerinden mesela 
		 * yeni isciler ekleyebiliriz tabi postmande de post istegi ile atılmalı
		 * @RequestBody, postmande body e tıkla - raw secenegi ve json formatında
		 * model sınıfındaki isimlerle birebir uyusacak sekilde çift tırnak içinde
		 * eklenecek iscinin özelliklerini belirt.Örnegin;
		 * {"id" : "77",
    		"firstName" : "Ali", 
    		"lastName" : "Can"
		   } 
		   hemen yukarıdaki gibi ekleme yapılabilir mesela.
		 */
	}
	@DeleteMapping(path = "/delete-employee/{id}") //girdigimiz id ye göre silme islemi icin bu path kullanılır
	public boolean deleteEmployee(@PathVariable(name = "id")String id) {
		return employeeService.deleteEmployee(id);
		/*
		 * DeleteMapping anatasyonu ile istedigimiz id deki
		 * isciyi silebiliyoruz tabi burada id ye göre bulmak icin de
		 * @PathVariable anatasyonunu kullanırız yine
		 */
	}
	@PutMapping(path = "/update-employee/{id}") //guncelleme islemlerinde bu anatasyon kullanılır.
	public Employee updateEmployee(@PathVariable(name = "id")String id, @RequestBody UpdateEmployeeRequest request) {
		return employeeService.updateEmployee(id, request);
	}
}
