# Diagrama de classes - StarLog-Zero

**Carga** (model)
- codigoRastreio: String (final)
- categoria: String
- pesoKg: double
- valorSeguro: double
- getters, equals, hashCode, toString

**ModuloCarga** (model)
- idModulo: String
- capacidadeMaxima: int
- cargas: List<Carga>
- carregarCarga(Carga) throws CapacidadeExcedidaException
- calcularSeguroTotal(), contarPorCategoria(String), calcularSeguroPesadas(double)

**BaseLancamento** (model)
- modulos: Map<String, ModuloCarga>
- cadastrarModulo(ModuloCarga), buscarModulo(String)

**CapacidadeExcedidaException** (exception) - extends Exception

**MainResgate** (main) - roda os 4 ataques

Relações: BaseLancamento tem vários ModuloCarga e ModuloCarga tem várias Carga.
