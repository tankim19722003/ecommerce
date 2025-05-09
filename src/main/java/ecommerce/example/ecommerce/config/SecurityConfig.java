package ecommerce.example.ecommerce.config;

import ecommerce.example.ecommerce.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public AuthenticationProvider authProvider() {
        // work with db
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> {
                    request
                            // for user
                            .requestMatchers(POST,
                                    String.format("%s/user/login", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/user/register", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/user/get_user_info/**", apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN, Role.SHIPPING_PROVIDER)

                            .requestMatchers(PUT,
                                    String.format("%s/user/update_user_info/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(PUT,
                                    String.format("%s/user/update_email/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(POST,
                                    String.format("%s/user/update_avatar/**", apiPrefix)).hasRole(Role.USER)

                            // for address
                            .requestMatchers(POST,
                                    String.format("%s/user_village/add_user_address", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(PUT,
                                    String.format("%s/user_village/update_user_address/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/user_village/get_all_address/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/user_village/get_all_province", apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN, Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/user_village/get_all_districts/**", apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN, Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/user_village/get_all_villages/**", apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN, Role.SHOP)

                            .requestMatchers(DELETE,
                                    String.format("%s/user_village/**", apiPrefix)).hasAnyRole(Role.USER)


                            // shop
                            .requestMatchers(POST,
                                    String.format("%s/shop/register/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(PUT,
                                    String.format("%s/shop/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/shop/**", apiPrefix)).hasAnyRole(Role.SHOP, Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/shop/get_all_pending_shops", apiPrefix)).hasAnyRole(Role.ADMIN)
                            // otp
//                            .requestMatchers(POST,
//                                    String.format("%s/otp/send/sms/**", apiPrefix)).permitAll()


                            // attribute
                            .requestMatchers(GET,
                                    String.format("%s/attribute/**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/attribute/add_attribute/**", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(POST,
                                    String.format("%s/attribute/add_multiple_attribute/**", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(PUT,
                                    String.format("%s/attribute/**", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/attribute/**", apiPrefix)).hasRole(Role.ADMIN)


                            // categories
                            .requestMatchers(GET,
                                    String.format("%s/category/get_all_categories", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/category/create", apiPrefix)).hasAnyRole(Role.ADMIN)

                            .requestMatchers(PUT,
                                    String.format("%s/category/**", apiPrefix)).hasAnyRole(Role.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/category/**", apiPrefix)).hasAnyRole(Role.ADMIN)

                            // sub-category
                            .requestMatchers(GET,
                                    String.format("%s/sub_category/get_all_categories", apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN, Role.SHOP)

                            .requestMatchers(POST,
                                    String.format("%s/sub_category/create", apiPrefix)).hasAnyRole(Role.ADMIN)

                            .requestMatchers(PUT,
                                    String.format("%s/sub_category/**", apiPrefix)).hasAnyRole(Role.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/sub_category/**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(GET,
                                    String.format("%s/sub_category/**", apiPrefix)).permitAll()

                            // sub-category attribute
                            .requestMatchers(POST,
                                    String.format("%s/subcategory_attribute/add_one", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(POST,
                                    String.format("%s/subcategory_attribute/add_multiple/**", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(GET,
                                    String.format("%s/subcategory_attribute/**", apiPrefix)).permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/subcategory_attribute/**", apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/subcategory_attribute/**", apiPrefix)).hasRole(Role.ADMIN)


                            // product
                            .requestMatchers(POST,
                                    String.format("%s/product/create_product", apiPrefix)).hasAnyRole(Role.ADMIN, Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/product/get_all_product_by_key_word/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/product/get_all_with_rating_order", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/product/get_product_detail/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/product/get_shop_product/**", apiPrefix)).hasRole(Role.SHOP)

                            // product image
                            .requestMatchers(GET,
                                    String.format("%s/product_image/**", apiPrefix)).permitAll()


                            // product attribute value
                            .requestMatchers(GET,
                                    String.format("%s/product_attribute_value/**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/product_attribute_value/add_one/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(POST,
                                    String.format("%s/product_attribute_value/add_multiple/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(PUT,
                                    String.format("%s/product_attribute_value/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(DELETE,
                                    String.format("%s/product_attribute_value/**", apiPrefix)).hasRole(Role.SHOP)
                            //quantity
                            .requestMatchers(POST,
                                    String.format("%s/product_category/add_multiple/**", apiPrefix)).hasAnyRole(Role.SHOP)

                            .requestMatchers(POST,
                                    String.format("%s/product_category/add_one/**", apiPrefix)).hasAnyRole(Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/product_category/**", apiPrefix)).permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/product_category/**", apiPrefix)).hasAnyRole(Role.SHOP)

                            .requestMatchers(DELETE,
                                    String.format("%s/product_category/**", apiPrefix)).hasAnyRole(Role.SHOP)
                            // image
                            .requestMatchers(POST,
                                     String.format("%s/image/product_image/upload", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/image/**", apiPrefix)).hasAnyRole(Role.USER, Role.SHOP, Role.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/image/**", apiPrefix)).hasAnyRole(Role.SHOP)

                            .requestMatchers(DELETE,
                                    String.format("%s/image/get_all_product_images/**", apiPrefix)).hasAnyRole(Role.USER, Role.SHOP, Role.ADMIN)


//                            User code
                            .requestMatchers(GET,
                                    String.format("%s/user_code/user/send_code/**", apiPrefix)).hasAnyRole(Role.USER)

                            .requestMatchers(POST,
                                    String.format("%s/user_code/user/confirm_code/**", apiPrefix)).hasAnyRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/user_code/send_code/**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/user_code/confirm_code/**", apiPrefix)).permitAll()

                            // shipping type
                            .requestMatchers(GET,
                                    String.format("%s/shipping_type", apiPrefix)).permitAll()


                            // product category
                            .requestMatchers(POST,
                                    String.format("%s/product_category/add_multiple/one_level/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(POST,
                                    String.format("%s/product_category/add_multiple/two_level/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/product_category/**", apiPrefix)).permitAll()

                            // vouchers

                            .requestMatchers(GET,
                                    String.format("%s/voucher/get_vouchers/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/voucher/get_all", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/voucher/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(DELETE,
                                    String.format("%s/voucher/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(PUT,
                                    String.format("%s/voucher/**", apiPrefix)).hasRole(Role.SHOP)

                            // product shipping type
                            .requestMatchers(POST,
                                    String.format("%s/product_shipping/calculate_each_shipping_type_fee", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(POST,
                                    String.format("%s/product_shipping/create_product_shipping_type", apiPrefix)).hasRole(Role.SHOP)

                            // order
                            .requestMatchers(POST,
                                    String.format("%s/order/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/order/get_order_by_user_id_and_status/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/order/cancel_order/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/order/get_shop_order_by_status/**", apiPrefix)).hasRole(Role.SHOP)

                            // change order status by shop
                            .requestMatchers(GET,
                                    String.format("%s/order/status/to_packaging/**", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/order/status/to_handed_over_to_carrier_status/**", apiPrefix)).hasRole(Role.SHOP)

                            // change order status by shipping provider
                            .requestMatchers(GET,
                                    String.format("%s/order/status/to_shipping/**", apiPrefix)).hasRole(Role.SHIPPING_PROVIDER)

                            .requestMatchers(GET,
                                    String.format("%s/order/status/to_completed/**", apiPrefix)).hasRole(Role.SHIPPING_PROVIDER)

                            .requestMatchers(GET,
                                    String.format("%s/order/status/to_returning/**", apiPrefix)).hasRole(Role.SHIPPING_PROVIDER)

                            .requestMatchers(GET,
                                    String.format("%s/order/status/to_completed_returning/**", apiPrefix)).hasRole(Role.SHIPPING_PROVIDER)

                            .requestMatchers(POST,
                                    String.format("%s/order/add_shipping_provider", apiPrefix)).hasRole(Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/order/get_shipping_provider_order/**", apiPrefix)).hasAnyRole(Role.SHIPPING_PROVIDER, Role.ADMIN)
                            // Cart
                            .requestMatchers(POST,
                                    String.format("%s/cart/add_product_to_cart", apiPrefix)).hasRole(Role.USER)

//                            .requestMatchers(POST,
//                                    String.format("%s/cart/add_product_to_cart", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(PUT,
                                    String.format("%s/cart", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(DELETE,
                                    String.format("%s/cart", apiPrefix)).hasRole(Role.USER)

                            // feed back
                            .requestMatchers(POST,
                                    String.format("%s/feedback/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(PUT,
                                    String.format("%s/feedback/update_feedback/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/feedback/get_all_feed_back/**", apiPrefix)).permitAll()

                            // shipping provider
                            .requestMatchers(GET,
                                    String.format("%s/shipping_provider/get_all", apiPrefix)).hasAnyRole(Role.ADMIN, Role.SHOP)

                            .requestMatchers(POST,
                                    String.format("%s/change_order_status", apiPrefix)).hasAnyRole(Role.SHIPPING_PROVIDER)

                            .anyRequest().authenticated();

                    }

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);




        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("authorization","content-type","x-auth-token","Accept-Languague"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**",configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
