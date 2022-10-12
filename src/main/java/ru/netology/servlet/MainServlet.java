package ru.netology.servlet;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {


  private final String GET = "GET";
  private final String POST = "POST";
  private final String UPDATE = "UPDATE";
  private final String DELETE = "DELETE";
  private final String PATH = "/api/posts";
  private final String PATH_WITH_ID = PATH + "/\\d+";
  private PostController controller;


  @Override
  public void init() {
    final var context = new AnnotationConfigApplicationContext("ru.netology");
    controller = context.getBean(PostController.class);
  }

  private long getID(String path){
    String str = path.substring(path.lastIndexOf("/") + 1);
    return Long.parseLong(str);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    // если деплоились в root context, то достаточно этого
    try {

      final var path = req.getRequestURI();
      final var method = req.getMethod();
      // primitive routing
      if (method.equals(GET) && path.equals(PATH)) {

        controller.all(resp);
        return;
      }
      if (method.equals(GET) && path.matches(PATH_WITH_ID)) {
        // easy way
        final var id = getID(path);
        controller.getById(id, resp);
        return;
      }
      if (method.equals(POST) && path.equals(PATH)) {
        controller.save(req.getReader(), resp);
        return;
      }
      if (method.equals(DELETE) && path.matches(PATH_WITH_ID)) {
        // easy way
        final var id = getID(path);
        controller.removeById(id, resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}