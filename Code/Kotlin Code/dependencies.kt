package dependencies

interface Entity {
    abstract fun dropRewards(): List<Equipment>
}

interface Equipment {
    abstract fun baseValue(): Double
}

interface Weapon: Equipment {
    abstract fun baseDamage(): Double
}

interface Armor: Equipment {
    abstract fun baseArmor(): Double
}

class DragonPriest(val weapon: Weapon, val armor: Armor) {

    val characterModel = DragonPriestCharacterModel()
    val level = 50
    var hp = 130

    fun attack(target: Entity) {
        println("dragon priest attacks target for ${level * weapon.baseDamage()}")
    }

    fun dropRewards() = listOf(weapon, armor)
}

class DragonPriestMask : Armor {
    override fun baseArmor() = 23.0
    override fun baseValue() = 2000.0
}

class DragonPriestStaff : Weapon {
    override fun baseDamage() = 2.0
    override fun baseValue() = 2000.0
}

class DragonPriestCharacterModel

fun main() {
    val nahkriin = DragonPriest(
        DragonPriestStaff(),
        DragonPriestMask()
    )

}
