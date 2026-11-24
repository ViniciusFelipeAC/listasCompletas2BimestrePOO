# Aula 29 - DAO

## Questões

1. **B** - O problema é a diferença entre objetos na memória (com comportamento e encapsulamento) e tabelas no banco (linhas e chaves).
2. **C** - A classe mistura regra de negócio com JDBC, quebra o SRP e não dá pra testar sem banco.
3. **A** - O controller depende da interface, então dá pra trocar o banco ou usar um mock sem mexer no controller.
4. **D** - O Optional deixa claro que pode não ter resultado e obriga a tratar isso, evitando NullPointerException.

## Desafios de código

### 1 - mapearLinha

```java
private Livro mapearLinha(ResultSet rs) throws SQLException {
    Long id = rs.getLong("id");
    String titulo = rs.getString("titulo");
    String autor = rs.getString("autor");
    double preco = rs.getDouble("preco");
    return new Livro(id, titulo, autor, preco);
}
```

### 2 - salvar

```java
@Override
public void salvar(Livro livro) {
    String sql = "INSERT INTO livros (titulo, autor, preco) VALUES (?, ?, ?);";
    try (Connection conn = ConexaoFactory.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setDouble(3, livro.getPreco());

        stmt.executeUpdate();

        try (ResultSet chaves = stmt.getGeneratedKeys()) {
            if (chaves.next()) {
                livro.setId(chaves.getLong(1));
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao salvar livro: " + e.getMessage(), e);
    }
}
```

### 3 - buscarPorId

```java
@Override
public Optional<Livro> buscarPorId(Long id) {
    String sql = "SELECT id, titulo, autor, preco FROM livros WHERE id = ?;";
    try (Connection conn = ConexaoFactory.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setLong(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return Optional.of(mapearLinha(rs));
            }
            return Optional.empty();
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao consultar livro por ID: " + e.getMessage(), e);
    }
}
```

O projeto da lista oficial está na pasta `Aula29_BookStore`.
