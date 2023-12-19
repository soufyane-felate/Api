package com.example.api_trantinet
class Trantinet(
    val id: Int,
    val name: String,
    val battery_level: Int,
    val max_distance: Int,
    var image: String
) {
    override fun toString(): String {
        return "Trantinet(id=$id, name='$name', battery_level=$battery_level, max_distance=$max_distance, image='$image')"
    }
}


