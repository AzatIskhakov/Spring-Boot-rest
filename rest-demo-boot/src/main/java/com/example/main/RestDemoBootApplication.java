package com.example.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Configuration
@ComponentScan
@EnableAutoConfiguration
//@SpringBootApplication
public class RestDemoBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoBootApplication.class, args);
	}
}

@Component
class BookingCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		for (Booking b : this.bookingRepository.findAll())
			System.out.println(b.toString());
		
	}
	
	@Autowired
	BookingRepository bookingRepository;
	
}

interface BookingRepository extends JpaRepository<Booking, Long> {
	
	Collection<Booking> findByBookingName(String bookingName);
}

@Entity
class Booking {
	
	@Id
	@GeneratedValue
	private Long id;
	private String bookingName;
	public Booking(String bookingName) {
		super();
		this.bookingName = bookingName;
	}
	public Booking() {
	}
	public Long getId() {
		return id;
	}
	public String getBookingName() {
		return bookingName;
	}
	
	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingName=" + bookingName + "]";
	}
		
}


@RestController
@RequestMapping("/bookings")
class BookingRestController {

    @Autowired
    BookingRepository bookingRepository;

    @RequestMapping(method = RequestMethod.POST)
    Booking add(@RequestBody Booking b) {
        return this.bookingRepository.save(b);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Booking> all() {
        return this.bookingRepository.findAll();
    }
}

@Controller
class BookingHtmlController {

    @Autowired
    BookingRepository bookingRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/bookings.html")
    String all(Model model) {
        model.addAttribute("bookings", this.bookingRepository.findAll());
        return "bookings";
    }
}