package barmej.com.healthyfamily;

import org.junit.Before;
import org.junit.Test;

import barmej.com.healthyfamily.manager.Active;
import barmej.com.healthyfamily.manager.CalculationsManager;
import barmej.com.healthyfamily.model.Gender;
import barmej.com.healthyfamily.model.User;

import static org.junit.Assert.assertEquals;

public class CalculationsManagerUnitTest {
    private User user;

    @Before
    public void init() {
        user = new User("Abdullah", 75, 170, 25, Gender.MALE);

    }

    @Test
    public void test_bmr() {
        assertEquals(1692.5, CalculationsManager.INSTANCE.basalMetabolicRate(user), 0.009);
    }

    @Test
    public void test_bmi() {
        assertEquals(25.95, CalculationsManager.INSTANCE.bodyMassIndex(user), 0.009);
    }

    @Test
    public void test_ti() {
        Active active = Active.SEDENTARY;
        assertEquals(2589.53, CalculationsManager.INSTANCE.totalIntake(user, active), 0.009);
    }
}
