package com.agt.ramsomuser.data.model

import java.util.Date

data class Results(
    var results: ArrayList<Result>,
    var info: Info?
)

data class Coordinates(
    var latitude: String?,
    var longitude: String?
)

data class Dob(
    var date: Date?,
    var age: Int
)

data class Id(
    var name: String?,
    var value: String?,
)

data class Info(
    var seed: String?,
    var results: Int,
    var page: Int,
    var version: String?
)

data class Location(
    var street: Street?,
    var city: String?,
    var state: String?,
    var country: String?,
    var postcode: String?,
    var coordinates: Coordinates?,
    var timezone: Timezone?
)

data class Login(
    var uuid: String?,
    var username: String?,
    var password: String?,
    var salt: String?,
    var md5: String?,
    var sha1: String?,
    var sha256: String?
)

data class Name(
    var title: String?,
    var first: String?,
    var last: String?
)

data class Picture(
    var large: String?,
    var medium: String?,
    var thumbnail: String?
)

data class Registered(
    var date: Date?,
    var age: Int
)

data class Result(
    var gender: String?,
    var name: Name?,
    var location: Location?,
    var email: String?,
    var login: Login?,
    var dob: Dob?,
    var registered: Registered?,
    var phone: String?,
    var cell: String?,
    var id: Id?,
    var picture: Picture?,
    var nat: String?
)

data class Street(
    var number: Int = 0,
    var name: String?
)

data class Timezone(
    var offset: String?,
    var description: String?
)