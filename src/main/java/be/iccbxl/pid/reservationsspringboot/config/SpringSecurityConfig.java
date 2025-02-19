package be.iccbxl.pid.reservationsspringboot.config;

import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.Collections;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, 12);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean permettant de charger les utilisateurs à partir du UserRepository.
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        System.out.println("📌 Initialisation du UserDetailsService..."); // ✅ Vérification
        return username -> {
            System.out.println("🔍 Tentative de connexion pour : " + username);

            // Recherche de l'utilisateur par son login
            User user = userRepository.findByLogin(username);

            if (user == null) {
                throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
            }

            System.out.println("✅ Utilisateur trouvé : " + user.getLogin());
            System.out.println("👤 Rôle de l'utilisateur : " + user.getRole());

            // Conversion de l'entité User en UserDetails.
            // on considère que le champ 'role' est un Enum et qu'on doit préfixer le nom du rôle avec "ROLE_"
            // SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
            String role = (user.getRole() != null) ? user.getRole().name() : "USER";
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getLogin())
                    .password(user.getPassword())
                    .authorities(Collections.singletonList(authority))
                    .build();
        };

    }

    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                //.csrf(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())    //Désactiver la protection des formulaires

                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/admin").hasRole("ADMIN");
                    auth.requestMatchers("/user").hasRole("MEMBER");

                    //API
                    auth.requestMatchers("/api/public/**").permitAll(); // Endpoints publics
                    auth.requestMatchers("/api/admin/**").hasRole("ADMIN"); // Endpoints réservés aux administrateurs

                    auth.anyRequest().permitAll();
                })
                .httpBasic(Customizer.withDefaults()) // Permet l'authentification de base (utile pour les tests)
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("login")
                        .failureUrl("/login?loginError=true"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logoutSuccess=true")
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))
                .build();

        /*return http.cors(Customizer.withDefaults())
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/error").permitAll();
                    auth.requestMatchers("/admin").hasRole("ADMIN");
                    auth.requestMatchers("/user").hasRole("MEMBER");
                    auth.anyRequest().permitAll();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("login")
                        .failureUrl("/login?loginError=true")
                        .defaultSuccessUrl("/home", true)
                        .successHandler((request, response, authentication) -> {
                            System.out.println("✅ Connexion réussie pour : " + authentication.getName());
                            response.sendRedirect("/home");
                        }))
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logoutSuccess=true")
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))
                .build();*/

        /*return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // ✅ Désactive toute sécurité temporairement
                .csrf(csrf -> csrf.disable()) // Désactive CSRF pour éviter d'autres erreurs
                .build();*/
    }

}
