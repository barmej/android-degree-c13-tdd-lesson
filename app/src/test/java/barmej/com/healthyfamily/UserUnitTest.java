package barmej.com.healthyfamily;

import org.junit.Before;
import org.junit.Test;

import barmej.com.healthyfamily.model.Gender;
import barmej.com.healthyfamily.model.User;

import static org.junit.Assert.assertEquals;

public class UserUnitTest {

    private User user;

    @Before
    public void init() {
        user = new User("Abdullah", 50.5, 150, 20, Gender.MALE);

    }

    @Test
    public void test_is_name_abdullah() {
        assertEquals("Abdullah", user.getName());
    }

    @Test
    public void test_height_to_m() {
        assertEquals(150 / 100.0, user.getHeightInM(), 0);
    }

    @Test
    public void test_is_gender_male() {
        assertEquals(Gender.MALE, user.getGender());
    }

    @Test
    public void test_change_weight() {
        user.setWeight(53);
        assertEquals(53, user.getWeight(), 0);
    }

    @Test
    public void test_change_height() {
        user.setHeight(153);
        assertEquals(153, user.getHeight(), 0);
    }

    @Test
    public void test_change_age() {
        user.setAge(22);
        assertEquals(22, user.getAge());
    }

}
