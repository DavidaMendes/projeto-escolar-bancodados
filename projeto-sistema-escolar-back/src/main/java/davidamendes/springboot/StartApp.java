package davidamendes.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import davidamendes.springboot.Menu;

@Component
public class StartApp implements CommandLineRunner {


    @Autowired
    private Menu menu;

    @Override
    public void run(String... args) throws Exception {
        menu.exibirMenu();

    }
}
