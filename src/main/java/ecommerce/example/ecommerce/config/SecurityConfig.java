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
                                    String.format("%s/user/get_user_info/**", apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN)

                            .requestMatchers(PUT,
                                    String.format("%s/user/update_user_info/**", apiPrefix)).hasRole(Role.USER)

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
                                    String.format("%s/attribute/**", apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN, Role.SHOP)

                            // categories
                            .requestMatchers(GET,
                                    String.format("%s/category/get_all_categories", apiPrefix)).hasAnyRole(Role.USER,Role.ADMIN, Role.SHOP)

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


                            // product
                            .requestMatchers(POST,
                                    String.format("%s/product/create_product", apiPrefix)).hasAnyRole(Role.ADMIN, Role.SHOP)


                            // product attribute value
                            .requestMatchers(POST,
                                    String.format("%s/product_attribute_value/**", apiPrefix)).hasAnyRole(Role.ADMIN, Role.SHOP)

                            //quantity
                            .requestMatchers(POST,
                                    String.format("%s/quantity", apiPrefix)).hasAnyRole(Role.SHOP)

                            .requestMatchers(GET,
                                    String.format("%s/quantity/**", apiPrefix)).hasAnyRole(Role.USER, Role.SHOP, Role.ADMIN)
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
                                    String.format("%s/user_code/send_code/**", apiPrefix)).hasAnyRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/user_code/confirm_code/**", apiPrefix)).hasAnyRole(Role.USER)
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
