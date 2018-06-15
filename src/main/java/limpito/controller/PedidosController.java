package limpito.controller;

import limpito.service.PedidoService;
import limpito.model.Pedido;
import limpito.utils.*;

import spark.*;
import java.util.HashMap;
import java.util.Map;


public class PedidosController {

	  public static Route servePedidos = (Request request, Response response) -> {
	    Map model = new HashMap();
	    model.put("pedidos",PedidoService.lista_pedidos());
	    return ViewUtil.render(model,"pedidos.mustache");
	  };

}