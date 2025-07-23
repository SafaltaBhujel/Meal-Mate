package np.com.safaltabhujel.mealmateapp.models

data class Grocery(
    val name: String = "",

    @field:JvmField
    var isPurchased: Boolean = false
)
