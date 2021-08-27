package com.example.tescontainers

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : CrudRepository<Empresa, Long> {

    fun findEmpresaByNome(nome: String): Empresa?
}