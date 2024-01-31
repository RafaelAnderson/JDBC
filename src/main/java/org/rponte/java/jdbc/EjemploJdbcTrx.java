package org.rponte.java.jdbc;

import org.rponte.java.jdbc.model.Categoria;
import org.rponte.java.jdbc.model.Producto;
import org.rponte.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.rponte.java.jdbc.repositorio.Repositorio;
import org.rponte.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcTrx {
    public static void main(String[] args) throws SQLException {

        try (
                Connection conn = ConexionBaseDatos.getInstance()
        ) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

                System.out.println("========== listar ==========");
                repositorio.listar().forEach(System.out::println);

                System.out.println("========== obtener por id ==========");
                System.out.println(repositorio.porId(1L));

                System.out.println("========== insertar nuevo producto ==========");
                Producto producto = new Producto();
                producto.setNombre("Teclado mecánico IBM");
                producto.setPrecio(1500);
                producto.setFecha_registro(new Date());
                Categoria categoria = new Categoria();
                categoria.setId(3L);
                producto.setCategoria(categoria);
                producto.setSku("abcd12345");
                repositorio.guardar(producto);
                System.out.println("Producto guardado con éxito");

                System.out.println("========== editar producto ==========");
                producto.setId(5L);
                producto.setNombre("Teclado mecánico Corsair");
                producto.setPrecio(700);
                producto.setSku("abcd123456");
                categoria = new Categoria();
                categoria.setId(2L);
                producto.setCategoria(categoria);
                repositorio.guardar(producto);
                System.out.println("Producto editado con éxito");

                System.out.println("========== listar todo ==========");
                repositorio.listar().forEach(System.out::println);
                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
                exception.printStackTrace();
            }
        }
    }
}
