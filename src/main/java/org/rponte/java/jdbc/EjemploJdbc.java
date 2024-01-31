package org.rponte.java.jdbc;

import org.rponte.java.jdbc.model.Categoria;
import org.rponte.java.jdbc.model.Producto;
import org.rponte.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.rponte.java.jdbc.repositorio.Repositorio;

public class EjemploJdbc {
    public static void main(String[] args) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

            System.out.println("========== listar ==========");
            repositorio.listar().forEach(System.out::println);

            System.out.println("========== obtener por id ==========");
            System.out.println(repositorio.porId(1L));

//            System.out.println("========== insertar nuevo producto ==========");
//            Producto producto = new Producto();
//            producto.setNombre("Teclado mecánico Redradon");
//            producto.setPrecio(240);
//            producto.setFecha_registro(new Date());
//            Categoria categoria = new Categoria();
//            categoria.setId(3L);
//            producto.setCategoria(categoria);
//            repositorio.guardar(producto);
//            System.out.println("Producto guardado con éxito");

            System.out.println("========== editar producto ==========");
            Producto producto1 = new Producto();
            producto1.setId(5L);
            producto1.setNombre("Teclado mecánico Corsair");
            producto1.setPrecio(700);
            Categoria categoria1 = new Categoria();
            categoria1.setId(2L);
            producto1.setCategoria(categoria1);
            //repositorio.guardar(producto1);
            System.out.println("Producto editado con éxito");

            System.out.println("========== listar todo ==========");
            repositorio.listar().forEach(System.out::println);
    }
}
