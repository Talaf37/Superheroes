package com.example.superheroes.data

class SuperheroesResponse (
    @SerializedName("response") val response:String,
    @SerializedName("results-for") val resultsFor:String,
    @SerializedName("results") val results:List<Superhero>
) {
}

class Superhero (
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("image") val image:Image
) {

}

class Image (
    @SerializedName("url") val url:String
) {

}

annotation class SerializedName(val value: String)
