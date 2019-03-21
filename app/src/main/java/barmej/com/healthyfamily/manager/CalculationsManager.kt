package barmej.com.healthyfamily.manager

import barmej.com.healthyfamily.model.Gender
import barmej.com.healthyfamily.model.User

object CalculationsManager {

    //    Calculates the BMR
    fun basalMetabolicRate(user: User): Double {
        val bmr = (10 * user.weight) + (6.25 * user.height) - (5 * user.age)
        return when (user.gender) {
            Gender.MALE -> bmr + 5
            Gender.FEMALE -> bmr - 161
        }
    }

    fun bmrString(user: User): String {
        return String.format("%.2f", basalMetabolicRate(user))
    }

    //    Calculates the TI
    fun totalIntake(user: User, activeType: Active): Double {
        return when (activeType) {
            Active.SEDENTARY -> basalMetabolicRate(user) * 1.53
            Active.ACTIVE -> basalMetabolicRate(user) * 1.76
            Active.Vigorously -> basalMetabolicRate(user) * 2.25
        }
    }

    fun tiString(user: User, activeType: Active): String {
        return String.format("%.2f", totalIntake(user, activeType))
    }

    //    Calculates the BMI = kg/m^2
    fun bodyMassIndex(user: User): Double {
        return user.weight / (user.heightInM * user.heightInM)
    }

    fun bmiString(user: User): String {
        return String.format("%.2f", bodyMassIndex(user))
    }
}