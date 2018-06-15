package limpito;
import limpito.service.*;
import limpito.controller.*;
import limpito.model.*;

import java.util.*;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {    

        System.out.println( "Hello World!" );
        try{
        	List<Pedido> lista = PedidoService.lista_pedidos();
            int i = 0;
            for(Pedido x : lista){
                System.out.println(++i);
                System.out.println(x.toString());
            }

        }catch(Exception e){System.err.println(e);}
        
        staticFiles.location("/public");
        get("/hello",(req, res) -> "Hello World!");
        get("/hello/:name",(req, res) -> "Hello "+req.params(":name")+"!");
		get("/pedidos", PedidosController.servePedidos);
    }
}
