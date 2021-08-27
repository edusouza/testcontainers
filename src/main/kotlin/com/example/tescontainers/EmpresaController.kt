package com.example.tescontainers

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/empresa")
class EmpresaController(private val service: EmpresaService) {

    @GetMapping("/{id}")
    fun getEmpresa(@PathVariable("id") id: Long): Empresa? = service.getEmpresa(id)

    @PostMapping("/{id}")
    fun atualizaEmpresa(@RequestBody empresa: Empresa): Empresa = service.salvarEmpresa(empresa)

    @PutMapping("")
    fun criarEmpresa(@RequestBody empresa: Empresa): Empresa = service.salvarEmpresa(empresa)

    @DeleteMapping("/{id}")
    fun removerEmpresa(@PathVariable("id") id: Long) = service.removerEmpresa(id)
}