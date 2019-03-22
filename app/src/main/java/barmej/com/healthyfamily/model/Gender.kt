package barmej.com.healthyfamily.model

enum class Gender {
    MALE, FEMALE;

    companion object {
        fun fromIndex(value: Int?): Gender {
            return when (value) {
                0 -> MALE
                1 -> FEMALE
                else -> MALE
            }
        }


        fun toIndex(gender: Gender?): Int {
            return when (gender) {
                MALE -> 0
                FEMALE -> 1
                else -> -1
            }
        }
    }
}