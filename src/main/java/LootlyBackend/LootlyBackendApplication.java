package LootlyBackend;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import LootlyBackend.config.AppConstants;
import LootlyBackend.entities.Role;
import LootlyBackend.repository.RoleRepo;

@SpringBootApplication
@ComponentScan(basePackages = {"LootlyBackend", "LootlyBackendSecurity"})
public class LootlyBackendApplication implements CommandLineRunner{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(LootlyBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		
		try {
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			
			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			List<Role> roles=List.of(role,role1);
			List<Role> result=this.roleRepo.saveAll(roles);
			
			result.forEach(r ->{
				System.out.println(r.getName());
			});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

//    @Override
//    public void run(String... args) throws Exception {
//        // Example of logic to run at application startup
//        System.out.println("Application started successfully!");
//        System.out.println("Encoded password example: " + passwordEncoder.encode("yourPassword"));
//
//        // Initialize roles or other setup tasks
//        if (roleRepo.count() == 0) {
//            Role adminRole = new Role();
//            adminRole.setName("ROLE_ADMIN");
//            roleRepo.save(adminRole);
//
//            Role userRole = new Role();
//            userRole.setName("ROLE_USER");
//            roleRepo.save(userRole);
//
//            System.out.println("Default roles created!");
//        }
//    }
}
