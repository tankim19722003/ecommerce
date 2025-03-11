package ecommerce.example.ecommerce;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.models.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class EcomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			OrderRepo orderRepo,
			UserRepo userRepo,
			ShippingProviderRepo shippingProviderRepo,
			RoleRepo roleRepo,
			SubCategoryRepo categoryRepo,
			OrderDetailRepo orderDetailRepo,
			ProductRepo productRepo,
			ShopRepo shopRepo,
			UserBannedRepo userBannedRepo,
			PaymentRepo paymentRepo,
			FeedBackRepo feedBackRepo,
			ShopBannedRepo shopBannedRepo,
			ShopRejectionRepo shopRejectionRepo,
			CouponRepo couponRepo,
			ShippingProviderRejectRepo shippingProviderRejectRepo,
			ProductRejectionRepo productRejectionRepo,
			ProductImageRepo imageRepo,
			ProductDiscountRepo productDiscountRepo,
			ProductImageRepo productImageRepo) {
		return runner -> {
//			deleteImage(productImageRepo);
		};
	}

	private void deleteImage(ProductImageRepo productImageRepo) {
		productImageRepo.deleteById(1L);
	}

	private void findingUser(UserRepo userRepo) {
		long userId = 3;
		String email = "alicesmith";
		System.out.println("check existing user with email " + email + "and has differ id with " + userId);
//		System.out.println(userRepo.existsByAccountAndDifferentUser(email, userId));
		System.out.println("Done!!");
	}

	private void checkExisting(UserRepo userRepo) {
		Boolean isEmailExisting = userRepo.existsByEmail("tankim1976");
		System.out.println("Email existing: " + isEmailExisting);
	}

	private void findUserByEmailOrPhoneNumber(UserRepo userRepo) {
		String account = "johndoe@example.com";
		System.out.println("Finding user: " + account);
//		User user = userRepo.findUserByEmailOrPhoneNumber(account);
//		System.out.println(user);
	}

