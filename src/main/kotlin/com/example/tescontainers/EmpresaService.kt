package com.example.tescontainers

import org.springframework.stereotype.Service

@Service
class EmpresaService(private val repository: EmpresaRepository) {

    fun getEmpresa(id: Long): Empresa? {
        return repository.findById(id).orElse(null)
    }

    fun salvarEmpresa(empresa: Empresa): Empresa {
        return repository.save(empresa)
    }

    fun removerEmpresa(id: Long): Any {
        return repository.deleteById(id)
    }

    fun getEmpresaByNome(nome: String): Empresa? = repository.findEmpresaByNome(nome)
}