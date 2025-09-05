package com.JPA.Spring.JPA;

import com.JPA.Spring.JPA.Repository.CustomerRepository;
import com.JPA.Spring.JPA.enitity.Customer;
import com.monitorjbl.xlsx.StreamingReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class SpringJpaApplication implements CommandLineRunner {


	private final CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		log.info("-> Reading File");
		InputStream is = new FileInputStream(new File("customers.xlsx"));
		Workbook workbook = StreamingReader.builder()
				.rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
				.bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
				.open(is);            // InputStream or File for XLSX file (required)

		List<Customer> customers = StreamSupport. stream(workbook.spliterator(), false)
				.flatMap(sheet-> StreamSupport.stream(sheet.spliterator(), false))
						.skip(1)
						.map( con -> {
							Customer customer = new Customer();

							// Asigna los valores de las celdas a las propiedades del objeto Customer
							// Celda 0 (ID): Se lee como número y se convierte a long
							//customer.setId((long) con.getCell(0).getNumericCellValue());
							// Celda 1 (Nombre): Se lee como texto
							customer.setName(con.getCell(1).getStringCellValue());
							// Celda 2 (Apellido): Se lee como texto
							customer.setLastName(con.getCell(2).getStringCellValue());
							// Celda 3 (Dirección): Se lee como texto
							customer.setAddress(con.getCell(3).getStringCellValue());
							// Celda 4 (Email): Se lee como texto
							customer.setEmail(con.getCell(4).getStringCellValue());
							return customer;
						})
					.toList();

		long endTimeRead=System.currentTimeMillis();

		long startTimeRead = System.currentTimeMillis();
		log.info("--> Reading finished, time " + (endTimeRead - startTimeRead) + "ms");
		long startTimeWrite = System.currentTimeMillis();
		customerRepository.saveAll(customers);
		long endTimeWrite=System.currentTimeMillis();
		log.info("-> Write finished, time "+ (endTimeWrite - startTimeWrite)+ "ms");


	}
}
