package limpito.utils;

import spark.*;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.Map;
import java.util.HashMap;


public class ViewUtil {

    public static String render(Map<String, Object> model, String templatePath) {     
        return new MustacheTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static Route notFound = (Request request, Response response) -> {
        response.status(404);
        return render(new HashMap<>(), "404_error.html");
    };

}
