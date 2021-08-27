package com.example.tescontainers

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MariaDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmpresaServiceWithTestContainersTest {

    @Autowired
    lateinit var empresaService: EmpresaService

    @Autowired
    lateinit var empresaRepository: EmpresaRepository

    @BeforeEach
    fun `Sempre criar uma empresa com ID 1`() {
        empresaRepository.save(Empresa(idGlobal, nome1))
    }

    @Test
    fun `Testar a criação de Empresa`() {

        val empresa = empresaService.salvarEmpresa(Empresa(0, "Empresa teste 2"))

        assertNotEquals(0L, empresa.id)
    }

    @Test
    fun `Testar atualizar Empresa`() {

        var empresaSalva = empresaService.getEmpresa(idGlobal)

        assertEquals(nome1, empresaSalva?.nome ?: "")

        val empresa = Empresa(idGlobal, "Novo nome da empresa")

        empresaService.salvarEmpresa(empresa)

        empresaSalva = empresaService.getEmpresa(idGlobal)

        assertEquals("Novo nome da empresa", empresaSalva?.nome ?: "")
    }

    @Test
    fun `Testar remover Empresa`() {

        var empresaSalva = empresaService.getEmpresa(idGlobal)

        assertNotNull(empresaSalva)

        empresaService.removerEmpresa(idGlobal)

        empresaSalva = empresaService.getEmpresa(idGlobal)

        assertNull(empresaSalva)
    }

    companion object {

        private val nome1 = "Empresa Teste 1"
        private val idGlobal = 1L

        @Container
        private val container = MariaDBContainer<Nothing>(
            DockerImageName.parse("mariadb:10.5.5")
        )

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl )
            registry.add("spring.datasource.username", container::getUsername )
            registry.add("spring.datasource.password", container::getPassword )
        }
    }

}