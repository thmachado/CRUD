package org.thmachado.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private final String firstname = "Thiago";
    private final String lastname = "Machado";
    private final String email = "thiago.wimps@gmail.com";
    private User userTest;

    @BeforeEach
    void setUp(){
        this.userTest = new User(this.firstname, this.lastname, this.email);
    }

    @Test
    void shouldGetFirstname(){
        assertEquals(this.firstname, this.userTest.getFirstname());
    }

    @Test
    void shouldGetLastname(){
        assertEquals(this.lastname, this.userTest.getLastname());
    }

    @Test
    void shouldGetEmail(){
        assertEquals(this.email, this.userTest.getEmail());
    }

    @Test
    void shouldNotCreateUserWithWrongFirstname(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User("", this.lastname, this.email);
        });
    }

    @Test
    void shouldNotCreateUserWithWrongLastname(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User(this.firstname, null, this.email);
        });
    }

    @Test
    void shouldNotCreateUserWithInvalidEmail(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User(this.firstname, this.lastname, this.firstname);
        });
    }

    @Test
    void shouldNotSetBlankFirstname(){
        assertThrows(IllegalArgumentException.class, () -> {
           this.userTest.changeFirstname("");
        });
    }

    @Test
    void shouldNotSetNullFirstname(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.userTest.changeFirstname(null);
        });
    }

    @Test
    void shouldNotSetBlankLastname(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.userTest.changeLastname("");
        });
    }

    @Test
    void shouldNotSetNullLastname(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.userTest.changeLastname(null);
        });
    }

    @Test
    void shouldNotSetBlankEmail(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.userTest.changeEmail("");
        });
    }

    @Test
    void shouldNotSetNullEmail(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.userTest.changeEmail(null);
        });
    }

    @Test
    void shouldNotSetWrongEmail(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.userTest.changeEmail("thiagoemail.com");
        });
    }

    @Test
    void shouldChangeFirstname(){
        String firstname = "Raphael";
        this.userTest.changeFirstname(firstname);
        assertEquals(firstname, this.userTest.getFirstname());
    }

    @Test
    void shouldChangeLastname(){
        String lastname = "Veiga";
        this.userTest.changeLastname(lastname);
        assertEquals(lastname, this.userTest.getLastname());
    }

    @Test
    void shouldChangeEmail(){
        String email = "thiago@email.com";
        this.userTest.changeEmail(email);
        assertEquals(email, this.userTest.getEmail());
    }
}
