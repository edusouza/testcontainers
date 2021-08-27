package com.example.tescontainers

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EmpresaServiceTest {

    @Autowired
    lateinit var empresaService: EmpresaService

    @Test
    fun `Testar a criação de Empresa`() {

        val empresa = empresaService.salvarEmpresa(Empresa(0, "Empresa teste"))

        assertNotEquals(0L, empresa.id)
    }

    @Test
    fun `Testar atualizar Empresa`() {

        var empresaSalva = empresaService.getEmpresa(10)

        assertEquals("Empresa de serviços LTDA", empresaSalva?.nome ?: "")

        val empresa = Empresa(10, "Novo nome da empresa")

        empresaService.salvarEmpresa(empresa)

        empresaSalva = empresaService.getEmpresa(10)

        assertEquals("Novo nome da empresa", empresaSalva?.nome ?: "")
    }

    @Test
    fun `Testar remover Empresa`() {

        var empresaSalva = empresaService.getEmpresa(10)

        assertNotNull(empresaSalva)

        empresaService.removerEmpresa(10)

        empresaSalva = empresaService.getEmpresa(10)

        assertNull(empresaSalva)
    }

}