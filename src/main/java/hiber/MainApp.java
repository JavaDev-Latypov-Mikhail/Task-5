package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car bugatti = new Car("Bugatti Mistral", 16);
        Car ferrari = new Car("Ferrari Purasangue", 812);
        Car lamborghini = new Car("Lamborghini Revuelto", 1);
        Car mercedes = new Car("Mercedes-Maybach GLS", 600);

        User user1 = new User("Dmitriy", "Belyakov", "i@belyacov.ru");
        user1.setCar(bugatti);
        userService.add(user1);

        User user2 = new User("Dmitriy", "Shapovalov", "bestDev&co@gmail.com");
        user2.setCar(ferrari);
        userService.add(user2);

        User user3 = new User("Vasiliy", "Shashihin", "it@it.org");
        user3.setCar(lamborghini);
        userService.add(user3);

        User user4 = new User("Maksim", "Shesternev", "java@spring.com");
        user4.setCar(mercedes);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.getUserByCarModelAndSeries("Bugatti Mistral", 16));
        context.close();
    }
}
