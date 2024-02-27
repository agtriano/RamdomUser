package com.agt.ramsomuser.domain


import com.agt.ramsomuser.data.DataRepository
import com.agt.ramsomuser.data.model.Results

class GetAllContactsUseCase {
    private val repository = DataRepository()
    suspend operator fun invoke(): Results? = repository.getAllContacts()
}
