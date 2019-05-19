package Serlvets;

import Class.Attribute;
import Class.Parameter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registrator extends HttpServlet {

    Parameter prm = null;
    Attribute attr = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("userName");
        String val = request.getParameter("Value");
        String param = null;

        try (PrintWriter out = response.getWriter()) {
            if (user == null || user.trim().isEmpty() || user.length() > 255) {
                user = "Thе field userName is incorrect, please add some symbols ";
                out.println(user);
                out.close();
            } else {
                if (!val.matches("[-+]?\\d+")) {
                    val = "Value is incorrect. Input correct!";
                    out.println(val);
                    out.close();
                } else {
                    out.println("<br><a href='http://localhost:8080/Task1/index.html'>Внести еще один параметр?<a/><br>");
                    out.close();

                }
            }
            Attribute attr = (Attribute) getServletContext().getAttribute("attr");
            if (attr == null) {
                attr = new Attribute();
                getServletContext().setAttribute("attr", attr);
            }

            int value = Integer.parseInt(request.getParameter("Value"));
            prm = new Parameter(user, value);

            if (prm != null) {
                param = "<h3>" + prm.getName() + " : " + prm.getVal() + " added" + "</h3>"; 
                System.out.println("введённый параметр добавлен как новый");
                for (Parameter p : attr.getUsers()) {
                    if (prm.getName().equals(p.getName())) {
                        param = "<h3>Warning: " + p.getName() + " replaced to " + p.getVal() + "</h3>"; //введённый параметр заменил ранее введённый параметр в случае,
                                                                                                        //когда параметр с таким именем уже присутствует в системе;
                        System.out.println("замена параметра");
                        break;
                    }
                }
                if (!attr.users.add(prm)) {
                    param = "<h3>" + prm.getName() + " : " + prm.getVal() + " not added" + "</h3>";
                }

            }
            out.println("<h1>" + param + "</h1>");

            out.println("<br><a href='http://localhost:8080/Task1/index.html'>Назад<a/><br>");
            out.println("<br><a href='http://localhost:8080/Task1/ViewList'>Просмотр введенных данных<a/><br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
