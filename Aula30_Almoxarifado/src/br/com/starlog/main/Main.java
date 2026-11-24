package br.com.starlog.main;

import br.com.starlog.dao.ProdutoDAO;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.model.Produto;
import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // apaga o banco antigo pra sempre começar do zero
        new File("almoxarifado.db").delete();

        ProdutoDAO dao = new ProdutoDAOSqlite();

        System.out.println("CREATE");
        Produto teclado = new Produto("Teclado Mecânico RGB", 249.90, 8);
        Produto mousepad = new Produto("Mousepad Speed XL", 79.90, 20);
        dao.salvar(teclado);
        dao.salvar(mousepad);
        System.out.println(teclado.getNome() + " -> ID " + teclado.getId());
        System.out.println(mousepad.getNome() + " -> ID " + mousepad.getId());

        System.out.println("\nREAD");
        imprimir(dao.listarTodos());

        System.out.println("\nUPDATE");
        teclado.setEstoque(15);
        teclado.setPreco(289.90);
        System.out.println("Atualizou: " + dao.atualizar(teclado));
        dao.buscarPorId(teclado.getId()).ifPresent(p -> System.out.println(p));

        System.out.println("\nDELETE");
        if (dao.excluir(mousepad.getId())) {
            System.out.println("Mousepad removido.");
        } else {
            System.out.println("Mousepad não encontrado.");
        }

        if (dao.excluir(888L)) {
            System.out.println("Produto 888 removido.");
        } else {
            System.out.println("Produto 888 não encontrado.");
        }

        System.out.println("\nESTADO FINAL");
        List<Produto> restantes = dao.listarTodos();
        imprimir(restantes);
        System.out.println("Total: " + restantes.size());
    }

    private static void imprimir(List<Produto> produtos) {
        System.out.println(String.format("%-4s | %-22s | %11s | %8s", "ID", "NOME", "PREÇO", "ESTOQUE"));
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}