//	private void createColorPrice(ColorPriceRepo colorPriceRepo, ProductSizeRepo productSizeRepo) {
//
//		ProductSize productSize = productSizeRepo.findById(3L).orElseThrow(() ->
//				new RuntimeException("Product with size does not found"));
//
//		ColorPrice colorPrice = new ColorPrice();
//
//		// Set values using setters
//		colorPrice.setId(null);                // Auto-generated, so can be null
//		colorPrice.setImageName("red_shirt.png");
//		colorPrice.setQuantity(50);
//		colorPrice.setColor("Red");
//		colorPrice.setProductSize(productSize);
//		System.out.println("Saving product size");
//		colorPriceRepo.save(colorPrice);
//		System.out.println("Done!!!");
//	}
//
//	private void createProductSize(ProductSizeRepo productSizeRepo, ProductRepo productRepo, SizeRepo sizeRepo) {
//
//		Product product = productRepo.findById(1L).orElseThrow(() -> new RuntimeException("product does not found"));
//		Size size = sizeRepo.findById(5L).orElseThrow(() -> new RuntimeException("size does not found"));
//
//		ProductSize productSize = new ProductSize();
//		productSize.setProduct(product);
//		productSize.setSize(size);
//		productSize.setPrice(100);
//
//		System.out.println("Saving product size...");
//		productSizeRepo.save(productSize);
//		System.out.println("Done!!");
//
//	}
//
//	private void createSize(SizeRepo sizeRepo, CategoryRepo categoryRepo) {
//
//		Category category = categoryRepo.findById(1L).orElseThrow(() ->
//				new RuntimeException("Category does not found"));
//
//		// Create a new Size object
//		Size size = new Size();
//		size.setSize("Small");
//		size.setCategory(category);
//		System.out.println("Saving size...");
//		sizeRepo.save(size);
//		System.out.println("Done!!	");
//	}

	private void createProductDiscount(ProductRepo productRepo, ProductDiscountRepo productDiscountRepo) {

		Product product = productRepo.findById(1L).orElseThrow(() ->
				new RuntimeException("Product does not exist"));


		// Creating a ProductDiscount object
		ProductDiscount discount = new ProductDiscount();
		discount.setId(100L); // Assuming an ID
		discount.setDiscountPercent(10.5F);
		discount.setDateStart(LocalDateTime.now());
		discount.setDateEnd(LocalDateTime.now().plusDays(7)); // Discount lasts 7 days
		discount.setProduct(product); // Linking to the product
		System.out.println("Creating discount...");
		productDiscountRepo.save(discount);
		System.out.println("Done");
	}

	private void createImage(ProductImageRepo imageRepo, ProductRepo productRepo) {
		Product product = productRepo.findById(1L).orElseThrow(() ->
				new RuntimeException("Product does found"));

		ProductImage image = new ProductImage();
		image.setId(100L);
//		image.setImageName("product_image.jpg");
//		image.setCreatedDate(LocalDateTime.now()); // Set creation date
//		image.setUpdatedDate(LocalDateTime.now()); // Set update date
		image.setProduct(product);

		System.out.println("Saving images...");
		imageRepo.save(image);
		System.out.println("Done...");

	}

	private void createProductRejection(ProductRepo productRepo, ProductRejectionRepo productRejectionRepo) {

		Product product = productRepo.findById(1L).orElseThrow(() ->
				new RuntimeException("Product does not exist!!"));
		ProductRejection rejection = new ProductRejection();
		rejection.setId(100L);
		rejection.setRejectedReason("Product description is misleading");
		rejection.setProduct(product);

		System.out.println("rejecting product...");
		productRejectionRepo.save(rejection);
		System.out.println("Done!");

	}

	private void createShippingProviderReject(ShippingProviderRepo shippingProviderRepo, ShippingProviderRejectRepo shippingProviderRejectRepo) {

		ShippingProvider shippingProvider = shippingProviderRepo.findById(3L).orElseThrow(()
				-> new RuntimeException("Shipping provider does not exist"));

		ShippingProviderReject reject = new ShippingProviderReject();
		reject.setId(100);
		reject.setRejectedReason("Failed to meet service quality standards");
		reject.setRejectedDate(LocalDateTime.now());
		reject.setShippingProvider(shippingProvider);

		System.out.println("Saving shipping provider reject");
		shippingProviderRejectRepo.save(reject);
		System.out.println("Done!!");
	}

	private void createCoupon(ShopRepo shopRepo, CouponRepo couponRepo) {

		Shop shop = shopRepo.findById(2L).orElseThrow(() ->  new RuntimeException("Shop does not exist"));

		// Creating a Coupon object
		Coupon coupon = new Coupon();
		coupon.setId(100L);
		coupon.setCode("DISCOUNT50");
		coupon.setDescription("Get 50% off on orders above $100");
		coupon.setDiscountValue(50.0f);
		coupon.setStartDate(LocalDateTime.now());
		coupon.setEndDate(LocalDateTime.now().plusDays(30)); // Valid for 30 days
		coupon.setMinimumOrderValue(100);
		coupon.setShop(shop);

		System.out.println("Creating coupon...");
		couponRepo.save(coupon);
		System.out.println("Done!!");

	}

	private void createShopRejection(ShopRejectionRepo shopRejectionRepo, ShopRepo shopRepo) {

		Shop shop = shopRepo.findById(2L).orElseThrow(() -> new RuntimeException("Shop does not found"));

		ShopRejection shopRejection = new ShopRejection();
		shopRejection.setId(100L);
		shopRejection.setRejectedReason("Incomplete business documentation");
		shopRejection.setRejectedDate(LocalDateTime.now());
		shopRejection.setShop(shop);

		System.out.println("Create shop rejection...");
		shopRejectionRepo.save(shopRejection);
		System.out.println("Done!!");

	}

	private void createShopBanned(ShopRepo shopRepo, ShopBannedRepo shopBannedRepo) {
		Shop shop = shopRepo.findById(2L).orElseThrow(() -> new RuntimeException("shop does not found"));


		// Creating a ShopBanned object
		ShopBanned shopBanned = new ShopBanned();
		shopBanned.setId(100L);
		shopBanned.setBanReason("Violation of marketplace policies");
		shopBanned.setBanStartDate(LocalDateTime.now());
		shopBanned.setBanEndDate(LocalDateTime.now().plusDays(7)); // Banned for 7 days
		shopBanned.setShop(shop);

		System.out.println("Saving Banned Shop...");
		shopBannedRepo.save(shopBanned);

	}

	private void createFeedBack(UserRepo userRepo, FeedBackRepo feedBackRepo, ProductRepo productRepo) {

		User user = userRepo.findById(2L).orElseThrow(() -> new RuntimeException("User does not found"));
		Product product = productRepo.findById(1L).orElseThrow(() -> new RuntimeException("Product does not found"));

		Feedback feedback = new Feedback();
		feedback.setId(100L);
		feedback.setContent("This is a great product!");
		feedback.setCreatedAt(LocalDate.now());
		feedback.setUpdatedAt(LocalDate.now());
		feedback.setUser(user);
		feedback.setProduct(product);

		System.out.println("Saving Feedback...");
		feedBackRepo.save(feedback);
		System.out.println("Done!!");
	}

	private void updatePayment(PaymentRepo paymentRepo, UserRepo userRepo) {

	}

	private void deletePayment(PaymentRepo paymentRepo) {
		System.out.println("Deleting payment...");
		paymentRepo.deleteById(2L);
		System.out.println("Done!!");
	}

	private void createPayment(PaymentRepo paymentRepo, UserRepo userRepo) {

		User user = userRepo.findById(2L).orElseThrow(() ->
				new RuntimeException("User does not found"));

		Payment payment = new Payment();
		payment.setBankName("ABC Bank");
		payment.setBankAccount("1234567890");
		payment.setAccountHolderName("John Doe");
		payment.setUser(user);

		System.out.println("Saving Payment");
		paymentRepo.save(payment);
		System.out.println("Done!");

	}

	private void deleteUserBannedById(UserBannedRepo userBannedRepo) {

		System.out.println("Deleting banned user with id: " + 1);
		UserBanned userBanned = userBannedRepo.findById(2L).orElseThrow(() ->
				new RuntimeException("Banned user does not found"));

		userBanned.getUser().setUserBanned(null);

		userBannedRepo.delete(userBanned);

		System.out.println("Done!");
	}

	private void createUserBanned(UserBannedRepo userBannedRepo, UserRepo userRepo) {
		//get id
		User user = userRepo.findById(2L).orElseThrow(() ->
				new RuntimeException("User does found"));

		UserBanned userBanned = new UserBanned();
		userBanned.setBanReason("Violation of terms of service");
		userBanned.setBanStartDate(LocalDateTime.now());
		userBanned.setBanEndDate(LocalDateTime.now().plusDays(7));
		userBanned.setUser(user);

		// saving user banned
		System.out.println("Saving banned user... ");
		userBannedRepo.save(userBanned);

	}

	private void createOrderDetail(OrderRepo orderRepo, OrderDetailRepo orderDetailRepo, ProductRepo productRepo) {

		// get order
		Order order = orderRepo.findById(2L).orElseThrow(() ->
			new RuntimeException("Order does not exist"));
		Product product = productRepo.findById(2L).orElse(null);

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setQuantity(2);
		orderDetail.setPrice(50000);
		orderDetail.setTotalPrice(orderDetail.getQuantity() * orderDetail.getPrice());
		orderDetail.setProduct(product);

		System.out.println("Creating Order Detail...");
		orderDetailRepo.save(orderDetail);
		System.out.println("Done!");

	}

	private void deleteUser(UserRepo userRepo) {
		long userId = 1;
		userRepo.deleteById(userId);
	}

	private void createShop(ShopRepo shopRepo, UserRepo userRepo) {

		// get user
		User user = userRepo.findById(2L).orElseThrow( () -> new RuntimeException("User does not found"));

		Shop shop = new Shop();
		shop.setId(1L);
//		shop.setName("Tech Haven");
		shop.setDescription("A store for the latest electronics and gadgets");
		shop.setCreatedAt(LocalDate.now());
		shop.setLogo("tech_haven_logo.png");
//		shop.setAddress("123 Main Street, New York, NY");
		shop.setPhoneNumber("+1 123-456-7890");
		shop.setStatus("Active");
		shop.setUser(user);
		System.out.println("Saving shop...");
		shopRepo.save(shop);
		System.out.println("Done!!");

	}

	private void createProduct(ProductRepo productRepo, ShopRepo shopRepo, SubCategoryRepo categoryRepo) {

		Shop shop = shopRepo.findById(1L).orElseThrow(() -> new RuntimeException("Shop does not exist!"));
		SubCategory category = categoryRepo.findById(1L).orElseThrow(() -> new RuntimeException("Category does not exist"));

		Product product = new Product();
		product.setName("Smartphone");
		product.setDescription("Latest 5G smartphone with AMOLED display");// Assuming 'M' is a valid size
		product.setRating(5);
		product.setThumbnail("smartphone.jpg");
		product.setShop(shop);
		product.setSubCategory(category);

		System.out.println("Saving product....");
		productRepo.save(product);
		System.out.println("Done!!!");
	}

	private void createCategory(SubCategoryRepo categoryRepo) {
		SubCategory category = new SubCategory();
		category.setName("Sandals");
		category.setCreatedAt(LocalDate.now());
		category.setDescription("All kinds of electronic gadgets");
		System.out.println("Saving Category...");
		categoryRepo.save(category);
		System.out.println("Done!");
	}


	private void createOrder(OrderRepo orderRepo, UserRepo userRepo, ShippingProviderRepo shippingProviderRepo) {
		// Create an Order object
		Order order = new Order();

		// Set attributes
		order.setOrderDate(LocalDateTime.now());
		order.setTotalPrice(500);
		order.setPaymentMethod("Credit Card");
		order.setPaymentStatus("Paid");
		order.setDiscountAmount(50);
		order.setShippingCost(20);
		order.setShippingAddress("123 Main St, City, Country");
		order.setShippingMethod("Express");
		order.setNotes("Deliver between 9 AM - 5 PM");
		order.setCouponCode("DISCOUNT50");
		order.setExpectedReceiveDate(LocalDate.now().plusDays(5));

		// get user
		long userId = 2;
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User does not found"));

		// get Shipping method
		long shippingId = 3;
		ShippingProvider shippingProvider = shippingProviderRepo.findById(shippingId).orElseThrow(() ->
				new RuntimeException("Shipping Provider does not found"));

		order.setUser(user);
		order.setShippingProvider(shippingProvider);

		// Saving order
		System.out.println("Saving order...");
		orderRepo.save(order);
	}


	private void createUser(UserRepo userRepo, RoleRepo roleRepo) {
		long roleId = 1;

		// get role
		Role role = roleRepo.findById(roleId).orElseThrow(() ->
				new RuntimeException("Role does not found"));


		User user = new User();
		// Setting values using setters
		user.setFullname("John Doe");
		user.setAccount("tankim1972");
		user.setPassword("securepassword");
//		user.setAddress("123 Street, City");
		user.setEmail("tankim1972@example.com");
		user.setGender(true); // true for male, false for female
//		user.setBirthdate(LocalDate.of(1995, 5, 20));
		user.setAvatar("avatar.jpg");

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
		role.setName("user");
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
