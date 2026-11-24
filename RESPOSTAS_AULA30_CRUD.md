# Aula 30 - CRUD

## Questões

1. **A** - O CRUD foi definido pelo James Martin em 1983: Create (INSERT), Read (SELECT), Update (UPDATE) e Delete (DELETE).
2. **C** - Se o ID não existir o banco não dá erro, só retorna 0. Checando `> 0` dá pra saber se o registro realmente foi alterado.
3. **D** - Sem o WHERE o DELETE apaga todas as linhas da tabela.
4. **B** - A View manda os dados pro Controller, o Controller cria o objeto e chama o DAO, e o DAO grava no banco.

## Desafios de código

### 1 - atualizar

```java
@Override
public boolean atualizar(Produto produto) {
    String sql = "UPDATE produtos SET nome = ?, preco = ?, estoque = ? WHERE id = ?;";
    try (Connection conn = ConexaoFactory.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setInt(3, produto.getEstoque());
        stmt.setLong(4, produto.getId());

        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
    }
}
```

### 2 - excluir

```java
@Override
public boolean excluir(Long id) {
    String sql = "DELETE FROM produtos WHERE id = ?;";
    try (Connection conn = ConexaoFactory.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setLong(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao excluir produto: " + e.getMessage(), e);
    }
}
```

### 3 - listarTodos

```java
@Override
public List<Produto> listarTodos() {
    String sql = "SELECT id, nome, preco, estoque FROM produtos ORDER BY nome;";
    List<Produto> lista = new ArrayList<>();
    try (Connection conn = ConexaoFactory.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            lista.add(mapearLinha(rs));
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar catálogo: " + e.getMessage(), e);
    }
    return lista;
}
```

O projeto da lista oficial está na pasta `Aula30_Almoxarifado`.
