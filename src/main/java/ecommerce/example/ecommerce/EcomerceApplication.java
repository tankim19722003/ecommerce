package ecommerce.example.ecommerce;

import ecommerce.example.ecommerce.Repo.RoleRepo;
import ecommerce.example.ecommerce.Repo.ShippingProviderRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.models.Role;
import ecommerce.example.ecommerce.models.ShippingProvider;
import ecommerce.example.ecommerce.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class EcomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepo userRepo, RoleRepo roleRepo) {
		return runner -> {
//			createShippingProvider(shippingProviderRepo);
			createUser(userRepo, roleRepo);
		};
	}

	private void createUser(UserRepo userRepo, RoleRepo roleRepo) {
		long roleId = 1;

		// get role
		Role role = roleRepo.findById(roleId).orElseThrow(() ->
				new RuntimeException("Role does not found"));


		User user = new User();
		// Setting values using setters
		user.setId(1L);
		user.setFullname("John Doe");
		user.setAccount("john_doe");
		user.setPassword("securepassword");
		user.setAddress("123 Street, City");
		user.setEmail("john@example.com");
		user.setGender(true); // true for male, false for female
		user.setBirthdate(LocalDate.of(1995, 5, 20));
		user.setAvatar("avatar.jpg");
		user.setRole(role);

		System.out.println("Saving user...");
		userRepo.save(user);
	}

	private void createShippingProvider(ShippingProviderRepo shippingProviderRepo) {
		ShippingProvider shippingProvider = new ShippingProvider();
		shippingProvider.setAccount("FastExpress");
		shippingProvider.setPassword("securePass123");
		shippingProvider.setShippingSpeed("Express");
		shippingProvider.setShippingCost(50);
		shippingProvider.setCreatedAt(LocalDate.now());
		shippingProvider.setPhone("+1234567890");
		shippingProvider.setEmail("contact@fastexpress.com");
		shippingProvider.setStatus("pending");
		shippingProviderRepo.save((shippingProvider));
	}

	public void createRole(RoleRepo roleRepo) {
		Role role = new Role();
		role.setName("Admin");
		role = roleRepo.save(role);
		System.out.println("Role: " + role);
	}

//	public void createUser(UserRepo userRepo, RoleRepo roleRepo) {
//
//		// create role
//		Role role = new Role();
//		role.setName("Admin");
//		role = roleRepo.save(role);
//
//
//		User user = new User(
//				1L,                          // id
//				"John Doe",                  // fullname
//				"johndoe123",                // account
//				"password123",               // password
//				"123 Main St, New York",     // address
//				"johndoe@example.com",       // email
//				true,                        // gender (true = male, false = female)
//				LocalDate.of(1995, 5, 20),   // birthdate
//				"avatar.png",                // avatar
//				role                         // role
//		);
//	}

}
