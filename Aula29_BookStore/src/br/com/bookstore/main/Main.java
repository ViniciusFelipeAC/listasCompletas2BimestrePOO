package br.com.bookstore.main;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;
import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // apaga o banco antigo pra os ids sempre começarem do 1
        new File("bookstore.db").delete();

        LivroDAO dao = new LivroDAOSqlite();

        Livro l1 = new Livro("Clean Code", "Robert C. Martin", 129.90);
        Livro l2 = new Livro("Design Patterns", "Gang of Four", 189.50);
        Livro l3 = new Livro("Refactoring", "Martin Fowler", 155.00);

        dao.salvar(l1);
        dao.salvar(l2);
        dao.salvar(l3);

        System.out.println("IDs gerados: " + l1.getId() + ", " + l2.getId() + ", " + l3.getId());

        System.out.println("\nBuscando ID 2:");
        dao.buscarPorId(2L).ifPresentOrElse(
                livro -> System.out.println(livro),
                () -> System.out.println("Livro não encontrado.")
        );

        System.out.println("\nBuscando ID 999:");
        dao.buscarPorId(999L).ifPresentOrElse(
                livro -> System.out.println(livro),
                () -> System.out.println("Livro não encontrado.")
        );

        System.out.println("\nCatálogo:");
        List<Livro> livros = dao.listarTodos();
        System.out.println(String.format("%-4s | %-20s | %-22s | %10s", "ID", "TÍTULO", "AUTOR", "PREÇO"));
        for (Livro l : livros) {
            System.out.println(l);
        }
    }
}
